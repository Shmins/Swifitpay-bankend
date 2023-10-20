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

import com.bank.approve.domain.approves.ApproveCards;
import com.bank.approve.usecase.approve.ApproveCardsService;

@RestController
@RequestMapping("approve/v1/cards")
public class ApproveCardsController {

    @Autowired
    private ApproveCardsService approveCardsService;

    @Autowired
    @Value("${java.hostusers}")
    private String host;

    @PostMapping(value = "/borrowing", produces = "application/json")
    public ResponseEntity<?> saveApprove(@RequestBody ApproveCards data) {
        try {
            var client = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            ApproveCards approve = new ApproveCards(
                    data.getNumberCard(),
                    client.getUsername());
            ApproveCards result = this.approveCardsService.createApprove(approve);

            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.valueOf(500));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADM') or hasRole('ROLE_BOSS')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            ApproveCards clients = this.approveCardsService.getApproveById(id);

            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));

        }
    }

    @GetMapping(value = "/getAll")
    @PreAuthorize("hasRole('ROLE_ADM') or hasRole('ROLE_BOSS')")
    public ResponseEntity<?> getApproveBorrowingAll() {
        try {
            List<ApproveCards> result = this.approveCardsService.getAll();
            return new ResponseEntity<>(result, HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> updateById(@PathVariable("id") Long id, @RequestBody ApproveCards data) {
        try {
            ApproveCards approve = this.approveCardsService.getApproveById(id);

            approve.setNumberCard(data.getNumberCard() != null ? data.getNumberCard() : approve.getNumberCard());
            approve.setCpfCreatedReq(
                    data.getCpfCreatedReq() != null ? data.getCpfCreatedReq() : approve.getCpfCreatedReq());

            approve.setIsApproved(data.getIsApproved() != null ? data.getIsApproved() : approve.getIsApproved());
            approve.setIsRefused(data.getIsRefused() != null ? data.getIsRefused() : approve.getIsRefused());

            approve.setUpdateAt(LocalDateTime.now());

            ApproveCards update = this.approveCardsService.updateApprove(approve);

            return new ResponseEntity<>(update, HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @PutMapping(value = "/cards/{decision}/{id}")
    @PreAuthorize("hasRole('ROLE_ADM') or hasRole('ROLE_BOSS')")
    public ResponseEntity<?> approveCards(@PathVariable("decision") Boolean isApproved, @PathVariable("id") Long id) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ApproveCards approve = this.approveCardsService.getApproveById(id);

            if (Boolean.TRUE.equals(isApproved)) {
                /* Card card = approve.getCard();
                card.setActive(true);
                restTemplate.put(String.format("http:%s/approve/v1/cards/true/", host), card); */

                approve.setIsApproved(true);
                this.approveCardsService.updateApprove(approve);

            } else {
                approve.setIsRefused(true);
                this.approveCardsService.updateApprove(approve);
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
            this.approveCardsService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }
}
