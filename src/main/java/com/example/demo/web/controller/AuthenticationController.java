package com.example.demo.web.controller;


import com.example.demo.service.AuthenticationService;
import com.example.demo.web.dto.LoginDto;
import com.example.demo.web.dto.RegisterRequest;
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
        LoginDto register = authenticationService.register(request);
        return ResponseEntity.status(HttpStatus.OK).body(register);
    }
}
