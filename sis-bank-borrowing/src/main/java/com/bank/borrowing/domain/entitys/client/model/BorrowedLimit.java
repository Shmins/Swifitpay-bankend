package com.bank.borrowing.domain.entitys.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
public class BorrowedLimit {
    private int minLimit;
    private int maxLimit;
}
