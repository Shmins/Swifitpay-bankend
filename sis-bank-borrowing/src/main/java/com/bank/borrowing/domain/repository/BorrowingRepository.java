package com.bank.borrowing.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.borrowing.domain.borrowing.Borrowing;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long>{
    List<Borrowing> findAllByCpf(String cpf);
}
