package com.example.demo.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SecurityConfigTest {

    @Autowired
    private PasswordEncoder myPasswordEncoder;

    @Test
    void passwordEncoder() {
        String encode = myPasswordEncoder.encode("123");
        System.out.println(encode);
        boolean matches = myPasswordEncoder.matches("123", encode);
        System.out.println(matches);
    }
}