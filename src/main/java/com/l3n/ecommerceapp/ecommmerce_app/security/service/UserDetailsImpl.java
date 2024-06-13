package com.l3n.ecommerceapp.ecommmerce_app.security.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.l3n.ecommerceapp.ecommmerce_app.entity.User;

import lombok.Data;

@Data
public class UserDetailsImpl implements UserDetails {

    private String username;
    private String email;
    private String name;
    @JsonIgnore
    private String password;

    public UserDetailsImpl(String username,
            String email,
            String name,
            String password) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }


    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
