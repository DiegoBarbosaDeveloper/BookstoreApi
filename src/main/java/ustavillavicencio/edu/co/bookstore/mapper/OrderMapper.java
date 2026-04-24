package ustavillavicencio.edu.co.bookstore.mapper;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ustavillavicencio.edu.co.bookstore.dto.response.OrderResponse;
import ustavillavicencio.edu.co.bookstore.entity.OrderEntity;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    public OrderResponse toResponse(OrderEntity order) {
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
