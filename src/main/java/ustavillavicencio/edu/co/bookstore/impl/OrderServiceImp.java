package ustavillavicencio.edu.co.bookstore.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.Order;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ustavillavicencio.edu.co.bookstore.dto.request.OrderItemRequest;
import ustavillavicencio.edu.co.bookstore.dto.request.OrderRequest;
import ustavillavicencio.edu.co.bookstore.dto.response.OrderResponse;
import ustavillavicencio.edu.co.bookstore.entity.OrderItem;
import ustavillavicencio.edu.co.bookstore.entity.OrderStatus;
import ustavillavicencio.edu.co.bookstore.mapper.OrderMapper;
import ustavillavicencio.edu.co.bookstore.repository.OrderRepository;

@Service
@RequiredArgsConstructor

public class OrderServiceImp {

    private final OrderRepository orderRepository;
    private final BookRepository  bookRepository;
    private final UserRepository  userRepository;
    private final OrderMapper     orderMapper;

    @Transactional
    public OrderResponse createOrder(OrderRequest request, String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        List<OrderItem> items   = new ArrayList<>();
        BigDecimal      total   = BigDecimal.ZERO;

        for (OrderItemRequest itemReq : request.getItems()) {

            Book book = bookRepository.findById(itemReq.getBookId())
                    .orElseThrow(() -> new ResourceNotFoundException("Libro con id " + itemReq.getBookId() + " no encontrado"));

            // ① Verificar stock — lanza excepción si no alcanza
            if (book.getStock() < itemReq.getQuantity()) {
                throw new InsufficientStockException(book.getTitle(), book.getStock(), itemReq.getQuantity());
            }

            // ② Calcular subtotal (precio histórico)
            BigDecimal subtotal = book.getPrice().multiply(BigDecimal.valueOf(itemReq.getQuantity()));

            OrderItem item = new OrderItem();
            item.setBook(book);
            item.setQuantity(itemReq.getQuantity());
            item.setSubtotal(subtotal);
            items.add(item);

            // ③ Acumular total
            total = total.add(subtotal);

            // ④ Decrementar stock del libro
            book.setStock(book.getStock() - itemReq.getQuantity());
            bookRepository.save(book);
        }

        // ⑤ Construir y persistir el pedido
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setTotal(total);
        items.forEach(item -> item.setOrder(order));   // relación bidireccional
        order.setItems(items);

        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getMyOrders(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return orderRepository.findByUser(user).stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(dontRollbackOn = true)
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    @Transactional
    public OrderResponse cancelOrder(Long orderId, String userEmail) {

        Order order = orderRepository.findById(orderId).orElseThrow();

        // Regla: solo el dueño puede cancelar
        if (!order.getUser().getEmail().equals(userEmail)) {
            throw new UnauthorizedAccessException();
        }

        // Regla: CONFIRMED no se puede cancelar
        if (order.getStatus() == OrderStatus.CONFIRMED) {
            throw new InvalidOrderStateException(order.getStatus());
        }

        order.setStatus(OrderStatus.CANCELLED);
        return orderMapper.toResponse(orderRepository.save(order));
    }

}
