package classes;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@NamedQuery(name = "findNumberOfMatchesWithParam", query = "SELECT count(s) FROM Student s where s.studentNumber=:fname")

@NamedQuery(name = "findWithParam", query = "SELECT s FROM Student s where s.studentNumber=:fname")
@Table(name="Student")
public class Student {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer studentID;
    private String studentNumber;


    private int NoOfBooksLend;
    private String Email;
    private Integer phoneNumber;
    private String Department;



    private String firstName;

    private String lastName;

    private String Password;
    public Student()
    {
        studentNumber = "r0000000";
        NoOfBooksLend = 0;
        Email = "default@default.com";
        phoneNumber = 1000000000;
        Department = "none";
        Password = "password";
        firstName = "nothing";
        lastName = "added";

    }
    public Student(String studentnumbervalue, String Emailvalue, int number, String department,String Passwordvalue,String firstnamevalue, String lastNamemvalue)
    {
        studentNumber = studentnumbervalue;
        NoOfBooksLend = 0;
        Email = Emailvalue;
        phoneNumber = number;
        Department = department;
        Password = Passwordvalue;
        firstName=firstnamevalue;
        lastName=lastNamemvalue;

    }
    public void resetStudentValues()
    {
        studentNumber = "r0000000";
        NoOfBooksLend = 0;
        Email = "default@default.com";
        phoneNumber = 1000000000;
        Department = "none";
        Password = "password";
        firstName = "nothing";
        lastName = "added";
    }
    public Integer getStudentID() {
        return studentID;
    }


    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumbervalue) {
        studentNumber = studentNumbervalue;
    }

    public int getNoOfBooksLend() {
        return NoOfBooksLend;
    }

    public void setNoOfBooksLend(int noOfBooksLend) {
        NoOfBooksLend = noOfBooksLend;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phonenumber) {
        phoneNumber = phonenumber;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
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

}
