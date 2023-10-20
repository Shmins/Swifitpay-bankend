package com.bank.borrowing.domain.borrowing;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "approvedBorrowing")
public class ApprovedBorrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String approvedBy;

    @OneToOne(targetEntity = Borrowing.class)
    private Long borrowingId;

    public ApprovedBorrowing(String approvedBy, Long borrowingId){
        this.approvedBy = approvedBy;
        this.borrowingId = borrowingId;
    }
}
