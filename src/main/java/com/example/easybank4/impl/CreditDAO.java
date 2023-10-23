package com.example.easybank4.impl;

import com.example.easybank4.dao.ICreditData;
import com.example.easybank4.dto.Credit;
import com.example.easybank4.enums.CreditStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CreditDAO implements ICreditData {
    private final SessionFactory _sessionFactory;
    public CreditDAO(SessionFactory sessionFactory) {
        this._sessionFactory = sessionFactory;
    }

    @Override
    public List<Credit> findAll() {
        List<Credit> credits = null;
        Transaction transaction = null;

        try (Session session = _sessionFactory.openSession()){
            transaction = session.beginTransaction();
            credits = session.createQuery("FROM com.example.easybank4.dto.Credit", Credit.class).list();
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return credits;
    }

    @Override
    public Optional<Credit> findById(String s) {
        if (s == null) {
            return Optional.empty();
        }

        Transaction transaction = null;
        try (Session session = _sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Credit credit = session.get(Credit.class, s);
            if (credit != null) {
                transaction.commit();
                return Optional.of(credit);
            }else {
                throw new Exception("Demand not found!!");
            }
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Credit> findByDate(LocalDate date) {
        List<Credit> credits = null;
        try (Session session = _sessionFactory.openSession()) {
            String hql = "FROM com.example.easybank4.dto.Credit c WHERE c.date = :date";
            credits = session.createQuery(hql, Credit.class)
                    .setParameter("date", date)
                    .list();

            return credits;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return credits;
    }

    @Override
    public List<Credit> findByStatus(CreditStatus creditStatus) {
        List<Credit> credits = null;
        try (Session session = _sessionFactory.openSession()){
            String hql = "FROM com.example.easybank4.dto.Credit c WHERE c.status = :status";
            credits = session.createQuery(hql, Credit.class)
                    .setParameter("status", creditStatus)
                    .list();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return credits;
    }

    @Override
    public Optional<Credit> find(String s) {
        return Optional.empty();
    }

    @Override
    public Optional<Credit> save(Credit entity) {
        if(entity == null) {
            return Optional.empty();
        }

        Transaction transaction = null;
        try(Session session = _sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Serializable creditId = session.save(entity);
            if (creditId != null) {
                transaction.commit();
                return Optional.of(entity);
            }else {
                throw new Exception("Demand not inserted!!");
            }
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Credit> update(Credit entity) {
        if (entity == null) {
            return Optional.empty();
        }

        Transaction transaction = null;
        try (Session session = _sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Credit credit = session.get(Credit.class, entity.getCode());
            if (credit != null) {
                credit.setStatus(entity.getStatus());
                credit.setUpdatedAt(LocalDate.now());
                session.update(credit);
                transaction.commit();
                return Optional.of(credit);
            }else {
                throw new Exception("Demand not found!!");
            }
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(String s) {
        if(s == null) {
            return false;
        }

        Transaction transaction = null;
        try(Session session = _sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Credit credit = session.get(Credit.class, s);
            if (credit != null) {
                session.delete(credit);
                transaction.commit();
                return true;
            }else {
                throw new Exception("Demand not found!!");
            }
        }catch(Exception e) {
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
        try (Session session = _sessionFactory.openSession()){
            transaction = session.beginTransaction();
            List<Credit> credits = session.createQuery("FROM com.example.easybank4.dto.Credit", Credit.class).list();
            for (Credit credit : credits) {
                session.delete(credit);
            }
            transaction.commit();
            return true;
        }catch(Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return false;
    }
}
