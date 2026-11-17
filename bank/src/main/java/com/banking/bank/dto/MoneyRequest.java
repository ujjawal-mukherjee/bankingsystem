package com.banking.bank.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record MoneyRequest(
        @NotNull Long accountId,
        @NotNull @DecimalMin("0.01") BigDecimal amount) {

}
