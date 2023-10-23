package com.example.easybank4.impl;

import com.example.easybank4.dto.Agency;
import com.example.easybank4.dto.Client;
import com.example.easybank4.dto.Credit;
import com.example.easybank4.dto.Employee;
import com.example.easybank4.enums.CreditStatus;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CreditDAOTest {
    private static SessionFactory _sessionFactory;
    private static CreditDAO _creditDAO;
    private static AgencyDAO _agencyDAO;
    private static ClientDAO _clientDAO;
    private static EmployeeDAO _employeeDAO;
    @BeforeAll
    public static void setUp() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.format_sql", "true");
        configuration.setProperty("hibernate.use_sql_comments", "true");
        _sessionFactory = configuration.buildSessionFactory();
        _creditDAO = new CreditDAO(_sessionFactory);
        _agencyDAO = new AgencyDAO(_sessionFactory);
        _clientDAO = new ClientDAO(_sessionFactory);
        _employeeDAO = new EmployeeDAO(_sessionFactory);
    }
    @Test
    public void saveTest() {
        Agency agency = new Agency();
        agency.setCode("ff113366");
        agency.setName("hoba");
        agency.setPhone("0677443322");
        agency.setAddress("Safi");
        agency.setDeleted(false);

        _agencyDAO.save(agency);

        Client client = new Client();
        client.setCode("cc1233");
        client.setFirstName("test");
        client.setLastName("test");
        client.setAddress("safi");
        client.setDeleted(false);
        client.setPhone("77553322");

        _clientDAO.save(client);

        Employee employee = new Employee();
        employee.setNumber("ee1233");
        employee.setFirstName("test");
        employee.setLastName("test");
        employee.setAddress("safi");
        employee.setDeleted(false);
        employee.setPhone("77553322");
        employee.setRecruitementDate(LocalDate.now());
        employee.setEmail("json@gmail.com");

        _employeeDAO.save(employee);

        Credit credit = new Credit();
        credit.setCode("dd1233");
        credit.setStatus(CreditStatus.pending);
        credit.setDate(LocalDate.now());
        credit.setAmount(156);
        credit.setAgency(agency);
        credit.setClient(client);
        credit.setEmployee(employee);
        credit.setDuration(12);
        credit.setMonthly(140);
        credit.setDescription("sdfgfdsq");
        credit.setAnnual_percentage_rate(33);

        Optional<Credit> creditInserted = _creditDAO.save(credit);

        assertNotNull(creditInserted.orElse(null));

        Credit credit2 = new Credit();
        credit2.setCode("dd5566");
        credit2.setStatus(CreditStatus.accepted);
        credit2.setDate(LocalDate.now());
        credit2.setAmount(156);
        credit2.setAgency(agency);
        credit2.setClient(client);
        credit2.setEmployee(employee);
        credit2.setDuration(12);
        credit2.setMonthly(140);
        credit2.setDescription("sdfgfdsq");
        credit2.setAnnual_percentage_rate(33);

        _creditDAO.save(credit2);
    }
    @Test
    public void findByIdTest() {
        Optional<Credit> creditFound = _creditDAO.findById("dd1233");
        assertTrue(creditFound.isPresent());
    }
    @Test
    public void findAllTest() {
        List<Credit> credits = _creditDAO.findAll();
        assertNotNull(credits);
    }
    @Test
    @Disabled
    public void findByStatusTest() {
        List<Credit> credits = _creditDAO.findByStatus(CreditStatus.accepted);
        assertNotNull(credits);
    }
    @Test
    @Disabled
    public void findByDateTest() {
        List<Credit> credits = _creditDAO.findByDate(LocalDate.now());
        assertNotNull(credits);
    }
    @Test
    public void updateTest() {
        Optional<Credit> creditFound = _creditDAO.findById("dd1233");
        if (creditFound.isPresent()) {
            Credit credit = creditFound.get();
            credit.setStatus(CreditStatus.accepted);
            Optional<Credit> creditUpdated = _creditDAO.update(credit);

            assertTrue(creditUpdated.isPresent());
            assertEquals(CreditStatus.accepted, creditUpdated.get().getStatus());
        }
    }
    @Test
    public void deleteTest() {
        _employeeDAO.delete("ee1233");
        _clientDAO.delete("cc1233");
        _agencyDAO.delete("ff113366");
        boolean creditDeleted = _creditDAO.delete("dd1233");
        assertTrue(creditDeleted);
    }
    @AfterAll
    public static void deleteAll() {
        _employeeDAO.deleteAll();
        _agencyDAO.deleteAll();
        _clientDAO.deleteAll();
        boolean allCreditsDeleted = _creditDAO.deleteAll();
        assertTrue(allCreditsDeleted);
        if (_sessionFactory != null) {
            _sessionFactory.close();
        }
    }
}
