package com.l3n.ecommerceapp.ecommmerce_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/users/{id}")
    public WebResponse<User> findById(@PathVariable("id") String id) {
        User user = userService.findById(id);
        return WebResponse.<User>builder()
                .message("Profil ditemukan")
                .data(user)
                .build();
    }

    @PutMapping("/users/{id}")
    public WebResponse<User> update(@RequestBody User user, @PathVariable("id") String id) {
        User updatedUser = userService.update(user);
        return WebResponse.<User>builder()
                .message("Data pengguna berhasil diperbarui")
                .data(updatedUser)
                .build();
    }

    @DeleteMapping("/users/{id}")
    public WebResponse<String> deleteById(@PathVariable("id") String id) {
        userService.deleteById(id);
        return WebResponse.<String>builder()
                .message("Pengguna berhasil dihapus")
                .build();
    }
}
