package com.example.easybank4.impl;

import com.example.easybank4.dao.IPersonData;
import com.example.easybank4.dto.Client;
import com.example.easybank4.dto.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class ClientDAO implements IPersonData {
    private final SessionFactory _sessionFactory;
    public ClientDAO(SessionFactory sessionFactory) {
        this._sessionFactory = sessionFactory;
    }

    @Override
    public List<Person> findAll() {
        List<Person> clients = null;
        Transaction transaction = null;
        try (Session session = _sessionFactory.openSession()){
            transaction = session.beginTransaction();
            clients = session.createQuery("FROM com.example.easybank4.dto.Client", Person.class).list();
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Optional<Person> findById(String s) {
        if (s == null) {
            return Optional.empty();
        }

        Transaction transaction = null;
        try(Session session = _sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Client client = session.get(Client.class, s);
            if (client != null) {
                transaction.commit();
                return Optional.of(client);
            }else {
                throw new Exception("Client not found!!");
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
            if (entity instanceof Client c) {
                Serializable clientId = session.save(c);
                if (clientId != null) {
                    transaction.commit();
                    return Optional.of(entity);
                }
                else {
                    throw new Exception("Client not inserted!!");
                }
            }
            else {
                throw new Exception("Entity is not of a client instance");
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
            if (entity instanceof Client c) {
                Client client = session.get(Client.class, c.getCode());
                if (client != null) {
                    client.setFirstName(c.getFirstName());
                    client.setLastName(c.getLastName());
                    client.setAddress(c.getAddress());
                    client.setDeleted(c.getDeleted());
                    client.setPhone(c.getPhone());
                    session.update(client);

                    transaction.commit();
                    return Optional.of(client);
                }else {
                    throw new Exception("Client not found!!");
                }
            }
            else {
                throw new Exception("Entity is not of a client instance");
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
            Client client = session.get(Client.class, s);
            if (client != null) {
                //client.setDeleted(true);
                //session.update(client);
                session.delete(client);
                transaction.commit();
                return true;
            }
            else {
                throw new Exception("Client not found!!");
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
            List<Client> clients = session.createQuery("FROM com.example.easybank4.dto.Client", Client.class).list();
            for (Client client : clients) {
                session.delete(client);
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
