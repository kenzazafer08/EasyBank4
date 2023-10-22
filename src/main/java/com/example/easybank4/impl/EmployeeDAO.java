package com.example.easybank4.impl;

import com.example.easybank4.dao.IPersonData;
import com.example.easybank4.dto.Employee;
import com.example.easybank4.dto.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class EmployeeDAO implements IPersonData {
    private final SessionFactory _sessionFactory;
    public EmployeeDAO(SessionFactory sessionFactory) {
        this._sessionFactory = sessionFactory;
    }
    @Override
    public List<Person> findAll() {
        List<Person> employees = null;
        Transaction transaction = null;
        try (Session session = _sessionFactory.openSession()){
            transaction = session.beginTransaction();
            employees = session.createQuery("FROM com.example.easybank4.dto.Employee", Person.class).list();
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Optional<Person> findById(String s) {
        if (s == null) {
            return Optional.empty();
        }

        Transaction transaction = null;
        try(Session session = _sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, s);
            if (employee != null) {
                transaction.commit();
                return Optional.of(employee);
            }else {
                throw new Exception("Employee not found!!");
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
    public Optional<Person> find(String s) {
        return Optional.empty();
    }

    @Override
    public Optional<Person> save(Person entity) {
        if (entity == null) {
            return Optional.empty();
        }


        Transaction transaction = null;
        try (Session session = _sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (entity instanceof Employee e) {
                Serializable employeeId = session.save(e);
                if (employeeId != null) {
                    transaction.commit();
                    return Optional.of(entity);
                }
                else {
                    throw new Exception("Employee not inserted!!");
                }
            }
            else {
                throw new Exception("Entity is not of an employee instance");
            }
        } catch (Exception e) {
            if (transaction != null) { transaction.rollback(); }
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Person> update(Person entity) {
        if (entity== null) {
            return Optional.empty();
        }

        Transaction transaction = null;
        try (Session session = _sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (entity instanceof Employee e) {
                Employee employee = session.get(Employee.class, e.getNumber());
                if (employee != null) {
                    employee.setFirstName(e.getFirstName());
                    employee.setLastName(e.getLastName());
                    employee.setAddress(e.getAddress());
                    employee.setDeleted(e.getDeleted());
                    employee.setPhone(e.getPhone());
                    employee.setRecruitementDate(e.getRecruitementDate());
                    employee.setEmail(e.getEmail());
                    session.update(employee);

                    transaction.commit();
                    return Optional.of(employee);
                }else {
                    throw new Exception("Employee not found!!");
                }
            }
            else {
                throw new Exception("Entity is not of an employee instance");
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
        if (s == null) {
            return false;
        }

        Transaction transaction = null;
        try (Session session = _sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, s);
            if (employee != null) {
                session.delete(employee);
                transaction.commit();
                return true;
            }
            else {
                throw new Exception("Employee not found!!");
            }
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return false;
    }

    @Override
    public boolean deleteAll() {
        Transaction transaction = null;
        try (Session session = _sessionFactory.openSession()){
            transaction = session.beginTransaction();
            List<Employee> employees = session.createQuery("FROM com.example.easybank4.dto.Employee", Employee.class).list();
            for (Employee employee : employees) {
                session.delete(employee);
            }
            transaction.commit();
            return true;
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
}
