package com.bank.approve.domain.approves;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "approve_official")
public class ApproveOfficial extends Approve{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approve_official_id")
    private Long id;

    private String cpf;

    public ApproveOfficial(String cpf, Boolean isApproved, Boolean isRefused, LocalDateTime createAt, LocalDateTime updateAt) {
        super(isApproved, isRefused, createAt, updateAt);
        this.cpf = cpf;
    }
}
