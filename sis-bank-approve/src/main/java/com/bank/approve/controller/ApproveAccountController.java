package com.bank.approve.controller;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bank.approve.domain.approves.ApproveAccount;
import com.bank.approve.domain.components.NumberAgency;
import com.bank.approve.domain.components.Account;
import com.bank.approve.usecase.approve.ApproveAccountService;

@RestController
@RequestMapping("approve/v1/account")
public class ApproveAccountController {

    @Autowired
    private ApproveAccountService approveAccountService;

    @Autowired
    @Value("${java.hostusers}")
    private String host;

    @PostMapping(value = "/borrowing", produces = "application/json")
    public ResponseEntity<?> saveApprove(@RequestBody ApproveAccount data) {
        try {
            var client = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            ApproveAccount approve = new ApproveAccount(
                    data.getAccountId(),
                    client.getUsername());
            ApproveAccount result = this.approveAccountService.createApprove(approve);

            return new ResponseEntity<>(result, HttpStatus.OK);

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
            List<ApproveAccount> result = this.approveAccountService.getAll();
            return new ResponseEntity<>(result, HttpStatus.valueOf(200));
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

    @PutMapping(value = "/account/{decision}/{id}")
    @PreAuthorize("hasRole('ROLE_ADM') or hasRole('ROLE_BOSS')")
    public ResponseEntity<?> approveAccount(@PathVariable("decision") Boolean decision,
            @PathVariable("id") Long id, @RequestBody NumberAgency data) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            if (Boolean.TRUE.equals(decision) && id != null) {
                ApproveAccount approve = this.approveAccountService.getApproveById(id);
/* 
                Account account = new Account(approve.getAccount().getTypeAccount(), data, approve.getCpfCreatedReq());
                restTemplate.put("http://localhost:8080/approve/v1/account/true", account); */

            }else{
                restTemplate.put("http://localhost:8080/approve/v1/account/false", null);
            }
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
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
