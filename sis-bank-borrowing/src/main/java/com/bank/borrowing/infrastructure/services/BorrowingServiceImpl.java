package com.bank.borrowing.infrastructure.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.borrowing.domain.borrowing.Borrowing;
import com.bank.borrowing.domain.repository.BorrowingRepository;
import com.bank.borrowing.usecase.borrowing.BorrowingService;


@Service
public class BorrowingServiceImpl implements BorrowingService{
    @Autowired
    private BorrowingRepository borrowingRepository;

    @Override
    public Borrowing createBorrowing(Borrowing data) {
        return this.borrowingRepository.save(data);
    }

    @Override
    public Borrowing getById(Long id) {
        Optional<Borrowing> data = this.borrowingRepository.findById(id);
        return data.isPresent() ? data.get(): null;
    }

    @Override
    public List<Borrowing> getAll() {
        return this.borrowingRepository.findAll();
    }

    @Override
    public void updateBorrowing(Borrowing data) {
        this.borrowingRepository.save(data);
    }

    @Override
    public void deleteById(Long id) {
        this.borrowingRepository.deleteById(id);
    }

    @Override
    public List<Borrowing> getAllByCpf(String cpf) {
        return this.borrowingRepository.findAllByCpf(cpf);
    }
    
}
