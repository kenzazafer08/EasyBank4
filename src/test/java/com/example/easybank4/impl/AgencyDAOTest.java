package com.example.easybank4.impl;

import com.example.easybank4.dto.Agency;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

public class AgencyDAOTest {
    private static SessionFactory _sessionFactory;
    private static AgencyDAO _agencyDAO;
    @BeforeAll
    public static void setUp() {
        _sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
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
        assertNotNull(agencyInserted.orElse(null));
    }
    @AfterAll
    public static void teardown() {
        if (_sessionFactory != null) {
            _sessionFactory.close();
        }
    }
}
