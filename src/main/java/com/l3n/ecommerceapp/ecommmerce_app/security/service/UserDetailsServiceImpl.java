package com.l3n.ecommerceapp.ecommmerce_app.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.l3n.ecommerceapp.ecommmerce_app.entity.User;
import com.l3n.ecommerceapp.ecommmerce_app.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " tidak ditemukan"));
        return UserDetailsImpl.build(user);

    }

}
                                                                           