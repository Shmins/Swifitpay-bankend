package com.bank.approve.domain.approves;



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
@Table(name = "approve_borrowing")
public class ApproveBorrowing extends Approve{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approve_borrowing_id")
    private Long id;

    private Long borrowingId;

    private String cpfCreatedReq;

    public ApproveBorrowing(Long borrowingId, String cpfCreatedReq){
        super();
        this.borrowingId = borrowingId;
        this.cpfCreatedReq = cpfCreatedReq;
    }
}
