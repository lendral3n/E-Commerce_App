package com.l3n.ecommerceapp.ecommmerce_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.l3n.ecommerceapp.ecommmerce_app.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String>{
}
