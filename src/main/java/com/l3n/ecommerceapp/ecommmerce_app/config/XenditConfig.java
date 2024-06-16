package com.l3n.ecommerceapp.ecommmerce_app.config;

import com.xendit.Xendit;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XenditConfig {

    @Value("${xendit.api.key}")
    private String xenditApiKey;

    @PostConstruct
    public void init() {
        Xendit.apiKey = xenditApiKey;
    }
}
