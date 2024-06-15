package com.l3n.ecommerceapp.ecommmerce_app.repository;

import com.l3n.ecommerceapp.ecommmerce_app.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(String userId);
    Optional<Cart> findByUserIdAndProductId(String userId, Long productId);
}
