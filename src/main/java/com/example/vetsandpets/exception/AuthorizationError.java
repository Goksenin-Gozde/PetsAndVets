package com.example.vetsandpets.exception;

public class AuthorizationError extends RuntimeException{
    public AuthorizationError(String message) {
        super(message);
    }
}