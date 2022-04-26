package com.example.cityweb.exception;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String message) {
        super (message);
    }
}