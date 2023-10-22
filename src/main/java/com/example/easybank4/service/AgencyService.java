package com.example.easybank4.service;

import com.example.easybank4.dao.IAgencyData;
import com.example.easybank4.dto.Agency;
import com.example.easybank4.impl.AgencyDAO;

import java.util.List;
import java.util.Optional;

public class AgencyService implements IAgencyData {
    private final AgencyDAO _agencyDAO;
    public AgencyService(AgencyDAO agencyDAO) {
        this._agencyDAO = agencyDAO;
    }

    @Override
    public List<Agency> findAll() {
        return _agencyDAO.findAll();
    }

    @Override
    public Optional<Agency> findById(String s) {
        return _agencyDAO.findById(s);
    }

    @Override
    public Optional<Agency> find(String s) {
        return _agencyDAO.find(s);
    }

    @Override
    public Optional<Agency> save(Agency entity) {
        return _agencyDAO.save(entity);
    }

    @Override
    public Optional<Agency> update(Agency entity) {
        return _agencyDAO.update(entity);
    }

    @Override
    public boolean delete(String s) {
        return _agencyDAO.delete(s);
    }

    @Override
    public boolean deleteAll() {
        return _agencyDAO.deleteAll();
    }
}
