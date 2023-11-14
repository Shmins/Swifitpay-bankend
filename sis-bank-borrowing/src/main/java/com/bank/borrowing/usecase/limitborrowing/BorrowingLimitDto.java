package com.bank.borrowing.usecase.limitborrowing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingLimitDto {
    private int minLimit;
    private int maxLimit;

}
