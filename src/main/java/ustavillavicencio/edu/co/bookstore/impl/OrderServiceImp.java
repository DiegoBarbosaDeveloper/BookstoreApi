package ustavillavicencio.edu.co.bookstore.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ustavillavicencio.edu.co.bookstore.dto.request.OrderItemRequest;
import ustavillavicencio.edu.co.bookstore.dto.request.OrderRequest;
import ustavillavicencio.edu.co.bookstore.dto.response.OrderResponse;
import ustavillavicencio.edu.co.bookstore.entity.BookEntity;
import ustavillavicencio.edu.co.bookstore.entity.OrderItemEntity;
import ustavillavicencio.edu.co.bookstore.entity.OrderEntity;
import ustavillavicencio.edu.co.bookstore.entity.UserEntity;
import ustavillavicencio.edu.co.bookstore.enums.OrderStatus;
import ustavillavicencio.edu.co.bookstore.exception.custom.InsufficientStockException;
import ustavillavicencio.edu.co.bookstore.exception.custom.InvalidOrderStateException;
import ustavillavicencio.edu.co.bookstore.exception.custom.ResourceNotFoundException;
import ustavillavicencio.edu.co.bookstore.exception.custom.UnauthorizedAccessException;
import ustavillavicencio.edu.co.bookstore.mapper.OrderMapper;
import ustavillavicencio.edu.co.bookstore.repository.BookRepository;
import ustavillavicencio.edu.co.bookstore.repository.OrderRepository;
import ustavillavicencio.edu.co.bookstore.repository.UserRepository;
import ustavillavicencio.edu.co.bookstore.service.OrderService;

@Service
@RequiredArgsConstructor

public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository  bookRepository;
    private final UserRepository  userRepository;
    private final OrderMapper  orderMapper;

    @Transactional
    public OrderResponse createOrder(OrderRequest request, String userEmail) {

        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        List<OrderItemEntity> items   = new ArrayList<>();
        BigDecimal      total   = BigDecimal.ZERO;

        for (OrderItemRequest itemReq : request.getItems()) {

            BookEntity book = bookRepository.findById(itemReq.getBookId())
                    .orElseThrow(() -> new ResourceNotFoundException("Libro con id " + itemReq.getBookId() + " no encontrado"));

            // ① Verificar stock — lanza excepción si no alcanza
            if (book.getStock() < itemReq.getQuantity()) {
                throw new InsufficientStockException("Stock insuficiente para el libro: " + book.getTitle());
            }

            // ② Calcular subtotal (precio histórico)
                BigDecimal subtotal = BigDecimal.valueOf(book.getPrice())
                    .multiply(BigDecimal.valueOf(itemReq.getQuantity()));

                OrderItemEntity item = OrderItemEntity.builder()
                    .book(book)
                    .quantity(itemReq.getQuantity())
                    .subtotal(subtotal)
                    .build();
            items.add(item);

            // ③ Acumular total
            total = total.add(subtotal);

            // ④ Decrementar stock del libro
            book.setStock(book.getStock() - itemReq.getQuantity());
            bookRepository.save(book);
        }

        // ⑤ Construir y persistir el pedido
        OrderEntity order = new OrderEntity();
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
        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return orderRepository.findByUser(user).stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    @Transactional
    public OrderResponse cancelOrder(Long orderId, String userEmail) {

        OrderEntity order = orderRepository.findById(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Pedido con id " + orderId + " no encontrado"));

        // Regla: solo el dueño puede cancelar
        if (!order.getUser().getEmail().equals(userEmail)) {
            throw new UnauthorizedAccessException("No tienes permisos para cancelar este pedido");
        }

        // Regla: CONFIRMED no se puede cancelar
        if (order.getStatus() == OrderStatus.CONFIRMED) {
            throw new InvalidOrderStateException("No se puede cancelar un pedido confirmado");
        }

        order.setStatus(OrderStatus.CANCELLED);
        return orderMapper.toResponse(orderRepository.save(order));
    }

}
