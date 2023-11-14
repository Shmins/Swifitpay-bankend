package com.bank.approve.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.approve.domain.approves.ApproveAccount;
import com.bank.approve.domain.components.Account;
import com.bank.approve.domain.entity.Client;
import com.bank.approve.usecase.account.AccountDto;
import com.bank.approve.usecase.account.AccountService;
import com.bank.approve.usecase.approve.ApproveAccountService;
import com.bank.approve.usecase.client.ClientService;

@RestController
@RequestMapping("approve/v1/account")
public class ApproveAccountController {

    @Autowired
    private ApproveAccountService approveAccountService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> saveApprove(@RequestBody AccountDto data) {
        try {
            Account account = new Account(
                null,
                    data.getTypeAccount(),
                    null,
                    data.getCpf(),
                    null,
                    LocalDateTime.now(),
                    LocalDateTime.now());
            Account accountCreated = this.accountService.createAccount(account);

            ApproveAccount approve = new ApproveAccount(
                    accountCreated,
                    data.getCpf(),
                    false,
                    false,
                    LocalDateTime.now(),
                    LocalDateTime.now());
            this.approveAccountService.createApprove(approve);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.valueOf(500));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADM') or hasRole('ROLE_BOSS')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            ApproveAccount approve = this.approveAccountService.getApproveById(id);

            return new ResponseEntity<>(approve, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));

        }
    }

    @GetMapping(value = "/getAll")
    @PreAuthorize("hasRole('ROLE_ADM') or hasRole('ROLE_BOSS')")
    public ResponseEntity<?> getApproveBorrowingAll() {
        try {
            return new ResponseEntity<>(this.approveAccountService.getAll(), HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> updateById(@PathVariable("id") Long id, @RequestBody ApproveAccount data) {
        try {
            ApproveAccount approve = this.approveAccountService.getApproveById(id);

            approve.setAccountId(data.getAccountId() != null ? data.getAccountId() : approve.getAccountId());
            approve.setCpfCreatedReq(
                    data.getCpfCreatedReq() != null ? data.getCpfCreatedReq() : approve.getCpfCreatedReq());

            approve.setIsApproved(data.getIsApproved() != null ? data.getIsApproved() : approve.getIsApproved());
            approve.setIsRefused(data.getIsRefused() != null ? data.getIsRefused() : approve.getIsRefused());

            approve.setUpdateAt(LocalDateTime.now());

            ApproveAccount update = this.approveAccountService.updateApprove(approve);

            return new ResponseEntity<>(update, HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @PutMapping(value = "/{decision}/{id}")
    public ResponseEntity<?> approveAccount(@PathVariable("decision") Boolean decision, @PathVariable("id") Long id) {
        try {
            ApproveAccount approve = this.approveAccountService.getApproveById(id);

            Account account = approve.getAccountId();

            Client client = this.clientService.getClientById(approve.getCpfCreatedReq());

            List<AccountDto> accounts = client.getAccount();

            AccountDto accountDto = new AccountDto(
                    account.getId(),
                    new ArrayList<>(),
                    account.getTypeAccount(),
                    null,
                    account.getCpf(),
                    LocalDateTime.now(),
                    LocalDateTime.now());

            accounts.add(accountDto);

            client.setAccount(accounts);

            Client update = this.clientService.updateClient(client);

            if (update != null) {

                approve.setIsApproved(true);
                this.approveAccountService.updateApprove(approve);

            }
            return new ResponseEntity<>(update, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.valueOf(500));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADM') or hasRole('ROLE_BOSS')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        try {
            this.approveAccountService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }
}
