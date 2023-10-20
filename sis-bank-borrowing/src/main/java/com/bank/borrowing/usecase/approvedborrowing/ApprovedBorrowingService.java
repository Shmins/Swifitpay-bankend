package com.bank.borrowing.usecase.approvedborrowing;

import java.util.List;


import com.bank.borrowing.domain.borrowing.ApprovedBorrowing;

public interface ApprovedBorrowingService {
    ApprovedBorrowing createApprovedBorrowing(ApprovedBorrowing data);
    ApprovedBorrowing getById(Long id);
    List<ApprovedBorrowing> getAll();
    void updateApprovedBorrowing(ApprovedBorrowing data);
    void deleteById(Long id);
}
