package com.banking.bank.controller;

import com.banking.bank.entity.Benificiary;
import com.banking.bank.service.BenificiaryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiaries")
@CrossOrigin(origins = "http://localhost:4200")

public class BenificiaryContoller {
    private final BenificiaryService service;
    public BenificiaryContoller(BenificiaryService service){
        this.service=service;
    }
    @PostMapping("/{userId}")
    public ResponseEntity<Benificiary> add(
            @PathVariable Long userId,
            @RequestBody Benificiary beneficiary) {

        return ResponseEntity.ok(service.add(beneficiary, userId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Benificiary>> getAll(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                service.getAll(userId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.ok(
                "Beneficiary deleted"
        );
    }

}
