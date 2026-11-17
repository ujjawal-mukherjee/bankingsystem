package com.banking.bank.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransferRequest(

        @NotNull Long senderAccountId,

        @NotNull Long receiverAccountId,

        @NotNull @DecimalMin("0.01") BigDecimal amount) {
}