package com.devsu.account_service.service.impl;

import com.devsu.account_service.dto.request.MovementRequest;
import com.devsu.account_service.dto.response.MovementResponse;
import com.devsu.account_service.entity.Account;
import com.devsu.account_service.entity.Movement;
import com.devsu.account_service.exception.ResourceNotFoundException;
import com.devsu.account_service.mapper.MovementMapper;
import com.devsu.account_service.repository.AccountRepository;
import com.devsu.account_service.repository.MovementRepository;
import com.devsu.account_service.service.MovementService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MovementServiceImpl implements MovementService {

    private final AccountRepository accountRepository;
    private final MovementRepository movementRepository;

    public MovementServiceImpl(
            AccountRepository accountRepository,
            MovementRepository movementRepository
    ) {
        this.accountRepository = accountRepository;
        this.movementRepository = movementRepository;
    }

    @Override
    public MovementResponse create(MovementRequest request) {

        Account account = accountRepository.findById(request.accountId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account not found")
                );

        BigDecimal newBalance = account.getCurrentBalance().add(request.amount());

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        account.setCurrentBalance(newBalance);

        Movement movement = new Movement();
        movement.setAccount(account);
        movement.setAmount(request.amount());
        movement.setBalanceAfter(newBalance);
        movement.setDate(LocalDateTime.now());

        movementRepository.save(movement);
        accountRepository.save(account);

        return MovementMapper.toResponse(movement);
    }

    @Override
    public List<MovementResponse> getByAccountId(UUID accountId) {

        if (!accountRepository.existsById(accountId)) {
            throw new ResourceNotFoundException("Account not found");
        }

        return movementRepository.findByAccountIdOrderByDateDesc(accountId)
                .stream()
                .map(MovementMapper::toResponse)
                .toList();
    }
}

