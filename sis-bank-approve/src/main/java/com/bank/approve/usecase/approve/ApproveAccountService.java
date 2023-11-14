package com.bank.approve.usecase.approve;

import java.util.List;

import com.bank.approve.domain.approves.ApproveAccount;

public interface ApproveAccountService {
    ApproveAccount createApprove(ApproveAccount approve);
    void insertMany(Iterable<ApproveAccount> approve);
    void deleteById(Long id);
    ApproveAccount getApproveById(Long id);
    List<ApproveAccount> getAll();
    ApproveAccount updateApprove(ApproveAccount approve);
}