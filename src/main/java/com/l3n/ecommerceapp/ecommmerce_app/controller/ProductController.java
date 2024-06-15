package com.l3n.ecommerceapp.ecommmerce_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.l3n.ecommerceapp.ecommmerce_app.entity.Product;
import com.l3n.ecommerceapp.ecommmerce_app.model.ProductDTO;
import com.l3n.ecommerceapp.ecommmerce_app.model.WebResponse;
import com.l3n.ecommerceapp.ecommmerce_app.service.CloudinaryService;
import com.l3n.ecommerceapp.ecommmerce_app.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@PreAuthorize("isAuthenticated()")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public WebResponse<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productDTOs = productService.getAllProductDTOs();
        return WebResponse.<List<ProductDTO>>builder()
                .message("Semua produk berhasil ditemukan")
                .data(productDTOs)
                .build();
    }

    @GetMapping("/{id}")
    public WebResponse<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO productDTO = productService.getProductDTOById(id);
        return WebResponse.<ProductDTO>builder()
                .message("Produk berhasil ditemukan")
                .data(productDTO)
                .build();
    }

    @GetMapping("/user")
    public WebResponse<List<ProductDTO>> getProductsByUser(Authentication authentication) {
        String userId = authentication.getName();
        List<ProductDTO> productDTOs = productService.getProductsByUserId(userId);
        return WebResponse.<List<ProductDTO>>builder()
                .message("Produk berdasarkan pengguna berhasil ditemukan")
                .data(productDTOs)
                .build();
    }

    @PostMapping
    public WebResponse<ProductDTO> createProduct(@ModelAttribute ProductDTO productDTO, @RequestParam("file") MultipartFile file, Authentication authentication) {
        String userId = authentication.getName();
        String photoUrl = cloudinaryService.uploadFile(file);
        productDTO.setPhotoProduct(photoUrl);
        ProductDTO createdProductDTO = productService.createProductDTO(productDTO, userId);
        return WebResponse.<ProductDTO>builder()
                .message("Produk berhasil ditambahkan")
                .data(createdProductDTO)
                .build();
    }

    @PutMapping("/{id}")
    public WebResponse<ProductDTO> updateProduct(@PathVariable Long id, @ModelAttribute ProductDTO productDTO, @RequestParam("file") MultipartFile file) {
        String photoUrl = cloudinaryService.uploadFile(file);
        productDTO.setPhotoProduct(photoUrl);
        ProductDTO updatedProductDTO = productService.updateProduct(id, productDTO);
        return WebResponse.<ProductDTO>builder()
                .message("Produk berhasil diperbarui")
                .data(updatedProductDTO)
                .build();
    }

    @DeleteMapping("/{id}")
    public WebResponse<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return WebResponse.<String>builder()
                .message("Produk berhasil dihapus")
                .build();
    }
}
