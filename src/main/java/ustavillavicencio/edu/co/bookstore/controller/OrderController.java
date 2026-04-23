package ustavillavicencio.edu.co.bookstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ustavillavicencio.edu.co.bookstore.dto.request.OrderRequest;
import ustavillavicencio.edu.co.bookstore.dto.response.ApiResponse;
import ustavillavicencio.edu.co.bookstore.dto.response.OrderResponse;
import ustavillavicencio.edu.co.bookstore.service.OrderService;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor

public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(
            @Valid @RequestBody OrderRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {

        OrderResponse body = orderService.createOrder(request, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.created(body, "Pedido creado exitosamente"));
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getMyOrders(
            @AuthenticationPrincipal UserDetails userDetails) {

        List<OrderResponse> body = orderService.getMyOrders(userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success(body, "Tus pedidos"));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getAllOrders() {

        List<OrderResponse> body = orderService.getAllOrders();
        return ResponseEntity.ok(ApiResponse.success(body, "Todos los pedidos"));
    }

    @PatchMapping("/{id}/cancel")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<OrderResponse>> cancelOrder(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        OrderResponse body = orderService.cancelOrder(id, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success(body, "Pedido cancelado"));
    }

}
