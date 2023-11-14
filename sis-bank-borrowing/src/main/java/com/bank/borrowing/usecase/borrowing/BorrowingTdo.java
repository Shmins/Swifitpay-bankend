package com.bank.borrowing.usecase.borrowing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingTdo {
    private Long borrowingId;
    private String approvedBy;
}