package com.example.easybank4.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
public abstract class Person {
    @Column(name = "first_name")
    protected String firstName;
    @Column(name = "last_name")
    protected String lastName;
    protected String phone;
    protected String address;
    protected Boolean deleted;
}