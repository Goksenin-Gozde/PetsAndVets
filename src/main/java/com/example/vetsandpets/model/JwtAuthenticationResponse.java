package com.example.vetsandpets.model;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}

