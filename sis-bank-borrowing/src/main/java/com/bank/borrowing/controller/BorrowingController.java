package com.bank.borrowing.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.borrowing.domain.borrowing.Borrowing;
import com.bank.borrowing.domain.entity.client.model.Client;
import com.bank.borrowing.usecase.approve.ApproveService;
import com.bank.borrowing.usecase.borrowing.BorrowingService;
import com.bank.borrowing.usecase.borrowing.BorrowingTdo;

@RestController
@RequestMapping("borrowing/v1")
public class BorrowingController {
    @Autowired
    private BorrowingService borrowingService;
    @Autowired
    private ApproveService approveService;
    
    @Autowired
    @Value("${java.hostapprove}")
    private String host;

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> saveBorrowing(@RequestBody Borrowing data) {
        try {
            Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
/* 
            List<Borrowing> borrowings = this.borrowingService.getAllByCpf(client.getCpf());
            for(Borrowing i : borrowings){
                if(Boolean.FALSE.equals(i.getIsAuthorized()) && Boolean.FALSE.equals(i.getIsRefused())){
                    throw new IllegalArgumentException("Análise de empréstimo em progresso");
                }
            }
            if(data.getQuantity() > client.getBorrowedLimit().getMaxLimit()){
                    throw new IllegalArgumentException("Valor fora do limite do estabelecido");
            } */
            Borrowing result = this.borrowingService.createBorrowing(new Borrowing(client.getCpf(), data.getQuantity()));
            BorrowingTdo borrowingApprove = new BorrowingTdo(result.getId(), client.getCpf());
            approveService.sendToApprove(borrowingApprove);

            return new ResponseEntity<>(HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
    }

    @GetMapping(value = "/getAll")
    @PreAuthorize("hasRole('ROLE_ADM') or hasRole('ROLE_BOSS') or hasRole('ROLE_OFFICIAL')")
    public ResponseEntity<?> getBorrowingAll() {
        try {
            List<Borrowing> result = this.borrowingService.getAll();

            
            return new ResponseEntity<>(result, HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> getBorrowingAllForClient() {
        try {
            Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<Borrowing> result = this.borrowingService.getAllByCpf(client.getCpf());
            return new ResponseEntity<>(result, HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
    }
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADM') or hasRole('ROLE_BOSS') or hasRole('ROLE_OFFICIAL')")
    public ResponseEntity<?> getBorrowing(@PathVariable("id") Long id) {
        try {
            Borrowing result = this.borrowingService.getById(id);
            return new ResponseEntity<>(result, HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADM') or hasRole('ROLE_BOSS')")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> updateBorrowingById(@PathVariable("id") Long id, @RequestBody Borrowing data) {
        try {
            Borrowing borrowing = this.borrowingService.getById(id);

            borrowing.setQuantity(
                    data.getQuantity() != borrowing.getQuantity() ? data.getQuantity() : borrowing.getQuantity());
            borrowing.setIsAuthorized(
                    data.getIsAuthorized() != null ? data.getIsAuthorized() : borrowing.getIsAuthorized());
            borrowing.setIsRefused(data.getIsRefused() != null ? data.getIsRefused() : borrowing.getIsRefused());
            borrowing.setUpdateAt(LocalDateTime.now());

            this.borrowingService.updateBorrowing(borrowing);

            return new ResponseEntity<>(HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADM') or hasRole('ROLE_BOSS')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteBorrowingById(@PathVariable("id") Long id) {
        try {
            this.borrowingService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }
}
