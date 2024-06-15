package com.l3n.ecommerceapp.ecommmerce_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.l3n.ecommerceapp.ecommmerce_app.entity.Cart;
import com.l3n.ecommerceapp.ecommmerce_app.model.CreateCartRequest;
import com.l3n.ecommerceapp.ecommmerce_app.model.UpdateCartRequest;
import com.l3n.ecommerceapp.ecommmerce_app.model.WebResponse;
import com.l3n.ecommerceapp.ecommmerce_app.service.CartService;
import com.l3n.ecommerceapp.ecommmerce_app.model.CartItemDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
@PreAuthorize("isAuthenticated()")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public WebResponse<CartItemDTO> createCart(@RequestBody CreateCartRequest createCartRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        Cart cart = cartService.addCart(username, createCartRequest.getProductId(), createCartRequest.getQuantity());
        CartItemDTO cartItemDTO = cartService.convertToCartItemDTO(cart);
        return WebResponse.<CartItemDTO>builder()
                .message("Berhasil menambahkan produk ke keranjang")
                .data(cartItemDTO)
                .build();
    }

    @PutMapping
    public WebResponse<CartItemDTO> updateCartQuantity(@RequestBody UpdateCartRequest updateCartRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        Cart cart = cartService.updateCartQuantity(username, updateCartRequest.getProductId(), updateCartRequest.getQuantity());
        CartItemDTO cartItemDTO = cartService.convertToCartItemDTO(cart);
        return WebResponse.<CartItemDTO>builder()
                .message("Berhasil mengperbaharui kuantitas produk")
                .data(cartItemDTO)
                .build();
    }

    @DeleteMapping
    public WebResponse<String> deleteCart(@RequestParam Long productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        cartService.deleteCartItem(username, productId);
        return WebResponse.<String>builder()
                .message("Berhasil menghapus produk di keranjang")
                .build();
    }

    @GetMapping
    public WebResponse<List<CartItemDTO>> getCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        List<Cart> cartItems = cartService.getCartItems(username);
        List<CartItemDTO> cartItemDTOs = cartItems.stream()
                .map(cartService::convertToCartItemDTO)
                .collect(Collectors.toList());
        return WebResponse.<List<CartItemDTO>>builder()
                .message("Berhasil Mendapatkan produk di keranjang")
                .data(cartItemDTOs)
                .build();
    }
}
