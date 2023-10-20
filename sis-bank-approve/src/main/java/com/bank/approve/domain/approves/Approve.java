package com.bank.approve.domain.approves;

import java.time.LocalDateTime;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class Approve {

    private Boolean isApproved;

    private Boolean isRefused;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    public Approve(){
        this.isApproved = false;
        this.isRefused = false;
        this.createdAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }
}
