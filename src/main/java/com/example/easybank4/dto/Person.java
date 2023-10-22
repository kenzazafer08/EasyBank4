package com.example.easybank4.dto;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@javax.persistence.MappedSuperclass
public class Person {

    protected String firstName;
    protected String lastName;
    protected String phone;
    protected String address;
    private Boolean deleted;
}
