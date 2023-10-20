package com.bank.borrowing.infrastructure.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.borrowing.domain.borrowing.ApprovedBorrowing;
import com.bank.borrowing.domain.repository.ApprovedBorrowingRepository;
import com.bank.borrowing.usecase.approvedborrowing.ApprovedBorrowingService;

@Service
public class ApprovedBorrowingServiceImpl implements ApprovedBorrowingService{
    @Autowired
    private ApprovedBorrowingRepository approedBorrowingRepository;
    @Override
    public ApprovedBorrowing createApprovedBorrowing(ApprovedBorrowing data) {
        return this.approedBorrowingRepository.save(data);
    }

    @Override
    public ApprovedBorrowing getById(Long id) {
        Optional<ApprovedBorrowing> data = this.approedBorrowingRepository.findById(id);
        return data.isPresent() ? data.get(): null;
    }

    @Override
    public List<ApprovedBorrowing> getAll() {
        return this.approedBorrowingRepository.findAll();
    }

    @Override
    public void updateApprovedBorrowing(ApprovedBorrowing data) {
        this.approedBorrowingRepository.save(data);
    }

    @Override
    public void deleteById(Long id) {
        this.approedBorrowingRepository.deleteById(id);
    }
    
}
