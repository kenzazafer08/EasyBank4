package dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name = "employee")
public class Employee extends Person {
    @Id
    private String number;
    private Date recruitementDate;
    private String email;
    public Employee() {
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getRecruitementDate() {
        return recruitementDate;
    }

    public void setRecruitementDate(Date recruitementDate) {
        this.recruitementDate = recruitementDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
