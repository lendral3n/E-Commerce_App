package com.l3n.ecommerceapp.ecommmerce_app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartRequest {
    private Long productId;
    private Double quantity;
}
