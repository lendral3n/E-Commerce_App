package com.l3n.ecommerceapp.ecommmerce_app.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponse {
    private Long productId;
    private String productName;
    private Double quantity;
    private BigDecimal price;
    private BigDecimal total;
}
