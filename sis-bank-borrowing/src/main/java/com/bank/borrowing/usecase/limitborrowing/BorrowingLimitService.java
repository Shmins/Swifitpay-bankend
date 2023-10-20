package com.bank.borrowing.usecase.limitborrowing;

import java.util.List;


import com.bank.borrowing.domain.borrowing.BorrowingLimit;

public interface BorrowingLimitService {
    BorrowingLimit createBorrowingLimit(BorrowingLimit data);
    BorrowingLimit getById(Long id);
    List<BorrowingLimit> getAll();
    void updateBorrowingLimit(BorrowingLimit data);
    void deleteById(Long id);
}
