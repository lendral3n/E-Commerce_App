package com.l3n.ecommerceapp.ecommmerce_app.repository;

import com.l3n.ecommerceapp.ecommmerce_app.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    
}
