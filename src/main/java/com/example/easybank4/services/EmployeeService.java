package com.example.easybank4.services;

import com.example.easybank4.Impl.ClientDAO;
import com.example.easybank4.Impl.EmployeeDAO;
import com.example.easybank4.dao.ClientI;
import com.example.easybank4.dao.EmployeeI;
import com.example.easybank4.dto.Client;
import com.example.easybank4.dto.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private final EmployeeI employeeDAO;



    public EmployeeService() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        employeeDAO = new EmployeeDAO(sessionFactory);
    }

    public Optional<Employee> addEmployee(Employee employee) {
        return employeeDAO.add(employee);
    }

    public Optional<Employee> getEmployeeByCode(String code) {
        Optional<Employee> employee = employeeDAO.searchByCode(code);
        if(employee.isPresent()){
            return employee;
        }
        return Optional.empty();
    }

    public boolean deleteEmployee(String code) {
        return employeeDAO.delete(code);
    }

    public List<Employee> getEmployeeList() {
        return employeeDAO.showList();
    }

    public Optional<Employee> updateEmployee(Employee employee) {
        return employeeDAO.update(employee);
    }

    public List<Employee> searchByAddress(String Address){
        return employeeDAO.SearchByAddress(Address);
    }
}
