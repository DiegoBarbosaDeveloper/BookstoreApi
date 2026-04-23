package ustavillavicencio.edu.co.bookstore.mapper;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import ustavillavicencio.edu.co.bookstore.dto.response.OrderItemResponse;
import ustavillavicencio.edu.co.bookstore.entity.OrderItem;

@Component

public class OrderItemMapper {

    public OrderItemResponse toResponse(OrderItem item) {
        OrderItemResponse dto = new OrderItemResponse();
        dto.setBookId(item.getBook().getId());
        dto.setBookTitle(item.getBook().getTitle());
        dto.setQuantity(item.getQuantity());
        dto.setSubtotal(item.getSubtotal());
        return dto;
    }

}
