package dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
@Entity
@Table(name = "agency")
public class Agency {
    @Id
    private String code;
    private String name;
    private String address;
    private String phone;
    private List<Credit> Credits;
    public Agency() {
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

    public List<Credit> getCredits() {
        return Credits;
    }

    public void setCredits(List<Credit> credits) {
        Credits = credits;
    }
}
