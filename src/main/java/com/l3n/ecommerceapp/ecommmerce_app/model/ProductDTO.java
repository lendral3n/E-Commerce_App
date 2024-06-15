package com.l3n.ecommerceapp.ecommmerce_app.model;

import java.math.BigDecimal;

public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String photoProduct;
    private String category;
    private BigDecimal price;
    private Double stock;
    private UserDTO user;

    // Constructor
    public ProductDTO(Long id, String name, String description, String photoProduct, String category, BigDecimal price, Double stock, UserDTO user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photoProduct = photoProduct;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getPhotoProduct() { return photoProduct; }
    public String getCategory() { return category; }
    public BigDecimal getPrice() { return price; }
    public Double getStock() { return stock; }
    public UserDTO getUser() { return user; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPhotoProduct(String photoProduct) { this.photoProduct = photoProduct; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setStock(Double stock) { this.stock = stock; }
    public void setUser(UserDTO user) { this.user = user; }
}
