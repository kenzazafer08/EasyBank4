package services;

import Impl.AgencyDAO;
import dao.AgencyI;
import dto.Agency;

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

}
