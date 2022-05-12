package classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="Admin")
public class Admin extends Person{
    @Column(nullable = false)
    private String SecurityCode;
    public Admin()
    {
        super();
        SecurityCode="5424";

    }
    public Admin(String studentnumbervalue, String Emailvalue, String number, String Passwordvalue,String firstnamevalue, String lastNamemvalue, String Securitycode)
    {
        super(studentnumbervalue,Emailvalue,number,Passwordvalue,firstnamevalue,lastNamemvalue);
        SecurityCode = Securitycode;

    }
    public void resetAdminValues() {
        resetpersonvalues();
        SecurityCode = "5424";

    }

    public String getSecurityCode() {
        return SecurityCode;
    }

    public void setSecurityCode(String securityCode) {
        SecurityCode = securityCode;
    }
}

