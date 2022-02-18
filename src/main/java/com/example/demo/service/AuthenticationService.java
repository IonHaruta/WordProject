package com.example.demo.service;

import com.example.demo.exceptions.DuplicateEmailException;
import com.example.demo.web.dto.LoginDto;
import com.example.demo.web.dto.RegisterRequest;

public interface AuthenticationService {
    LoginDto register(RegisterRequest request);
}
