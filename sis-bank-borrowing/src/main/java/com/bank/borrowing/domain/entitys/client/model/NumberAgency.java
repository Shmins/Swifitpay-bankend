package com.bank.borrowing.domain.entitys.client.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberAgency {
    @Id
    private String id;

    private String number;

    private String nameAgency;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public NumberAgency(String number, String nameAgency){
        this.nameAgency = nameAgency;
        this.number = number;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }
}
