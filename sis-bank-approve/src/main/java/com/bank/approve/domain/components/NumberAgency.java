package com.bank.approve.domain.components;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "number_agency")
public class NumberAgency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numberAgency_id")
    private Long id;

    private String number;

    private String nameAgency;

    @OneToMany(mappedBy = "numberAgency",cascade = CascadeType.ALL)
    private Set<Account> account;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public NumberAgency(String number, String nameAgency){
        this.nameAgency = nameAgency;
        this.number = number;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }
}
