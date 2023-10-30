package com.example.easybank4.Impl;

import com.example.easybank4.dao.EmployeeI;
import com.example.easybank4.dto.Client;
import com.example.easybank4.dto.Employee;
import com.example.easybank4.helpers.helper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EmployeeDAO implements EmployeeI{

    private SessionFactory sessionFactory;

    public EmployeeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Optional<Employee> add(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction  = session.beginTransaction();
        try {
            employee.setNumber(helper.generate(5));
            session.save(employee);
            transaction.commit();
            return Optional.of(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }    }

    @Override
    public Optional<Employee> searchByCode(String number) {
        try (Session session = sessionFactory.openSession()) {
            Employee employee = session.get(Employee.class, number);

            return Optional.of(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }    }

    @Override
    public boolean delete(String id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee entity = session.get(Employee.class, id);
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
        }    }

    @Override
    public List<Employee> showList() {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Employee.class);
            List<Employee> employees = criteria.list();
            return employees;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<Employee> SearchByAddress(String address) {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add(Restrictions.eq("address", address));
            List<Employee> employees = criteria.list();
            return employees;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }    }

    @Override
    public Optional<Employee> update(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
            return Optional.of(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }    }
}
