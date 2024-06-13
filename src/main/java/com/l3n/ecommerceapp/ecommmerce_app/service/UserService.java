package com.l3n.ecommerceapp.ecommmerce_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.l3n.ecommerceapp.ecommmerce_app.entity.User;
import com.l3n.ecommerceapp.ecommmerce_app.exception.BadRequestException;
import com.l3n.ecommerceapp.ecommmerce_app.exception.ResourceNotFoundException;
import com.l3n.ecommerceapp.ecommmerce_app.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User dengan id " + id + "tidak diteumukan"));
    }

    public User create(User user) {
        if (!StringUtils.hasText(user.getId())) {
            throw new BadRequestException("Username harus diisi");
        }

        if (userRepository.existsById(user.getId())) {
            throw new BadRequestException("Username " + user.getId() + " sudah terdaftar");
        }

        if (!StringUtils.hasText(user.getEmail())) {
            throw new BadRequestException("Email harus diisi");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email " + user.getEmail() + " sudah terdaftar");
        }

        return userRepository.save(user);
    }

    public User update(User user) {
        if (!StringUtils.hasText(user.getId())) {
            throw new BadRequestException("Username harus diisi");
        }

        if (!StringUtils.hasText(user.getEmail())) {
            throw new BadRequestException("Email harus diisi");
        }

                return userRepository.save(user);
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
