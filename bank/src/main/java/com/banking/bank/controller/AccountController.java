package com.banking.bank.controller;

import com.banking.bank.dto.AccountRequest;
import com.banking.bank.entity.Account;
import com.banking.bank.service.AccountService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:4200")

public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@Valid @RequestBody AccountRequest request) {
        return ResponseEntity.ok(accountService.createAccount(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                accountService.getAccount(id));

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Account>> getUserAccounts(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.getUserAccounts(userId));
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<?> getBalance(
            @PathVariable Long id) {

        Account account = accountService.getAccount(id);

        return ResponseEntity.ok(
                account.getBalance());
    }
}
