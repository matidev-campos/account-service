package com.devsu.account_service.service;

import com.devsu.account_service.dto.request.AccountRequest;
import com.devsu.account_service.dto.response.AccountResponse;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    AccountResponse create(AccountRequest request);

    AccountResponse getById(UUID id);

    List<AccountResponse> getAll();

    void delete(UUID id);

    // async events
    void handleClientCreated(UUID clientId);

    void handleClientDeleted(UUID clientId);
}

