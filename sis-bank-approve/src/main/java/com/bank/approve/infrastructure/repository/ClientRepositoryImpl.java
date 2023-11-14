package com.bank.approve.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.security.core.userdetails.UserDetails;

import com.bank.approve.domain.components.cardmodel.Card;
import com.bank.approve.domain.entity.Client;
import com.bank.approve.domain.entity.repository.ClientRepository;

public class ClientRepositoryImpl implements ClientRepository{

    @Override
    public <S extends Client> S insert(S entity) {
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public <S extends Client> List<S> insert(Iterable<S> entities) {
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public <S extends Client> List<S> findAll(Example<S> example) {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public <S extends Client> List<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public <S extends Client> List<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException("Unimplemented method 'saveAll'");
    }

    @Override
    public List<Client> findAll() {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public List<Client> findAllById(Iterable<String> ids) {
        throw new UnsupportedOperationException("Unimplemented method 'findAllById'");
    }

    @Override
    public <S extends Client> S save(S entity) {
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Optional<Client> findById(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public boolean existsById(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override
    public void deleteById(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public void delete(Client entity) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void deleteAllById(Iterable<? extends String> ids) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllById'");
    }

    @Override
    public void deleteAll(Iterable<? extends Client> entities) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public List<Client> findAll(Sort sort) {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Page<Client> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public <S extends Client> Optional<S> findOne(Example<S> example) {
        throw new UnsupportedOperationException("Unimplemented method 'findOne'");
    }

    @Override
    public <S extends Client> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public <S extends Client> long count(Example<S> example) {
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override
    public <S extends Client> boolean exists(Example<S> example) {
        throw new UnsupportedOperationException("Unimplemented method 'exists'");
    }

    @Override
    public <S extends Client, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        throw new UnsupportedOperationException("Unimplemented method 'findBy'");
    }

    @Override
    public UserDetails findByCpf(String cpf) {
        throw new UnsupportedOperationException("Unimplemented method 'findByCpf'");
    }

    @Override
    public List<Client> findByEmail(String email) {
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }

    @Override
    public List<Client> findByCards(List<Card> cards) {
        throw new UnsupportedOperationException("Unimplemented method 'findByCards'");
    }

    @Override
    public List<Client> findByNameComplete(String nameComplete) {
        throw new UnsupportedOperationException("Unimplemented method 'findByNameComplete'");
    }

    @Override
    public Client findByAccount(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'findByAccount'");
    }

    @Override
    public Client findByCard(String number) {
        throw new UnsupportedOperationException("Unimplemented method 'findByCard'");
    }

    @Override
    public Client findByIdAccount(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'findByIdAccount'");
    }
    
}
