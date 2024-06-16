package com.l3n.ecommerceapp.ecommmerce_app.model;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private List<Long> cartId;
    private String address;
    private String bank;
}
