package com.devsu.account_service.service.impl;

import com.devsu.account_service.dto.request.AccountRequest;
import com.devsu.account_service.dto.response.AccountResponse;
import com.devsu.account_service.entity.Account;
import com.devsu.account_service.entity.enums.AccountStatus;
import com.devsu.account_service.exception.ResourceNotFoundException;
import com.devsu.account_service.mapper.AccountMapper;
import com.devsu.account_service.repository.AccountRepository;
import com.devsu.account_service.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountResponse create(AccountRequest request) {

        Account account = AccountMapper.toEntity(request);
        Account saved = accountRepository.save(account);

        return AccountMapper.toResponse(saved);
    }

    @Override
    public AccountResponse getById(UUID id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account not found: " + id)
                );

        return AccountMapper.toResponse(account);
    }

    @Override
    public List<AccountResponse> getAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountMapper::toResponse)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        if (!accountRepository.existsById(id)) {
            throw new ResourceNotFoundException("Account not found: " + id);
        }
        accountRepository.deleteById(id);
    }

    @Override
    public void handleClientCreated(UUID clientId) {

        // regla simple: crear una cuenta inicial
        Account account = new Account();
        account.setClientId(clientId);
        account.setInitialBalance(BigDecimal.ZERO);
        account.setStatus(AccountStatus.ACTIVE);

        accountRepository.save(account);
    }

    @Override
    public void handleClientDeleted(UUID clientId) {

        List<Account> accounts = accountRepository.findByClientId(clientId);

        for (Account account : accounts) {
            account.setStatus(AccountStatus.INACTIVE);
        }

        accountRepository.saveAll(accounts);
    }
}

