package com.example.easybank4.impl;

import com.example.easybank4.dao.IAgencyData;
import com.example.easybank4.dto.Agency;
import org.hibernate.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class AgencyDAO implements IAgencyData {

    private final SessionFactory _sessionFactory;
    public AgencyDAO(SessionFactory sessionFactory) {
        this._sessionFactory = sessionFactory;
    }

    @Override
    public List<Agency> findAll() {
        List<Agency> agencies = null;
        Transaction transaction = null;

        try(Session session = _sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            agencies = session.createQuery("FROM com.example.easybank4.dto.Agency", Agency.class).list();
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return agencies;
    }

    @Override
    public Optional<Agency> findById(String s) {
        if (s == null) {
            return Optional.empty();
        }

        Transaction transaction = null;
        try(Session session = _sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Agency agency = session.get(Agency.class, s);
            if (agency != null) {
                transaction.commit();
                return Optional.of(agency);
            }
            else {
                throw new Exception("Agency not found");
            }
        }catch (Exception e) {
            if (transaction != null) { transaction.rollback(); }
            e.printStackTrace();
        }

        return Optional.empty();
    }

    //find agency by any field / attribute
    //still not finished
    @Override
    public Optional<Agency> find(String s) {
        if (s == null){
            return Optional.empty();
        }

        Transaction transaction = null;
        try(Session session= _sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Agency agency;

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Agency> save(Agency entity) {
        if (entity == null) {
            return Optional.empty();
        }


        Transaction transaction = null;
        try (Session session = _sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Serializable agencyId = session.save(entity);

            if (agencyId != null) {
                transaction.commit();
                return Optional.of(entity);
            }
            else {
                throw new Exception("Agency not inserted");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


        return Optional.empty();
    }

    @Override
    public Optional<Agency> update(Agency entity) {
        if (entity == null || entity.getCode() == null) {
            return Optional.empty();
        }

        Transaction transaction = null;
        try (Session session = _sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Agency existingAgency = session.get(Agency.class, entity.getCode());

            if (existingAgency != null) {
                existingAgency.setName(entity.getName());
                existingAgency.setPhone(entity.getPhone());
                existingAgency.setAddress(entity.getAddress());

                session.update(existingAgency);

                transaction.commit();
                return Optional.of(existingAgency);
            } else {
                throw new Exception("Agency not found for update");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(String s) {
        if (s == null) {
            return false;
        }

        Transaction transaction = null;
        try (Session session = _sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Agency agency = session.get(Agency.class, s);

            if (agency != null) {
                session.delete(agency);
                transaction.commit();
                return true;
            }
            else {
                throw new Exception("Agency not found!!");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteAll() {
        Transaction transaction = null;
        try (Session session = _sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List<Agency> agencies = session.createQuery("FROM com.example.easybank4.dto.Agency", Agency.class).list();

            for (Agency agency : agencies) {
                session.delete(agency);
            }

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return false;
    }
}
