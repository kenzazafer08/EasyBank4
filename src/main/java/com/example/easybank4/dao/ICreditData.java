package com.example.easybank4.dao;

import com.example.easybank4.dto.Credit;
import com.example.easybank4.enums.CreditStatus;

import java.time.LocalDate;
import java.util.List;

public interface ICreditData extends IData<Credit, String>{
    List<Credit> findByDate(LocalDate date);
    List<Credit> findByStatus(CreditStatus creditStatus);
}
