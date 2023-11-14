package com.bank.approve.usecase.approve.borrowing;

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