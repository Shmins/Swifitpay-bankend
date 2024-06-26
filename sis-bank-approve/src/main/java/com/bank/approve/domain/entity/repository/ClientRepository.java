package com.bank.approve.domain.entity.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import com.bank.approve.domain.components.cardmodel.Card;
import com.bank.approve.domain.entity.Client;

public interface ClientRepository extends MongoRepository<Client, String> {
    UserDetails findByCpf(String cpf);
    List<Client> findByEmail(String email);
    List<Client> findByCards(List<Card> cards);
    List<Client> findByNameComplete(String nameComplete);
    
    @Query(value = "{'account.id': ?0}" )
    Client findByAccount(String id);
    @Query(value = "{'cards.numberCard': ?0}" )
    Client findByCard(String number);
    @Query(value = "{'account._id': ?0}" )
    Client findByIdAccount(String id);
}
