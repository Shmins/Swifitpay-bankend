package com.bank.approve.domain.components;

import java.time.LocalDateTime;
import java.util.UUID;

import com.bank.approve.domain.approves.ApproveAccount;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String typeAccount;

    @ManyToOne
    private NumberAgency numberAgency;

    private String cpf;

    @OneToOne(mappedBy = "accountId")
    private ApproveAccount approveAccount;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
