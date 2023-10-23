package com.example.easybank4.impl;

import com.example.easybank4.dto.Client;
import com.example.easybank4.dto.Person;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
public class ClientDAOTest {
    private static SessionFactory _sessionFactory;
    private static ClientDAO _clientDAO;
    @BeforeAll
    public static void setUp() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.format_sql", "true");
        configuration.setProperty("hibernate.use_sql_comments", "true");
        _sessionFactory = configuration.buildSessionFactory();
        _clientDAO = new ClientDAO(_sessionFactory);
    }
    @Test
    public void saveTest() {
        Client client = new Client();
        client.setCode("cc1233");
        client.setFirstName("test");
        client.setLastName("test");
        client.setAddress("safi");
        client.setDeleted(false);
        client.setPhone("77553322");

        Optional<Person> clientInserted = _clientDAO.save(client);
        assertNotNull(clientInserted.orElse(null));

        Client client2 = new Client();
        client2.setCode("cc9977");
        client2.setFirstName("test2");
        client2.setLastName("test2");
        client2.setAddress("safi2");
        client2.setDeleted(false);
        client2.setPhone("7754455");

        Optional<Person> clientInserted2 = _clientDAO.save(client2);
        assertNotNull(clientInserted2.orElse(null));
    }
    @Test
    public void findAllTest(){
        List<Person> clients = _clientDAO.findAll();
        assertNotNull(clients);
    }
    @Test
    public void findByIdTest() {
        Optional<Person> client = _clientDAO.findById("cc1233");
        assertNotNull(client.orElse(null));
    }
    @Test
    public void updateTest() {
        Optional<Person> client = _clientDAO.find("cc9977");
        if (client.isPresent()) {
            Person client1 = client.get();
            client1.setLastName("spirit");
            client1.setAddress("tanger");
            Optional<Person> clientUpdated = _clientDAO.update(client1);

            assertTrue(clientUpdated.isPresent());
            assertEquals("spirit", clientUpdated.get().getLastName());
            assertEquals("tanger", clientUpdated.get().getAddress());
        }
    }
    @Test
    public void deleteTest() {
        boolean clientDeleted = _clientDAO.delete("cc9977");
        assertTrue(clientDeleted);
    }
    @AfterAll
    public static void deleteAll() {
        boolean allClientsDeleted = _clientDAO.deleteAll();
        assertTrue(allClientsDeleted);
        if (_sessionFactory != null) {
            _sessionFactory.close();
        }
    }
}
