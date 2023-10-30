package com.example.easybank4.dto;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Credit {
    @Id
    private String code;

    private LocalDate date;
    private double amount;
    private int duration;
    private String description;
    private double monthly;
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

}
