package com.l3n.ecommerceapp.ecommmerce_app.repository;

import com.l3n.ecommerceapp.ecommmerce_app.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByOrderId(Long id);
    
}
