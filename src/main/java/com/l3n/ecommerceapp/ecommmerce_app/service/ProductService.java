package com.l3n.ecommerceapp.ecommmerce_app.service;

import com.l3n.ecommerceapp.ecommmerce_app.entity.Product;
import com.l3n.ecommerceapp.ecommmerce_app.entity.User;
import com.l3n.ecommerceapp.ecommmerce_app.exception.ResourceNotFoundException;

import com.l3n.ecommerceapp.ecommmerce_app.model.ProductDTO;
import com.l3n.ecommerceapp.ecommmerce_app.model.UserDTO;
import com.l3n.ecommerceapp.ecommmerce_app.repository.ProductRepository;
import com.l3n.ecommerceapp.ecommmerce_app.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ProductDTO> getAllProductDTOs() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductDTOById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produk dengan id " + id + " tidak ditemukan"));
        return convertToProductDTO(product);
    }

    public List<ProductDTO> getProductsByUserId(String userId) {
        List<Product> products = productRepository.findByUserId(userId);
        return products.stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToProductDTO(Product product) {
        UserDTO userDTO = new UserDTO(product.getUser().getName());
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPhotoProduct(),
                product.getCategory(),
                product.getPrice(),
                product.getStock(),
                userDTO
        );
    }

    public ProductDTO createProductDTO(ProductDTO productDTO, String userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User dengan id " + userId + " tidak ditemukan"));

    Product product = new Product();
    product.setName(productDTO.getName());
    product.setDescription(productDTO.getDescription());
    product.setPhotoProduct(productDTO.getPhotoProduct());
    product.setCategory(productDTO.getCategory());
    product.setPrice(productDTO.getPrice());
    product.setStock(productDTO.getStock());
    product.setUser(user);

    Product savedProduct = productRepository.save(product);
    return convertToProductDTO(savedProduct);
}


    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produk dengan id " + id + " tidak ditemukan"));
        
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPhotoProduct(productDTO.getPhotoProduct());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        
        Product updatedProduct = productRepository.save(product);
        return convertToProductDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produk dengan id " + id + " tidak ditemukan"));
        productRepository.delete(product);
    }
}
