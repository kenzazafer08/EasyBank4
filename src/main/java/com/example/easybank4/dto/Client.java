package com.example.easybank4.dto;


import javax.persistence.*;


@Entity
@Table(name = "client")
public class Client extends Person {

    @Id
    private String Code;

    public Client() {
    }

    public Client(String firstName, String lastName, String phone, String address, String code) {
        super(firstName, lastName, phone, address);
        Code = code;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }


}
