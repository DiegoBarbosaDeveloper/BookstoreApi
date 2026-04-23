package ustavillavicencio.edu.co.bookstore.mapper;

import org.springframework.stereotype.Component;

import jakarta.persistence.criteria.Order;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ustavillavicencio.edu.co.bookstore.dto.response.OrderResponse;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    public OrderResponse toResponse(Order order) {
        OrderResponse dto = new OrderResponse();
        dto.setId(order.getId());
        dto.setStatus(order.getStatus().name());
        dto.setTotal(order.getTotal());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setItems(order.getItems().stream()
                .map(orderItemMapper::toResponse)
                .toList());
        return dto;
    }

}
