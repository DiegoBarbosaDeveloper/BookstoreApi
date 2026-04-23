package ustavillavicencio.edu.co.bookstore.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

public class OrderRequest {
    
    @NotEmpty(message = "El pedido debe tener al menos un ítem")
    private List<OrderItemRequest> items;

}
