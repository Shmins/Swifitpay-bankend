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
@Table(name = "approve_official")
public class ApproveOfficial extends Approve{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approve_official_id")
    private Long id;

    private String cpf;

    public ApproveOfficial(String cpf){
        super();
        this.cpf = cpf;
    }
}
