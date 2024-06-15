package com.l3n.ecommerceapp.ecommmerce_app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private String photoProduct;
    private String category;
    private BigDecimal price;
    private Double stock;
}
