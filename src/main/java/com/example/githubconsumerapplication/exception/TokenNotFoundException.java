package com.example.githubconsumerapplication.exception;

import org.springframework.http.HttpStatus;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException(String message, HttpStatus badRequest) {
        super(message);
    }
}
