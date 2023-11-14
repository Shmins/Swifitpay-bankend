package com.bank.approve.domain.entity.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.bank.approve.domain.entity.Official;

public interface OfficialRepository extends MongoRepository<Official, String> {
    UserDetails findByCpf(String cpf);
    List<Official> findByRg(String rg);
    List<Official> findByNameComplete(String nameComplete);
}
