package com.devsu.account_service.service;

import com.devsu.account_service.dto.request.MovementRequest;
import com.devsu.account_service.dto.response.MovementResponse;

import java.util.List;
import java.util.UUID;

public interface MovementService {

    MovementResponse create(MovementRequest request);

    List<MovementResponse> getByAccountId(UUID accountId);
}

