package com.bank.approve.usecase.client;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.bank.approve.domain.entity.Client;


public interface ClientService {
    void deleteById(String cpf);
    Client getClientById(String cpf);
    UserDetails getClientByCpf(String cpf);
    List<Client> getAll();
    Client getCardClient(String number);
    Client updateClient(Client client);
    Client getByIdAccount(String id);
}