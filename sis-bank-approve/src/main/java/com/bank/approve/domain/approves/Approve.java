package com.bank.approve.domain.approves;

import java.time.LocalDateTime;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Approve{

    private Boolean isApproved;

    private Boolean isRefused;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;
}
