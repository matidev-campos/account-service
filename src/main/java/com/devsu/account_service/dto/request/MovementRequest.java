package com.devsu.account_service.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record MovementRequest(

        @NotNull
        UUID accountId,

        @NotNull
        BigDecimal amount

) {}

