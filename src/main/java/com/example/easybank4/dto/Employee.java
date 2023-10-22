package com.example.easybank4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
@Where(clause = "deleted = false")
public final class Employee extends Person implements Serializable {
    @Id
    @Column(name = "number")
    private String number;
    @Column(name = "recruitment_date")
    private LocalDate recruitementDate;
    private String email;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Credit> credits;
}
