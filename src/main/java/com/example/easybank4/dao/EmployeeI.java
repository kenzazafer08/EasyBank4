package com.example.easybank4.dao;

import com.example.easybank4.dto.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeI {
    Optional<Employee> add(Employee employee);
    Optional<Employee> searchByCode(String number);
    boolean delete(String id);
    List<Employee> showList();
    List<Employee> SearchByAddress(String address);
    Optional<Employee> update(Employee employee);
}
