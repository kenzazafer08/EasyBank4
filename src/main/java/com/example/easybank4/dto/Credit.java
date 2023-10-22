package com.example.easybank4.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "credit")
public class Credit {
    @Id
    private String code;
    private LocalDate date;
    private double amount;
    @Column(name = "deadline")
    private int duration;
    private String description;
    private CreditStatus status;
    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private double monthly;
    @Transient
    private double annual_percentage_rate;
}
