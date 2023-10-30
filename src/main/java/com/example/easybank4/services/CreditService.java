package com.example.easybank4.services;

import com.example.easybank4.Impl.CreditDAO;
import com.example.easybank4.dao.CreditI;
import com.example.easybank4.dto.Credit;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class CreditService {
    private final CreditI creditDAO;

    public CreditService() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        creditDAO = new CreditDAO(sessionFactory);
    }

    public Optional<Credit> add(Credit c){
        return creditDAO.add(c);
    }

    public Optional<Credit> SearchByCode(String code){
        return creditDAO.SearchByCode(code);
    }

    public List<Credit> ShowList(){
        return creditDAO.ShowList();
    }

    public Double Simulation(Credit credit){
        return creditDAO.Simulation(credit);
    }
}
