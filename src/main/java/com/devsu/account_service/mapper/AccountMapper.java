package com.devsu.account_service.mapper;

import com.devsu.account_service.dto.request.AccountRequest;
import com.devsu.account_service.dto.response.AccountResponse;
import com.devsu.account_service.entity.Account;

public final class AccountMapper {

    private AccountMapper() {}

    public static Account toEntity(AccountRequest request) {
        Account account = new Account();
        account.setAccountNumber(request.accountNumber());
        account.setAccountType(request.type());
        account.setInitialBalance(request.initialBalance());
        account.setClientId(request.clientId());
        return account;
    }

    public static AccountResponse toResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getAccountType(),
                account.getInitialBalance(),
                account.getClientId()
        );
    }
}

