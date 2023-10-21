package com.example.easybank4.dao;


import com.example.easybank4.dto.Client;

import java.util.List;
import java.util.Optional;

public interface ClientI {
    Optional<Client> add(Client client);
    Optional<Client> searchByCode(String code);
    boolean delete(String id);
    List<Client> showList();
    Optional<Client> update(Client client);
}
