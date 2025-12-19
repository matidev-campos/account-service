package com.devsu.account_service.controller;

import com.devsu.account_service.dto.request.MovementRequest;
import com.devsu.account_service.dto.response.MovementResponse;
import com.devsu.account_service.service.MovementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/movements")
public class MovementController {

    private final MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }

    @PostMapping
    public ResponseEntity<MovementResponse> create(
            @Valid @RequestBody MovementRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movementService.create(request));
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<MovementResponse>> getByAccount(
            @PathVariable UUID accountId
    ) {
        return ResponseEntity.ok(
                movementService.getByAccountId(accountId)
        );
    }
}

