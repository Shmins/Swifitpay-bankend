package com.bank.approve.usecase.account;

import java.util.List;
import java.util.UUID;

import com.bank.approve.domain.components.Account;

public interface AccountService {
    Account createAccount(Account account);
    void insertMany(Iterable<Account> account);
    void deleteById(UUID id);
    Account getAccountById(UUID id);
    List<Account> getAll();
    Account updateAccount(Account account);
}
