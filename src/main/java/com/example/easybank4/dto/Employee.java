package com.example.easybank4.dto;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Where(clause = "deleted = false")
public class Employee extends Person {

    @Id
    private String Number;
}