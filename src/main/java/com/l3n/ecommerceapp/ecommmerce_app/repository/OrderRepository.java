package com.l3n.ecommerceapp.ecommmerce_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.l3n.ecommerceapp.ecommmerce_app.entity.Order;

public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByUserId(String userId);
    
}
