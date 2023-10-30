package com.example.easybank4.dao;


import com.example.easybank4.dto.Credit;

import java.util.List;
import java.util.Optional;

public interface CreditI {
    Optional<Credit> add(Credit credit);
    Optional<Credit> SearchByCode(String code);
    List<Credit> ShowList();
    Double Simulation(Credit credit);
}

