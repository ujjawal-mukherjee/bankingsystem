package com.banking.bank.controller;

import com.banking.bank.entity.Account;
import com.banking.bank.entity.Transaction;
import com.banking.bank.entity.User;
import com.banking.bank.service.AdminService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<User>> customers() {
        return ResponseEntity.ok(
                service.getCustomers()
        );
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> accounts() {
        return ResponseEntity.ok(
                service.getAccounts()
        );
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> transactions() {
        return ResponseEntity.ok(
                service.getTransactions()
        );
    }

    @PatchMapping("/accounts/{id}/block")
    public ResponseEntity<Account> block(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.block(id)
        );
    }

    @PatchMapping("/accounts/{id}/unblock")
    public ResponseEntity<Account> unblock(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.unblock(id)
        );
    }

    @PatchMapping("/accounts/{id}/approve")
    public ResponseEntity<Account> approve(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.approve(id)
        );
    }

    @PatchMapping("/accounts/{id}/reject")
    public ResponseEntity<Account> reject(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.reject(id)
        );
    }
}