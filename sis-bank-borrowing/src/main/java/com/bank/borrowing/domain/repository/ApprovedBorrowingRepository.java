package com.bank.borrowing.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.borrowing.domain.borrowing.ApprovedBorrowing;

@Repository
public interface ApprovedBorrowingRepository extends JpaRepository<ApprovedBorrowing, Long>{
    
}
