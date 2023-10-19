package dto;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Table(name = "Agency")
public class Agency {
    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;

    public Agency() {
    }

    public Agency(String code, String name, String address, String phone ) {
        this.code = code;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
