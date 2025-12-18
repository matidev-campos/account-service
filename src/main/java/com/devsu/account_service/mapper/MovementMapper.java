package com.devsu.account_service.mapper;

import com.devsu.account_service.dto.response.MovementResponse;
import com.devsu.account_service.entity.Movement;

public final class MovementMapper {

    private MovementMapper() {}

    public static MovementResponse toResponse(Movement movement) {
        return new MovementResponse(
                movement.getId(),
                movement.getAccount().getId(),
                movement.getAmount(),
                movement.getBalanceAfter(),
                movement.getDate()
        );
    }
}

