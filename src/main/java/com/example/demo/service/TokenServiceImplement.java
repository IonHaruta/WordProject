package com.example.demo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenServiceImplement implements TokenService {

    @Override
    public String createToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("plmokn");
            return JWT.create()
                    .withClaim("username", user.getEmail())
                    .withClaim("userId", user.getId().toString())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, String> getUserDataFromToken(String rawToken) {
        String token = rawToken;
        if (rawToken.startsWith("Bearer ")) {
            token = rawToken.substring(7);
        }
        Map<String, String> result = new HashMap<>();
        try {
            Algorithm algorithm = Algorithm.HMAC256("plmokn");
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            result.put("email", jwt.getClaim("username").asString());
            result.put("id", jwt.getClaim("userId").asString());
            return result;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return null;
        }
    }

}
