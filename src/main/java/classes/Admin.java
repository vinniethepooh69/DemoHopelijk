package classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
@NamedQuery(name = "findAdminWithAdminUserNumber", query = "SELECT a FROM Admin a where a.personUserNumber=:fname")

@Entity
@Table(name="Admin")
public class Admin extends Person{
    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    @Column(nullable = false)
    private String securityCode;

    //private String SecurityCode;
    public Admin()
    {
        super();
        securityCode="";

    }
    public Admin(String studentnumbervalue, String Emailvalue, String number, String Passwordvalue,String firstnamevalue, String lastNamemvalue, String Securitycode)
    {
        super(studentnumbervalue,Emailvalue,number,Passwordvalue,firstnamevalue,lastNamemvalue);
        securityCode = Securitycode;

    }
    public void resetAdminValues() {
        resetpersonvalues();
        securityCode = "";

    }



}

