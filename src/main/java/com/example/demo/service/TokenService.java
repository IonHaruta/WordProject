package com.example.demo.service;



import com.example.demo.web.entity.User;

import java.util.Map;

public interface TokenService {
    Map<String, String> getUserDataFromToken(String token);

    String createToken(User user);

}