package com.example.easybank4.impl;

import com.example.easybank4.dto.Employee;
import com.example.easybank4.dto.Person;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class EmployeeDAOTest {
    private static SessionFactory _sessionFactory;
    private static EmployeeDAO _employeeDAO;
    @BeforeAll
    public static void setUp() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.format_sql", "true");
        configuration.setProperty("hibernate.use_sql_comments", "true");
        _sessionFactory = configuration.buildSessionFactory();
        _employeeDAO = new EmployeeDAO(_sessionFactory);
    }
    @Test
    public void saveTest() {
        Employee employee = new Employee();
        employee.setNumber("ee1233");
        employee.setFirstName("test");
        employee.setLastName("test");
        employee.setAddress("safi");
        employee.setDeleted(false);
        employee.setPhone("77553322");
        employee.setRecruitementDate(LocalDate.now());
        employee.setEmail("json@gmail.com");

        Optional<Person> employeeInserted = _employeeDAO.save(employee);
        assertNotNull(employeeInserted.orElse(null));

        Employee employee2 = new Employee();
        employee2.setNumber("ee9977");
        employee2.setFirstName("test2");
        employee2.setLastName("test2");
        employee2.setAddress("safi2");
        employee2.setDeleted(false);
        employee2.setPhone("7754455");
        employee2.setRecruitementDate(LocalDate.now());
        employee2.setEmail("graham@gmail.com");

        Optional<Person> employeeInserted2 = _employeeDAO.save(employee2);
        assertNotNull(employeeInserted2.orElse(null));
    }
    @Test
    public void findAllTest(){
        List<Person> employees = _employeeDAO.findAll();
        assertNotNull(employees);
    }
    @Test
    public void findById() {
        Optional<Person> employee = _employeeDAO.findById("ee1233");
        assertNotNull(employee.orElse(null));
    }
    @Test
    public void updateTest() {
        Optional<Person> employee = _employeeDAO.find("ee9977");
        if (employee.isPresent()) {
            Person employee1 = employee.get();
            employee1.setLastName("spirit");
            employee1.setAddress("tanger");
            Optional<Person> employeeUpdated = _employeeDAO.update(employee1);

            assertTrue(employeeUpdated.isPresent());
            assertEquals("spirit", employeeUpdated.get().getLastName());
            assertEquals("tanger", employeeUpdated.get().getAddress());
        }
    }
    @Test
    public void deleteTest() {
        boolean employeeDeleted = _employeeDAO.delete("ee9977");
        assertTrue(employeeDeleted);
    }
    @AfterAll
    public static void deleteAll() {
        boolean allEmployeesDeleted = _employeeDAO.deleteAll();
        assertTrue(allEmployeesDeleted);
        if (_sessionFactory != null) {
            _sessionFactory.close();
        }
    }
}
