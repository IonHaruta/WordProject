package com.example.demo.service;

import antlr.Token;
import com.example.demo.exceptions.AuthenticationFailedException;
import com.example.demo.exceptions.DuplicateEmailException;
import com.example.demo.repo.UserRepository;
import com.example.demo.repo.WordRepository;
import com.example.demo.web.dto.LogInRequest;
import com.example.demo.web.dto.LoginDto;
import com.example.demo.web.dto.RegisterRequest;
import com.example.demo.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImplement implements AuthenticationService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    @Override
    public LoginDto register(RegisterRequest request) throws DuplicateEmailException{
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

    @Override
    public LoginDto logIn(LogInRequest logInRequest) throws AuthenticationFailedException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(logInRequest.getEmail(),logInRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if(authentication.isAuthenticated()){
            User user = (User) authentication.getPrincipal();
            return new LoginDto(user.getEmail(), tokenService.createToken(user));
        }else{
            throw new AuthenticationFailedException("Could not authenticate!");
        }

    }
}
