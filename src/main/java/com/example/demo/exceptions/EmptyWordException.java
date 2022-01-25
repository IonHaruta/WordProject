package com.example.demo.exceptions;

public class EmptyWordException extends RuntimeException {
    public EmptyWordException(String message){
        super(message);
    }
}
