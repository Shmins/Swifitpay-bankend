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
@Table(name = "approve_cards")
public class ApproveCards extends Approve{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "approve_cards_id")
    private Long id;

    private String numberCard;

    private String cpfCreatedReq;


    public ApproveCards(String card, String cpfCreatedReq) {
        super();
        this.numberCard = card;
        this.cpfCreatedReq = cpfCreatedReq;
    }
}
