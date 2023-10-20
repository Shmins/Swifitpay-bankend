package com.bank.approve.domain.approves.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.approve.domain.approves.ApproveOfficial;


@Repository
public interface ApproveOfficialRepository extends JpaRepository<ApproveOfficial, Long> {

}

