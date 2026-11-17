package com.banking.bank.controller;

import com.banking.bank.dto.MoneyRequest;
import com.banking.bank.dto.TransferRequest;
import com.banking.bank.entity.Transaction;
import com.banking.bank.service.TransactionService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(
            TransactionService transactionService) {

        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(
            @Valid @RequestBody MoneyRequest request) {

        return ResponseEntity.ok(
                transactionService.deposit(request)
        );
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(
            @Valid @RequestBody MoneyRequest request) {

        return ResponseEntity.ok(
                transactionService.withdraw(request)
        );
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(
            @Valid @RequestBody TransferRequest request) {

        transactionService.transfer(request);

        return ResponseEntity.ok(
                "Transfer successful"
        );
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactions(
            @PathVariable Long accountId) {

        return ResponseEntity.ok(
                transactionService
                        .getTransactions(accountId)
        );
    }
}