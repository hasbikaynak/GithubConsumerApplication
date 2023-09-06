package com.example.githubconsumerapplication.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message, HttpStatus status) {
        super(message);
    }
}
