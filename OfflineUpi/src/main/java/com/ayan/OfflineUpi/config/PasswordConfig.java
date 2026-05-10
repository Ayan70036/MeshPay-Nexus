package com.ayan.OfflineUpi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Configuration for password encoding
@Configuration
public class PasswordConfig {

    // Provides BCrypt encoder for passwords
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}