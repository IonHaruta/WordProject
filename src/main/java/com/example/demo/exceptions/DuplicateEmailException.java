package com.example.demo.exceptions;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String s) {
        super(s);
    }
}
