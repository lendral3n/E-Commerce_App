package com.l3n.ecommerceapp.ecommmerce_app.controller;

import com.l3n.ecommerceapp.ecommmerce_app.model.*;
import com.l3n.ecommerceapp.ecommmerce_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    //inject
    @Autowired
    private UserService userService;

    @PostMapping(
            path = "/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> add(@RequestBody UserRequest request){
        userService.Create(request);
        return WebResponse.<String>builder()
                .message("success add data")
                .build();
    }


    @PutMapping(
            path = "/users/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> update(
            @RequestBody UpdateUserRequest request,
            @PathVariable("userId") String userId
    ){
        // ambil dari url param dan set ke var request
        request.setId(userId);

        UserResponse userResponse = userService.update(request);
        return WebResponse.<UserResponse>builder()
                .message("success update data")
                .data(userResponse)
                .build();
    }

    @DeleteMapping(
            path = "/users/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(
            @PathVariable("userId") String userId
    ){
        userService.delete(userId);
        return WebResponse.<String>builder()
                .message("success delete data")
                .build();
    }

    @GetMapping(
            path = "/users/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> getById(
            @PathVariable("userId") String userId
    ){
        UserResponse userResponse = userService.getById(userId);
        return WebResponse.<UserResponse>builder()
                .message("success get data")
                .data(userResponse)
                .build();
    }
}