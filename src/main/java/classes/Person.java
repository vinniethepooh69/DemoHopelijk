package classes;

import jakarta.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer personID;

    @Column(nullable = false, unique = true)
    private String personUserNumber;

    @Column(nullable = false)
    private String Email;
    private String phoneNumber;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String Password;

    public Person()
    {
        personUserNumber = "";
        Email = "";
        phoneNumber="";
        firstName="";
        lastName="";
        Password = "";

    }
    public Person(String studentnumbervalue, String Emailvalue, String number, String Passwordvalue,String firstnamevalue, String lastNamemvalue)
    {
        personUserNumber = studentnumbervalue;
        Email = Emailvalue;
        phoneNumber = number;
        Password = Passwordvalue;
        firstName=firstnamevalue;
        lastName=lastNamemvalue;

    }
    public void resetpersonvalues(){
        personUserNumber = "";
        Email = "";
        phoneNumber="";
        firstName="";
        lastName="";
        Password = "";

    }


    public Integer getPersonID() {
        return personID;
    }

    public void setPersonID(Integer personID) {
        this.personID = personID;
    }

    public String getPersonUserNumber() {
        return personUserNumber;
    }

    public void setPersonUserNumber(String personUserNummer) {
        this.personUserNumber = personUserNummer;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
