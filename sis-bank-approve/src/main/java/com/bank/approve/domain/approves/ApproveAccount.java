package com.bank.approve.domain.approves;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;

import com.bank.approve.domain.components.Account;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "approve_account")
public class ApproveAccount extends Approve{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approve_account_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account accountId;

    private String cpfCreatedReq;

    public ApproveAccount(Account accountId, String cpfCreatedReq, Boolean isApproved, Boolean isRefused, LocalDateTime createAt, LocalDateTime updateAt) {
        super(isApproved, isRefused, createAt, updateAt);
        this.accountId = accountId;
        this.cpfCreatedReq = cpfCreatedReq;
    }
}
