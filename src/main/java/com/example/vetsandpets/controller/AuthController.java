package com.example.vetsandpets.controller;

import com.example.vetsandpets.model.ApiResponse;
import com.example.vetsandpets.model.LoginRequest;
import com.example.vetsandpets.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ApiResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        boolean success = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (success) {
            return new ApiResponse(true, "Login successful");
        } else {
            return new ApiResponse(false, "Invalid credentials");
        }
    }
}
