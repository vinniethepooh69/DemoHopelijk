package classes;

import jakarta.persistence.*;
import jakarta.ws.rs.DefaultValue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "findStudentWithParam", query = "SELECT s FROM Student s where s.personUserNumber=:fname")

@Entity
@Table(name="Student")
public class Student extends Person {


    private int NoOfBooksLend;

    private String Department;


    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.MERGE)
    @JoinColumn(name="BookLendByStudentID" ,nullable = true)
    private List<Book> LendBooks;

    public Student()
    {   super();

        NoOfBooksLend = 0;
        Department = "none";
        LendBooks = new ArrayList<Book>();


    }
    public Student(String studentnumbervalue, String Emailvalue, String number, String department,String Passwordvalue,String firstnamevalue, String lastNamemvalue)
    {
        super(studentnumbervalue,Emailvalue,number,Passwordvalue,firstnamevalue,lastNamemvalue);
        NoOfBooksLend = 0;
        Department = department;
        LendBooks = new ArrayList<Book>();

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

    public List<Book> getLendBooks() {
        return LendBooks;
    }
    public List<Book> addBook(Book bookvalue)
    {
        LendBooks.add(LendBooks.size(),bookvalue);
        return LendBooks;
    }

    public void setLendBooks(List<Book> lendBooks) {
        LendBooks = lendBooks;
    }
}
