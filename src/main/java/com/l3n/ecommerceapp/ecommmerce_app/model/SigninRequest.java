package com.l3n.ecommerceapp.ecommmerce_app.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class SigninRequest implements Serializable {
    private String username;
    private String password;
}
