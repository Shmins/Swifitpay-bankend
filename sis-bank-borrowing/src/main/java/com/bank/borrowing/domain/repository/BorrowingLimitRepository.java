package com.bank.borrowing.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.borrowing.domain.borrowing.BorrowingLimit;

@Repository
public interface BorrowingLimitRepository extends JpaRepository<BorrowingLimit, Long>{
    BorrowingLimit findByCpf(String cpf);
}
