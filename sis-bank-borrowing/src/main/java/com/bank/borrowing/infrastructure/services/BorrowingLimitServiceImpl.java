package com.bank.borrowing.infrastructure.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.borrowing.domain.borrowing.BorrowingLimit;
import com.bank.borrowing.domain.repository.BorrowingLimitRepository;
import com.bank.borrowing.usecase.limitborrowing.BorrowingLimitService;

@Service
public class BorrowingLimitServiceImpl implements BorrowingLimitService{
    @Autowired
    private BorrowingLimitRepository borrowingLimitRepository;
    @Override
    public BorrowingLimit createBorrowingLimit(BorrowingLimit data) {
        return this.borrowingLimitRepository.save(data);
    }

    @Override
    public BorrowingLimit getById(Long id) {
        Optional<BorrowingLimit> data = this.borrowingLimitRepository.findById(id);
        return data.isPresent() ? data.get(): null;
    }

    @Override
    public List<BorrowingLimit> getAll() {
        return this.borrowingLimitRepository.findAll();
    }

    @Override
    public void updateBorrowingLimit(BorrowingLimit data) {
        this.borrowingLimitRepository.save(data);
    }

    @Override
    public void deleteById(Long id) {
        this.borrowingLimitRepository.deleteById(id);
    }
    
}
