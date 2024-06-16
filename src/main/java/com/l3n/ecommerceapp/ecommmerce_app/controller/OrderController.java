package com.l3n.ecommerceapp.ecommmerce_app.controller;

import com.l3n.ecommerceapp.ecommmerce_app.model.CreateOrderRequest;
import com.l3n.ecommerceapp.ecommmerce_app.model.CreateOrderResponse;
import com.l3n.ecommerceapp.ecommmerce_app.model.OrderHistoryResponse;
import com.l3n.ecommerceapp.ecommmerce_app.model.WebResponse;
import com.l3n.ecommerceapp.ecommmerce_app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public WebResponse<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request, Authentication authentication) {
        String userId = authentication.getName();
        CreateOrderResponse response = orderService.createOrder(request, userId);
        return WebResponse.<CreateOrderResponse>builder()
                .message("Order berhasil dibuat")
                .data(response)
                .build();
    }

    @GetMapping("/history")
    public WebResponse<List<OrderHistoryResponse>> getHistoryOrder(Authentication authentication) {
        String userId = authentication.getName();
        List<OrderHistoryResponse> history = orderService.getHistoryOrder(userId);
        return WebResponse.<List<OrderHistoryResponse>>builder()
                .message("History order berhasil diambil")
                .data(history)
                .build();
    }
}
