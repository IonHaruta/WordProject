package com.example.demo.exceptions;

public class WordNotFoundException extends RuntimeException{
    public WordNotFoundException(String message) {
        super(message);
    }
}
