package com.example.easybank4.impl;

import com.example.easybank4.dto.Agency;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

public class AgencyDAOTest {
    private static SessionFactory _sessionFactory;
    private static AgencyDAO _agencyDAO;
    @BeforeAll
    public static void setUp() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.format_sql", "true");
        configuration.setProperty("hibernate.use_sql_comments", "true");
        _sessionFactory = configuration.buildSessionFactory();
        _agencyDAO = new AgencyDAO(_sessionFactory);
    }
    @Test
    public void saveTest() {
        Agency agency = new Agency();
        agency.setCode("ff113366");
        agency.setName("hoba");
        agency.setPhone("0677443322");
        agency.setAddress("Safi");
        agency.setDeleted(false);
        Optional<Agency> agencyInserted = _agencyDAO.save(agency);

        Agency agency2 = new Agency();
        agency2.setCode("gg123432");
        agency2.setName("hymn");
        agency2.setPhone("875544");
        agency2.setAddress("Kenitra");
        agency2.setDeleted(false);
        Optional<Agency> agency2Inserted = _agencyDAO.save(agency2);

        assertNotNull(agencyInserted.orElse(null));
        assertNotNull(agency2Inserted.orElse(null));
    }
    @Test
    public void findAllTest() {
        List<Agency> agencies = _agencyDAO.findAll();
        assertNotNull(agencies);
    }
    @Test
    public void findByIdTest() {
        Optional<Agency> agencyFound = _agencyDAO.findById("ff113366");
        assertNotNull(agencyFound.orElse(null));
    }
    @Test
    public  void updateTest() {
        Optional<Agency> agency = _agencyDAO.findById("ff113366");
        if (agency.isPresent()) {
            Agency newAgency = agency.get();
            newAgency.setName("Jesus");
            newAgency.setAddress("Casablanca");
            Optional<Agency> updatedAgency = _agencyDAO.update(newAgency);

            assertTrue(updatedAgency.isPresent());
            assertEquals("Jesus", updatedAgency.get().getName());
            assertEquals("Casablanca", updatedAgency.get().getAddress());
        }
    }
    @Test
    public void deleteTest() {
        boolean agencyDeleted = _agencyDAO.delete("ff113366");
        assertTrue(agencyDeleted);
    }
    @AfterAll
    public static void deleteAll() {
        boolean allAgenciesDeleted = _agencyDAO.deleteAll();
        assertTrue(allAgenciesDeleted);
        if (_sessionFactory != null) {
            _sessionFactory.close();
        }
    }
}
