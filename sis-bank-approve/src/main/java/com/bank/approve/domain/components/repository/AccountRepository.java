package com.bank.approve.domain.components.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.approve.domain.components.Account;

public interface AccountRepository extends JpaRepository<Account, UUID>{

      
}
