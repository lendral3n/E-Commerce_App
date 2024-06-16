package com.l3n.ecommerceapp.ecommmerce_app.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateOrderResponse {
    private Long orderId;
    private List<OrderItemResponse> items;
    private BigDecimal total;
    private String paymentStatus;
    private String vaNumber;
    private String bank;
}