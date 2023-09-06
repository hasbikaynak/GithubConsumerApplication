package com.example.githubconsumerapplication.exception;

import org.springframework.http.HttpStatus;

public class UnsupportedMediaTypeException extends RuntimeException{
    public UnsupportedMediaTypeException(String message, HttpStatus conflict) {
        super(message);
    }
}
