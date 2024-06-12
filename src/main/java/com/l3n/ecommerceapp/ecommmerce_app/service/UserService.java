package com.l3n.ecommerceapp.ecommmerce_app.service;

import com.l3n.ecommerceapp.ecommmerce_app.entity.User;
import com.l3n.ecommerceapp.ecommmerce_app.model.UpdateUserRequest;
import com.l3n.ecommerceapp.ecommmerce_app.model.UserRequest;
import com.l3n.ecommerceapp.ecommmerce_app.repository.UserRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    /**
     * method untuk mapping dari entity ke useresponse
     * @param user
     * @return
     */
    private UserResponse toUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    public void Create(UserRequest request){

        validationService.validate(request);

        // validation
        if (request.getEmail().equals("admin@admin.com")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email tidak bisa dipakai");
        }

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setName(request.getName());
        userRepository.save(user);
    }

    @Transactional
    public UserResponse update(UpdateUserRequest request){
        // cek id user ke db
        User user = userRepository.findById(request.getId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "user tidak ditemukan"));

        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setName(request.getName());

        userRepository.save(user);

        return toUserResponse(user);
    }

    @Transactional
    public void delete(String userId){
        // cek id user ke db
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "user tidak ditemukan"));

        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    public UserResponse getById(String userId){
        // baca data user by id user ke db
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "user tidak ditemukan"));

        return toUserResponse(user);
    }
}