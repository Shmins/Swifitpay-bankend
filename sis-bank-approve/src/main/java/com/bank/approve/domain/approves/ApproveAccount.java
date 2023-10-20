package com.bank.approve.domain.approves;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


import com.bank.approve.domain.components.Account;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "approve_account")
public class ApproveAccount extends Approve{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approve_account_id")
    private Long id;

    @OneToOne(targetEntity = Account.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_account_id")
    private String accountId;

    private String cpfCreatedReq;

    public ApproveAccount(String accountId, String cpfCreatedReq){
        super();
        this.accountId = accountId;
        this.cpfCreatedReq = cpfCreatedReq;
    }
}
