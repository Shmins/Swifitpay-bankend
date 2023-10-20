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
@Table(name = "borrowingLimit")
public class BorrowingLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    private int limitMin; // Quantity -> 30000 = R$ 300,00

    private int limitMax; // Quantity -> 30000 = R$ 300,00

    private LocalDateTime createdAt;
    
    private LocalDateTime updateAt;

    public BorrowingLimit(String cpf, int limitMin, int limitMax) {
        this.cpf = cpf;
        this.limitMin = limitMin;
        this.limitMax = limitMax;
        this.updateAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }
}
