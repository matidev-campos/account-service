package com.devsu.account_service.dto.response;

import com.devsu.account_service.entity.enums.AccountType;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountResponse(
        UUID id,
        String accountNumber,
        AccountType type,
        BigDecimal balance,
        UUID clientId
) {}

