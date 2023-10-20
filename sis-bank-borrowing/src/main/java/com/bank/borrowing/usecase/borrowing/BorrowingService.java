package com.bank.borrowing.usecase.borrowing;

import java.util.List;


import com.bank.borrowing.domain.borrowing.Borrowing;

public interface BorrowingService {
    Borrowing createBorrowing(Borrowing data);
    Borrowing getById(Long id);
    List<Borrowing> getAllByCpf(String cpf);
    List<Borrowing> getAll();
    void updateBorrowing(Borrowing data);
    void deleteById(Long id);
}
