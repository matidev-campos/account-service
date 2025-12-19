package com.devsu.account_service.dto.request;

import com.devsu.account_service.entity.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountRequest(

        @NotBlank
        String accountNumber,

        @NotNull
        AccountType type,

        @NotNull
        BigDecimal initialBalance,

        @NotNull
        UUID clientId

) {}

