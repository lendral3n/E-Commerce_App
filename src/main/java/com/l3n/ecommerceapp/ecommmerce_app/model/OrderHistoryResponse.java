package com.l3n.ecommerceapp.ecommmerce_app.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderHistoryResponse {
    private Long orderId;
    private String address;
    private Date dateOrder;
    private BigDecimal total;
    private List<OrderItemResponse> items;
    private String paymentStatus;
    private String bank;
}
