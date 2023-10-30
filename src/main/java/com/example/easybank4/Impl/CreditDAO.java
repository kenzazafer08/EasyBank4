package com.example.easybank4.Impl;

import com.example.easybank4.dao.CreditI;
import com.example.easybank4.dto.Credit;
import com.example.easybank4.helpers.helper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class CreditDAO implements CreditI {

    private SessionFactory sessionFactory;

    public CreditDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Credit> add(Credit credit) {
        Session session = sessionFactory.openSession();
        Transaction transaction  = session.beginTransaction();
        try {
            session.save(credit);
            transaction.commit();
            return Optional.of(credit);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Credit> SearchByCode(String code) {
        try (Session session = sessionFactory.openSession()) {
            Credit credit = session.get(Credit.class, code);

            return Optional.of(credit);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Credit> ShowList() {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Credit.class);
            List<Credit> credits = criteria.list();
            return credits;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Double Simulation(Credit credit) {
        double amount = credit.getAmount();
        int duration = credit.getDuration();
        double annualPercentageRate = 4;

        double monthlyRate = (annualPercentageRate / 100) / 12;

        double numerator = amount * monthlyRate;
        double denominator = 1 - Math.pow(1 + monthlyRate, -duration);
        double monthlyPayment = numerator / denominator;

        return monthlyPayment;    }
}
