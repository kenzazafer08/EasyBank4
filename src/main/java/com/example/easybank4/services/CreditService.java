package com.example.easybank4.services;

import com.example.easybank4.dao.ICreditData;
import com.example.easybank4.dto.Credit;
import com.example.easybank4.enums.CreditStatus;
import com.example.easybank4.impl.CreditDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CreditService implements ICreditData {
    private final CreditDAO _creditDAO;
    public CreditService(CreditDAO creditDAO) {
        this._creditDAO = creditDAO;
    }

    public void monthlyPayout(Credit credit) {
        double paidMonthly = (credit.getAmount()
                * (credit.getAnnual_percentage_rate() / 12))
                / (1 - Math.pow((1 + (credit.getAnnual_percentage_rate() / 12)), - credit.getDuration()));
        credit.setMonthly(paidMonthly);
    }
    @Override
    public List<Credit> findAll() {
        return _creditDAO.findAll();
    }

    @Override
    public Optional<Credit> findById(String s) {
        return _creditDAO.findById(s);
    }

    @Override
    public Optional<Credit> find(String s) {
        return _creditDAO.find(s);
    }

    @Override
    public List<Credit> findByDate(LocalDate date) {
        return _creditDAO.findByDate(date);
    }

    @Override
    public List<Credit> findByStatus(CreditStatus creditStatus) {
        return _creditDAO.findByStatus(creditStatus);
    }

    @Override
    public Optional<Credit> save(Credit entity) {
        return _creditDAO.save(entity);
    }

    @Override
    public Optional<Credit> update(Credit entity) {
        return _creditDAO.update(entity);
    }

    @Override
    public boolean delete(String s) {
        return _creditDAO.delete(s);
    }

    @Override
    public boolean deleteAll() {
        return _creditDAO.deleteAll();
    }
}
