package ustavillavicencio.edu.co.bookstore.mapper;

import org.springframework.stereotype.Component;

import ustavillavicencio.edu.co.bookstore.dto.response.OrderItemResponse;
import ustavillavicencio.edu.co.bookstore.entity.OrderItemEntity;

@Component

public class OrderItemMapper {

    public OrderItemResponse toResponse(OrderItemEntity item) {
        OrderItemResponse dto = new OrderItemResponse();
        dto.setBookId(item.getBook().getId());
        dto.setBookTitle(item.getBook().getTitle());
        dto.setQuantity(item.getQuantity());
        dto.setSubtotal(item.getSubtotal());
        return dto;
    }

}
