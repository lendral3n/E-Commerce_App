package com.l3n.ecommerceapp.ecommmerce_app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private String id;
    private String status;
    private String bankCode;
    private String currency;
    private Double amount;
    private String transactionTime;
    private String accountNumber;
}