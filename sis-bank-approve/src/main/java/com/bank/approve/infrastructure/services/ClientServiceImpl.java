package com.bank.approve.infrastructure.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.bank.approve.domain.entity.Client;
import com.bank.approve.domain.entity.repository.ClientRepository;
import com.bank.approve.usecase.client.ClientService;


@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void deleteById(String cpf) {
        this.clientRepository.deleteById(cpf);
    }

    @Override
    public Client getClientById(String cpf) {
        Optional<Client> client = this.clientRepository.findById(cpf);
        return client.isPresent() ? client.get() : null;
    }

    @Override
    public UserDetails getClientByCpf(String cpf) {
        UserDetails client = this.clientRepository.findByCpf(cpf);
        return client != null ? client : null;
    }

    @Override
    public List<Client> getAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Client getCardClient(String number) {
        return this.clientRepository.findByCard(number);
    }
    @Override
    public Client getByIdAccount(String id) {
        return this.clientRepository.findByAccount(id);
    }

    @Override
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

}
