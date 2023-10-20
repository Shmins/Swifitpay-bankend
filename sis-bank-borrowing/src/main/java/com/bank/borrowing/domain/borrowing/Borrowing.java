package com.bank.borrowing.domain.borrowing;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "borrowings")
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf; // Cpf of the user who requested the loan

    private int quantity; // Quantity -> 30000 = R$ 300,00

    private Boolean isSendToApprove = false;

    private Boolean isAuthorized = false;

    private Boolean isRefused = false;

    private LocalDateTime createdAt;
    
    private LocalDateTime updateAt;

    public Borrowing(String cpf, int quantity) {
        this.cpf = cpf;
        this.quantity = quantity;
        this.updateAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }
}
