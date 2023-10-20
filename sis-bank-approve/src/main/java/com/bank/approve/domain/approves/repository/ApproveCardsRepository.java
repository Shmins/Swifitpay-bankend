package com.bank.approve.domain.approves.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.approve.domain.approves.ApproveCards;

@Repository
public interface ApproveCardsRepository extends JpaRepository<ApproveCards, Long> {
}
