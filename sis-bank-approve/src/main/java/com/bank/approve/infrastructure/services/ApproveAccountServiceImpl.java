package com.bank.approve.infrastructure.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.approve.domain.approves.ApproveAccount;
import com.bank.approve.domain.approves.repository.ApproveAccountRepository;
import com.bank.approve.usecase.approve.ApproveAccountService;

@Service
public class ApproveAccountServiceImpl implements ApproveAccountService {
    @Autowired
    private ApproveAccountRepository approveRepository;

    @Override
    public ApproveAccount createApprove(ApproveAccount approve) {
        return this.approveRepository.save(approve);
    }

    @Override
    public void insertMany(Iterable<ApproveAccount> approve) {
        this.approveRepository.saveAll(approve);
    }

    @Override
    public void deleteById(Long id) {
        this.approveRepository.deleteById(id);
    }

    @Override
    public ApproveAccount getApproveById(Long id) {
        Optional<ApproveAccount> borrowing = this.approveRepository.findById(id);
        return borrowing.isPresent() ? borrowing.get() : null;
    }

    @Override
    public List<ApproveAccount> getAll() {
        return this.approveRepository.findAll();
    }

    @Override
    public ApproveAccount updateApprove(ApproveAccount approve) {
        return approveRepository.save(approve);
    }
}
