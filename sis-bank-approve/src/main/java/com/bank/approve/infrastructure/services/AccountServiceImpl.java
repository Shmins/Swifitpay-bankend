package com.bank.approve.infrastructure.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.approve.domain.components.Account;
import com.bank.approve.domain.components.repository.AccountRepository;
import com.bank.approve.usecase.account.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        return this.accountRepository.save(account);
    }

    @Override
    public void insertMany(Iterable<Account> account) {
        this.accountRepository.saveAll(account);
    }

    @Override
    public void deleteById(UUID id) {
        this.accountRepository.deleteById(id);
    }

    @Override
    public Account getAccountById(UUID id) {
        Optional<Account> borrowing = this.accountRepository.findById(id);
        return borrowing.isPresent() ? borrowing.get() : null;
    }

    @Override
    public List<Account> getAll() {
        return this.accountRepository.findAll();
    }

    @Override
    public Account updateAccount(Account account) {
        return this.accountRepository.save(account);
    }

}
