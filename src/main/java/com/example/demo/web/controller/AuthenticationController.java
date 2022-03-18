package com.example.demo.web.controller;


import com.example.demo.exceptions.AuthenticationFailedException;
import com.example.demo.exceptions.DuplicateEmailException;
import com.example.demo.service.AuthenticationService;
import com.example.demo.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        LoginDto register;
        try {
            register = authenticationService.register(request);
        } catch (DuplicateEmailException e) {
            ErrorDto errorDto = new ErrorDto(e.getMessage(), ErrorCode.DUPLICATE_EMAIL);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(register);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LogInRequest logInRequest){
        LoginDto loginDto;
        try{
            loginDto = authenticationService.logIn(logInRequest);
        }catch(AuthenticationFailedException e){
            ErrorDto errorDto = new ErrorDto(e.getMessage(), ErrorCode.AUTHENTICATION_ERROR);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(loginDto);
    }
}
