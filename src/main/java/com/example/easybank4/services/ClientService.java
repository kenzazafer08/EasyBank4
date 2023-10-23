package com.example.easybank4.services;

import com.example.easybank4.dao.IPersonData;
import com.example.easybank4.dto.Person;
import com.example.easybank4.impl.ClientDAO;

import java.util.List;
import java.util.Optional;

public class ClientService implements IPersonData {
    private final ClientDAO _clientDAO;
    public ClientService(ClientDAO clientDAO) {
        this._clientDAO = clientDAO;
    }

    @Override
    public List<Person> findAll() {
        return _clientDAO.findAll();
    }
    @Override
    public Optional<Person> findById(String s) {
        return  _clientDAO.findById(s);
    }
    @Override
    public Optional<Person> find(String s) {
        return _clientDAO.find(s);
    }
    @Override
    public Optional<Person> save(Person person) {
        return _clientDAO.save(person);
    }
    @Override
    public Optional<Person> update(Person person) {
        return _clientDAO.update(person);
    }
    @Override
    public boolean delete(String s) {
        return _clientDAO.delete(s);
    }
    @Override
    public boolean deleteAll() {
        return _clientDAO.deleteAll();
    }
}
