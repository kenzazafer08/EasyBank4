package com.example.easybank4.impl;

import com.example.easybank4.dao.IPersonDAO;
import com.example.easybank4.dto.Employee;
import com.example.easybank4.dto.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.hibernate.cfg.Configuration;

public class EmployeeDAO implements IPersonDAO {
    private final SessionFactory _sessionFactory;
    public EmployeeDAO(SessionFactory sessionFactory) {
        this._sessionFactory = sessionFactory;
    }
    @Override
    public List<Person> findAll() {
        return null;
    }

    @Override
    public Optional<Person> findById(String s) {
        return Optional.empty();
    }

    @Override
    public Optional<Person> find(String s) {
        return Optional.empty();
    }

    @Override
    public Optional<Person> save(Person entity) {
        /*System.out.println("here");
        Transaction transaction = null;
        try (Session session = _sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Serializable personId = session.save(entity);
            if (personId != null) {
                if (entity instanceof Employee e) {
                    e.setId((int) personId);
                    Serializable employeeId = session.save(e);
                    if (employeeId == null) {
                        throw new Exception("Employé non ajouté!!");
                    }
                }
            } else {
                throw new Exception("Personne non ajoutée!!");
            }
            transaction.commit();
            return Optional.of(entity);
        } catch (Exception e) {
            if (transaction != null) { transaction.rollback(); }
            e.printStackTrace();
        }*/
        return Optional.empty();
    }

    @Override
    public Optional<Person> update(Person entity) {
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
