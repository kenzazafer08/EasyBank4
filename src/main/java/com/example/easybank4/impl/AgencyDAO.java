package com.example.easybank4.impl;

import com.example.easybank4.dao.IAgencyDAO;
import com.example.easybank4.dto.Agency;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class AgencyDAO implements IAgencyDAO {

    private final SessionFactory _sessionFactory;
    public AgencyDAO(SessionFactory sessionFactory) {
        this._sessionFactory = sessionFactory;
    }

    @Override
    public List<Agency> findAll() {
        return null;
    }

    @Override
    public Optional<Agency> findById(String s) {
        return Optional.empty();
    }

    @Override
    public Optional<Agency> find(String s) {
        return Optional.empty();
    }

    @Override
    public Optional<Agency> save(Agency demande) {
        System.out.println("3afaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaak : ");
        Session session = _sessionFactory.openSession();
        try {
            session.save(demande);
            session.close();
        } catch (Exception e) {
            demande = null;
            e.printStackTrace();
        }
        return Optional.ofNullable(demande);
    }

    @Override
    public Optional<Agency> update(Agency entity) {
        return Optional.empty();
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
