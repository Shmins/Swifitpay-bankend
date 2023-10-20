package com.bank.approve.infrastructure.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.approve.domain.approves.ApproveBorrowing;
import com.bank.approve.domain.approves.repository.ApproveBorrowingRepository;
import com.bank.approve.usecase.approve.borrowing.ApproveBorrowingService;

@Service
public class ApproveBorrowingServiceImpl implements ApproveBorrowingService {
    @Autowired
    private ApproveBorrowingRepository approveRepository;

    @Override
    public ApproveBorrowing createApprove(ApproveBorrowing approve) {
        return this.approveRepository.save(approve);
    }

    @Override
    public void insertMany(Iterable<ApproveBorrowing> approve) {
        this.approveRepository.saveAll(approve);
    }

    @Override
    public void deleteById(Long id) {
        this.approveRepository.deleteById(id);
    }

    @Override
    public ApproveBorrowing getApproveById(Long id) {
        Optional<ApproveBorrowing> borrowing = this.approveRepository.findById(id);
        return borrowing.isPresent() ? borrowing.get() : null;
    }

    @Override
    public List<ApproveBorrowing> getAll() {
        return this.approveRepository.findAll();
    }

    @Override
    public ApproveBorrowing updateApprove(ApproveBorrowing approve) {
        return approveRepository.save(approve);
    }
}
