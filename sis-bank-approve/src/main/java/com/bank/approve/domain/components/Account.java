package com.bank.approve.domain.components;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    private String typeAccount;

    @ManyToOne(targetEntity = Account.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_numberAgency_id")
    private NumberAgency numberAgency;

    private String cpf;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public Account(String typeAccount, NumberAgency numberAgency, String cpf){
        if(!typeAccount.equals("chain") && !typeAccount.equals("savings")){
            throw new IllegalArgumentException("Tipo de conta inválido");
        }
        this.numberAgency = numberAgency;
        this.cpf = cpf;
        this.typeAccount = typeAccount;

        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }
}
