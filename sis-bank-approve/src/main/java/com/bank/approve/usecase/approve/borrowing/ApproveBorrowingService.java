package com.bank.approve.usecase.approve.borrowing;

import java.util.List;


import com.bank.approve.domain.approves.ApproveBorrowing;

public interface ApproveBorrowingService {
    ApproveBorrowing createApprove(ApproveBorrowing approve);
    void insertMany(Iterable<ApproveBorrowing> approve);
    void deleteById(Long id);
    ApproveBorrowing getApproveById(Long id);
    List<ApproveBorrowing> getAll();
    ApproveBorrowing updateApprove(ApproveBorrowing approve);
}