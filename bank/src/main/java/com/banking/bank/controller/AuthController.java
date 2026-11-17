package com.banking.bank.controller;

import com.banking.bank.dto.LoginRequest;
import com.banking.bank.dto.RegisterRequest;
import com.banking.bank.entity.User;
import com.banking.bank.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.register(request);
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(
            @Valid @RequestBody LoginRequest request) {

        User user = authService.login(request);

        user.setPassword(null);

        return ResponseEntity.ok(user);
    }
}
