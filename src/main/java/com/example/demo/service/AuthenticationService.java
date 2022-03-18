package com.example.demo.service;

import com.example.demo.exceptions.AuthenticationFailedException;
import com.example.demo.exceptions.DuplicateEmailException;
import com.example.demo.web.dto.LogInRequest;
import com.example.demo.web.dto.LoginDto;
import com.example.demo.web.dto.RegisterRequest;

public interface AuthenticationService {
    LoginDto register(RegisterRequest request) throws DuplicateEmailException;

    LoginDto logIn(LogInRequest logInRequest) throws AuthenticationFailedException;
}
