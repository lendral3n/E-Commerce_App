package com.l3n.ecommerceapp.ecommmerce_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.l3n.ecommerceapp.ecommmerce_app.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, String>{
    
}
