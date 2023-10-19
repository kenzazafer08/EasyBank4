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

}
