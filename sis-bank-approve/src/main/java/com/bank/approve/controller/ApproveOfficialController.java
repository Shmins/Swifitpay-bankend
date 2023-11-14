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

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.approve.domain.approves.ApproveOfficial;
import com.bank.approve.domain.entity.Official;

import jakarta.annotation.security.RolesAllowed;

import com.bank.approve.usecase.approve.ApproveOfficialService;
import com.bank.approve.usecase.official.OfficialService;

@RestController
@RequestMapping("approve/v1/official")
public class ApproveOfficialController {

    @Autowired
    private ApproveOfficialService approveOfficialService;

    @Autowired
    private OfficialService officialService;

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> saveApprove(@RequestBody String cpf) {
        try {
            ApproveOfficial approve = new ApproveOfficial(
                    cpf,
                    false,
                    false,
                    LocalDateTime.now(),
                    LocalDateTime.now());
            this.approveOfficialService.createApprove(approve);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.valueOf(500));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADM') or hasRole('ROLE_BOSS')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            ApproveOfficial clients = this.approveOfficialService.getApproveById(id);

            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));

        }
    }

    @GetMapping(value = "/getAll")
    @PreAuthorize("hasRole('ROLE_ADM') or hasRole('ROLE_BOSS')")
    public ResponseEntity<?> getApproveBorrowingAll() {
        try {
            List<ApproveOfficial> result = this.approveOfficialService.getAll();
            return new ResponseEntity<>(result, HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> updateById(@PathVariable("id") Long id, @RequestBody ApproveOfficial data) {
        try {
            ApproveOfficial approve = this.approveOfficialService.getApproveById(id);

            approve.setCpf(
                    data.getCpf() != null ? data.getCpf() : approve.getCpf());

            approve.setIsApproved(data.getIsApproved() != null ? data.getIsApproved() : approve.getIsApproved());
            approve.setIsRefused(data.getIsRefused() != null ? data.getIsRefused() : approve.getIsRefused());

            approve.setUpdateAt(LocalDateTime.now());

            ApproveOfficial update = this.approveOfficialService.updateApprove(approve);

            return new ResponseEntity<>(update, HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @RolesAllowed("BOSS")
    @PutMapping(value = "/{decision}/{id}")
    public ResponseEntity<?> approveOfficial(@PathVariable("decision") Boolean isApproved,
            @PathVariable("id") Long id) {
        ApproveOfficial approve = this.approveOfficialService.getApproveById(id);
        try {
            if (Boolean.TRUE.equals(isApproved)) {
                Official official = this.officialService.getOfficialById(approve.getCpf());
                official.setIsAuthorized(true);

                this.officialService.updateOfficial(official);
                approve.setIsApproved(true);
                this.approveOfficialService.updateApprove(approve);
            } else {
                approve.setIsApproved(false);
                this.approveOfficialService.updateApprove(approve);
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
            this.approveOfficialService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }
}
