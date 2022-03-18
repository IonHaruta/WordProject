package com.example.demo.web.dto;

public class ErrorDto {
    private String message;
    private ErrorCode description;

    public ErrorDto(String message, ErrorCode description) {
        this.message = message;
        this.description = description;
    }
}
