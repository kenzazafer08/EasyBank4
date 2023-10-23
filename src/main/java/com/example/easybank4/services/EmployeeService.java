package com.example.easybank4.services;

import com.example.easybank4.dao.IPersonData;
import com.example.easybank4.dto.Person;
import com.example.easybank4.impl.EmployeeDAO;

import java.util.List;
import java.util.Optional;

public class EmployeeService implements IPersonData {
    private final EmployeeDAO _employeeDAO;
    public EmployeeService(EmployeeDAO employeeDAO) {
        this._employeeDAO = employeeDAO;
    }

    @Override
    public List<Person> findAll() {
        return _employeeDAO.findAll();
    }
    @Override
    public Optional<Person> findById(String s) {
        return  _employeeDAO.findById(s);
    }
    @Override
    public Optional<Person> find(String s) {
        return _employeeDAO.find(s);
    }
    @Override
    public Optional<Person> save(Person person) {
        return _employeeDAO.save(person);
    }
    @Override
    public Optional<Person> update(Person person) {
        return _employeeDAO.update(person);
    }
    @Override
    public boolean delete(String s) {
        return _employeeDAO.delete(s);
    }
    @Override
    public boolean deleteAll() {
        return _employeeDAO.deleteAll();
    }

}
