package com.l3n.ecommerceapp.ecommmerce_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.l3n.ecommerceapp.ecommmerce_app.model.WebResponse;
import com.l3n.ecommerceapp.ecommmerce_app.model.UserProfileResponse;
import com.l3n.ecommerceapp.ecommmerce_app.service.UserService;

@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/profile")
    public WebResponse<UserProfileResponse> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        UserProfileResponse userProfile = userService.findProfileById(username);
        return WebResponse.<UserProfileResponse>builder()
                .message("Profil ditemukan")
                .data(userProfile)
                .build();
    }

    @PutMapping("/users/profile")
    public WebResponse<UserProfileResponse> updateProfile(@RequestBody UserProfileResponse userProfileRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        return WebResponse.<UserProfileResponse>builder()
                .message("Data pengguna berhasil diperbarui")
                .data(userProfileRequest)
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
