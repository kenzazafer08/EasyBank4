package com.example.easybank4.Impl;

import com.example.easybank4.dao.ClientI;
import com.example.easybank4.dto.Agency;
import com.example.easybank4.dto.Client;
import com.example.easybank4.dto.Person;
import com.example.easybank4.helpers.helper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ClientDAO implements ClientI {

    private SessionFactory sessionFactory;

    public ClientDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ClientDAO() {

    }

    @Override
    public Optional<Client> add(Client client) {
        Session session = sessionFactory.openSession();
        Transaction transaction  = session.beginTransaction();
        try {
            client.setCode(helper.generate(3));
            session.save(client);
            transaction.commit();
            return Optional.of(client);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Client> searchByCode(String code) {
        try (Session session = sessionFactory.openSession()) {
            Client client = session.get(Client.class, code);

            return Optional.of(client);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(String id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Client entity = session.get(Client.class, id);
            if (entity != null) {
                entity.setDeleted(true);
                session.update(entity);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Client> showList() {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Client.class);
            List<Client> clients = criteria.list();
            return clients;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Client> SearchByAddress(String address) {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Client.class);
            criteria.add(Restrictions.eq("address", address));
            List<Client> clients = criteria.list();
            return clients;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Client> update(Client client) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(client);
            transaction.commit();
            return Optional.of(client);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
