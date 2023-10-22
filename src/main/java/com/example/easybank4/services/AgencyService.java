package com.example.easybank4.services;

import com.example.easybank4.Impl.AgencyDAO;
import com.example.easybank4.dao.AgencyI;
import com.example.easybank4.dto.Agency;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class AgencyService {
    private final AgencyI agencyDAO;

    public AgencyService() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        agencyDAO = new AgencyDAO(sessionFactory);
    }

    public Optional<Agency> AddAgency(Agency agency){
        return agencyDAO.add(agency);
    }

    public Optional<Agency> findAgency(String code){
        return agencyDAO.SearchByCode(code);
    }

    public boolean deleteAgency(String code){
        if(agencyDAO.SearchByCode(code).isPresent()) {
            return agencyDAO.delete(code);
        }else{
            return false;
        }
    }

    public List<Agency> list(){
        return agencyDAO.AgencyList();
    }

    public Optional<Agency> searchByAddress(String Address){
        return agencyDAO.SearchByAddress(Address);
    }

    public  Optional<Agency> update(Agency agency){
        if(agencyDAO.SearchByCode(agency.getCode()).isPresent()){
            return agencyDAO.update(agency);
        }return Optional.empty();
    }

}
