package com.example.demo.service;

import antlr.Token;
import com.example.demo.exceptions.DuplicateEmailException;
import com.example.demo.repo.UserRepository;
import com.example.demo.repo.WordRepository;
import com.example.demo.web.dto.LoginDto;
import com.example.demo.web.dto.RegisterRequest;
import com.example.demo.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImplement implements AuthenticationService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    @Override
    public LoginDto register(RegisterRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()){
            throw new DuplicateEmailException("This email is taken!");
        }
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .email(request.getEmail())
                .password(hashedPassword).build();
        User save = userRepository.save(user);
        String token = tokenService.createToken(save);
        LoginDto loginDto = new LoginDto(save.getEmail(),token);



        return loginDto;
    }
}
