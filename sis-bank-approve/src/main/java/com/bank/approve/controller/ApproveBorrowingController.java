package com.bank.approve.controller;

import java.time.LocalDateTime;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.approve.domain.approves.ApproveBorrowing;
import com.bank.approve.usecase.approve.borrowing.ApproveBorrowingService;
import com.bank.approve.usecase.approve.borrowing.BorrowingService;
import com.bank.approve.usecase.approve.borrowing.BorrowingTdo;


@RestController
@RequestMapping("approve/v1/borrowing")
public class ApproveBorrowingController {
    @Autowired
    private ApproveBorrowingService approveBorrowingService;
    private final BorrowingService borrowingService;

    public ApproveBorrowingController(ApproveBorrowingService approveBorrowingService, BorrowingService borrowingService){
        this.approveBorrowingService = approveBorrowingService;
        this.borrowingService = borrowingService;
    }
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> saveApprove(@RequestBody BorrowingTdo data) {
        try {
            ApproveBorrowing approve = new ApproveBorrowing(
                    data.getBorrowingId(),
                    data.getApprovedBy(),
                    false,
                    false,
                    LocalDateTime.now(),
                    LocalDateTime.now());
            this.approveBorrowingService.createApprove(approve); 
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.valueOf(500));
        }
    }
    @PreAuthorize("hasRole('ROLE_ADM') or hasRole('ROLE_BOSS')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            ApproveBorrowing clients = this.approveBorrowingService.getApproveById(id);

            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));

        }
    }

    @GetMapping(value = "/getAll")
    @PreAuthorize("hasRole('ROLE_ADM') or hasRole('ROLE_BOSS')")
    public ResponseEntity<?> getApproveBorrowingAll() {
        try {
            List<ApproveBorrowing> result = this.approveBorrowingService.getAll();
            return new ResponseEntity<>(result, HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
    }
   
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> updateById(@PathVariable("id") Long id, @RequestBody ApproveBorrowing data) {
        try {
            ApproveBorrowing approve = this.approveBorrowingService.getApproveById(id);

            approve.setBorrowingId(data.getBorrowingId() != null ? data.getBorrowingId() : approve.getBorrowingId());
            approve.setCpfCreatedReq(
                    data.getCpfCreatedReq() != null ? data.getCpfCreatedReq() : approve.getCpfCreatedReq());
            approve.setIsApproved(data.getIsApproved() != null ? data.getIsApproved() : approve.getIsApproved());
            approve.setIsRefused(data.getIsRefused() != null ? data.getIsRefused() : approve.getIsRefused());
            approve.setUpdateAt(LocalDateTime.now());

            ApproveBorrowing update = this.approveBorrowingService.updateApprove(approve);

            return new ResponseEntity<>(update, HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @PutMapping(value = "/{decision}/{id}")
    @PreAuthorize("hasRole('ROLE_ADM') or hasRole('ROLE_BOSS')")
    public ResponseEntity<?> approveBorrowing(@PathVariable("decision") Boolean isApproved,
            @PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
        try {
            ApproveBorrowing approve = this.approveBorrowingService.getApproveById(id);
            if (Boolean.TRUE.equals(isApproved)) {
                this.borrowingService.sendApprovedBorrowing(approve.getBorrowingId(), token);

                approve.setIsApproved(true);
                this.approveBorrowingService.updateApprove(approve);

            } else {
                this.borrowingService.sendRefusedBorrowing(approve.getBorrowingId(), token);
                approve.setIsRefused(true);
                this.approveBorrowingService.updateApprove(approve);

            }
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (

        Exception e) {
            return new ResponseEntity<>(e, HttpStatus.valueOf(500));

        }
    }

    @PreAuthorize("hasRole('ROLE_ADM') or hasRole('ROLE_BOSS')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        try {
            this.approveBorrowingService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }
}
