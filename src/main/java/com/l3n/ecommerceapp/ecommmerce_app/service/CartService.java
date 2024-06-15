package com.l3n.ecommerceapp.ecommmerce_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.l3n.ecommerceapp.ecommmerce_app.entity.Cart;
import com.l3n.ecommerceapp.ecommmerce_app.entity.Product;
import com.l3n.ecommerceapp.ecommmerce_app.entity.User;
import com.l3n.ecommerceapp.ecommmerce_app.exception.BadRequestException;
import com.l3n.ecommerceapp.ecommmerce_app.model.CartItemDTO;
import com.l3n.ecommerceapp.ecommmerce_app.model.ProductDTO;
import com.l3n.ecommerceapp.ecommmerce_app.repository.CartRepository;
import com.l3n.ecommerceapp.ecommmerce_app.repository.ProductRepository;
import com.l3n.ecommerceapp.ecommmerce_app.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Transactional
    public Cart addCart(String username, Long productId, Double quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BadRequestException("Product ID " + productId + " not found"));
        User user = userRepository.findById(username)
                .orElseThrow(() -> new BadRequestException("User " + username + " not found"));

        Optional<Cart> optional = cartRepository.findByUserIdAndProductId(user.getId(), product.getId());
        Cart cart;
        if (optional.isPresent()) {
            cart = optional.get();
            cart.setQuantity(cart.getQuantity() + quantity);
            cart.setTotal(cart.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())));
            cartRepository.save(cart);
        } else {
            cart = new Cart();
            cart.setProduct(product);
            cart.setUser(user);
            cart.setQuantity(quantity);
            cart.setPrice(product.getPrice());
            cart.setTotal(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
            cartRepository.save(cart);
        }

        return cart;
    }

    @Transactional
    public Cart updateCartQuantity(String username, Long productId, Double quantity) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new BadRequestException("User " + username + " not found"));
        Cart cart = cartRepository.findByUserIdAndProductId(user.getId(), productId)
                .orElseThrow(() -> new BadRequestException("Product ID " + productId + " not found in your cart"));

        cart.setQuantity(quantity);
        cart.setTotal(cart.getPrice().multiply(BigDecimal.valueOf(quantity)));
        cartRepository.save(cart);
        return cart;
    }

    @Transactional
    public void deleteCartItem(String username, Long productId) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new BadRequestException("User " + username + " not found"));
        Cart cart = cartRepository.findByUserIdAndProductId(user.getId(), productId)
                .orElseThrow(() -> new BadRequestException("Product ID " + productId + " not found in your cart"));

        cartRepository.delete(cart);
    }

    public List<Cart> getCartItems(String username) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new BadRequestException("User " + username + " not found"));
        return cartRepository.findByUserId(user.getId());
    }

    public ProductDTO getProductDTO(Product product) {
        return productService.convertToProductDTO(product);
    }

    public CartItemDTO convertToCartItemDTO(Cart cart) {
        ProductDTO productDTO = productService.convertToProductDTO(cart.getProduct());
        return new CartItemDTO(cart.getId(), cart.getQuantity(), productDTO);
    }
}
