package com.banking.bank.dto;

import jakarta.validation.constraints.NotBlank;

public record AccountRequest(
        @NotBlank String accountType,
        Long userId) {

}
