package com.banking.bank.service;

import com.banking.bank.dto.LoginRequest;
import com.banking.bank.dto.RegisterRequest;
import com.banking.bank.entity.User;
import com.banking.bank.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new RuntimeException("Username Already Exists");
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email Already Exists");
        }
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setUsername(request.username());
        //
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole("CUSTOMER");
        return userRepository.save(user);
    }

    public User login(LoginRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        return user;
    }
}
