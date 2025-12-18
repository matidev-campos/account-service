package com.devsu.account_service.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record MovementResponse(
        UUID id,
        UUID accountId,
        BigDecimal amount,
        BigDecimal balance,
        LocalDateTime date
) {}

