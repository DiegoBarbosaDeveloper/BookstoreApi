package ustavillavicencio.edu.co.bookstore.service;

import java.util.List;

import ustavillavicencio.edu.co.bookstore.dto.request.OrderRequest;
import ustavillavicencio.edu.co.bookstore.dto.response.OrderResponse;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request, String userEmail);
    List<OrderResponse> getMyOrders(String userEmail);
    List<OrderResponse> getAllOrders();
    OrderResponse cancelOrder(Long orderId, String userEmail);

}
