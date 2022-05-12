package classes;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity

@NamedQuery(name = "findStudentWithParam", query = "SELECT s FROM Student s where s.personUserNumber=:fname")
@NamedQuery(name = "findAdminWithParam", query = "SELECT a FROM Admin a where a.personUserNumber=:fname")
@Table(name="Student")
public class Student extends Person {


    private int NoOfBooksLend;

    private String Department;



    public Student()
    {   super();

        NoOfBooksLend = 0;
        Department = "none";

    }
    public Student(String studentnumbervalue, String Emailvalue, String number, String department,String Passwordvalue,String firstnamevalue, String lastNamemvalue)
    {
        super(studentnumbervalue,Emailvalue,number,Passwordvalue,firstnamevalue,lastNamemvalue);
        NoOfBooksLend = 0;
        Department = department;

    }
    public void resetStudentValues() {
        resetpersonvalues();
        NoOfBooksLend = 0;
        Department = "none";
    }

    public int getNoOfBooksLend() {
        return NoOfBooksLend;
    }

    public void setNoOfBooksLend(int noOfBooksLend) {
        NoOfBooksLend = noOfBooksLend;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }
}
