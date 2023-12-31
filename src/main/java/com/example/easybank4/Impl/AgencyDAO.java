package com.example.easybank4.Impl;

import com.example.easybank4.dto.Agency;
import com.example.easybank4.dao.AgencyI;
import com.example.easybank4.helpers.helper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Optional;

public class AgencyDAO implements AgencyI {
    private SessionFactory sessionFactory;

    public AgencyDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public AgencyDAO() {
    }

    public Optional<Agency> add(Agency agency) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            String agencyCode = helper.generate(5);
            agency.setCode(agencyCode);

            session.save(agency);

            session.getTransaction().commit();
            return Optional.of(agency);
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Agency> SearchByCode(String code) {
        try (Session session = sessionFactory.openSession()) {
            Agency agency = session.get(Agency.class, code);
            return Optional.of(agency);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Boolean delete(String code){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Agency entity = session.get(Agency.class, code);
            if (entity != null) {
                entity.setDeleted(true);
                session.update(entity);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<Agency> update(Agency agency) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(agency);
            transaction.commit();
            return Optional.of(agency);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    @Override
    public Optional<Agency> SearchByAddress(String address) {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Agency.class);
            criteria.add(Restrictions.eq("address", address)); // Assuming you have an "address" field
            Agency agency = (Agency) criteria.uniqueResult();

            return Optional.ofNullable(agency);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    @Override
    public List<Agency> AgencyList() {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Agency.class);
            List<Agency> agencies = criteria.list();
            return agencies;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
