package com.bank.borrowing.domain.borrowing;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
public class Borrowing implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String cpf; // Cpf of the user who requested the loan

    private int quantity; // Quantity -> 30000 = R$ 300,00

    private Boolean isApproved = false;

    private Boolean isRefused = false;

    private String approvedOrRefusedBy = null;

    private LocalDateTime createdAt;
    
    private LocalDateTime updateAt;


}
