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
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "approve_borrowing")
public class ApproveBorrowing extends Approve{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approve_borrowing_id")
    private Long id;

    private Long borrowingId;

    private String cpfCreatedReq;

    public ApproveBorrowing(Long borrowingId, String cpfCreatedReq, Boolean isApproved, Boolean isRefused, LocalDateTime createAt, LocalDateTime updateAt) {
        super(isApproved, isRefused, createAt, updateAt);
        this.borrowingId = borrowingId;
        this.cpfCreatedReq = cpfCreatedReq;
    }
}
