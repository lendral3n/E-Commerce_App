package com.l3n.ecommerceapp.ecommmerce_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.l3n.ecommerceapp.ecommmerce_app.entity.User;
import com.l3n.ecommerceapp.ecommmerce_app.model.WebResponse;
import com.l3n.ecommerceapp.ecommmerce_app.service.UserService;

@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/profile")
    public WebResponse<User> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userService.findById(username);
        return WebResponse.<User>builder()
                .message("Profil ditemukan")
                .data(user)
                .build();
    }

    @PutMapping("/users/profile")
    public WebResponse<User> updateProfile(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        user.setId(username); 
        User updatedUser = userService.update(user);
        return WebResponse.<User>builder()
                .message("Data pengguna berhasil diperbarui")
                .data(updatedUser)
                .build();
    }

    @DeleteMapping("/users/profile")
    public WebResponse<String> deleteProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        userService.deleteById(username);
        return WebResponse.<String>builder()
                .message("Pengguna berhasil dihapus")
                .build();
    }
}
