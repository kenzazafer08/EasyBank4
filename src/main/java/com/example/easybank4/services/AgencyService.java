package com.example.easybank4.services;

import com.example.easybank4.Impl.AgencyDAO;
import com.example.easybank4.dao.AgencyI;
import com.example.easybank4.dto.Agency;

import java.util.List;
import java.util.Optional;

public class AgencyService {
    private final AgencyI agencyDAO;

    public AgencyService() {
        agencyDAO = new AgencyDAO();
    }

    public Optional<Agency> AddAgency(Agency agency){
        return agencyDAO.add(agency);
    }

    public Optional<Agency> findAgency(String code){
        Optional<Agency> agency = agencyDAO.SearchByAddress(code);
        if(agency.isPresent()){
            if(agency.get().getDeleted()){
                return Optional.empty();
            }return agency;
        }
        return Optional.empty();
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

}
