package com.bank.borrowing.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.borrowing.domain.borrowing.BorrowingLimit;
import com.bank.borrowing.domain.entitys.client.model.Client;
import com.bank.borrowing.usecase.limitborrowing.BorrowingLimitDto;
import com.bank.borrowing.usecase.limitborrowing.BorrowingLimitService;

import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping("borrowing/v1/limit")
public class BorrowingLimitController {
    @Autowired
    private BorrowingLimitService borrowingLimitService;

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> saveBorrowingLimit(@RequestBody BorrowingLimitDto data, @RequestHeader("Authorization") String token) {
        try {
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            BorrowingLimit borrowingLimit = new BorrowingLimit(user.getUsername(), data.getMinLimit(), data.getMaxLimit());
            this.borrowingLimitService.createBorrowingLimit(borrowingLimit);


            return new ResponseEntity<>(HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.valueOf(500));
        }
    }

    @GetMapping(value = "/getAll")
    @PreAuthorize("hasRole('ROLE_ADM') or hasRole('ROLE_BOSS') or hasRole('ROLE_OFFICIAL')")
    public ResponseEntity<?> getBorrowingLimitAll() {
        try {
            List<BorrowingLimit> result = this.borrowingLimitService.getAll();

            return new ResponseEntity<>(result, HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.valueOf(500));
        }
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> getBorrowingLimitOfClient() {
        try {
            Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            BorrowingLimit result = this.borrowingLimitService.getByCpf(client.getCpf());
            return new ResponseEntity<>(result, HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADM') or hasRole('ROLE_BOSS') or hasRole('ROLE_OFFICIAL')")
    public ResponseEntity<?> getBorrowingLimit(@PathVariable("id") Long id) {
        try {
            BorrowingLimit result = this.borrowingLimitService.getById(id);
            return new ResponseEntity<>(result, HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
    }

    @RolesAllowed("BOSS")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> updateBorrowingLimitById(@PathVariable("id") Long id, @RequestBody BorrowingLimit data) {
        try {
            BorrowingLimit borrowingLimit = this.borrowingLimitService.getById(id);

            borrowingLimit.setLimitMax(
                    data.getLimitMax() != borrowingLimit.getLimitMax() ? data.getLimitMax() : borrowingLimit.getLimitMax());
            borrowingLimit.setLimitMin(
                    data.getLimitMin() != borrowingLimit.getLimitMin() ? data.getLimitMin() : borrowingLimit.getLimitMin());
            borrowingLimit.setUpdateAt(LocalDateTime.now());

            this.borrowingLimitService.updateBorrowingLimit(borrowingLimit);

            return new ResponseEntity<>(HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }
    @PreAuthorize("hasRole('ROLE_ADM') or hasRole('ROLE_BOSS')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteBorrowingLimitById(@PathVariable("id") Long id) {
        try {
            this.borrowingLimitService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }
}
