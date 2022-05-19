package classes;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "findStudentByUserNumber", query = "SELECT s FROM Student s where s.personUserNumber=:fname")

@Entity
@Table(name="Student")
public class Student extends Person {


    private int NoOfBooksLend;

    private String Department;

    public boolean isInlogStatus() {
        return InlogStatus;
    }

    public void setInlogStatus(boolean inlogStatus) {
        InlogStatus = inlogStatus;
    }

    @Transient
    private boolean InlogStatus;

    @OneToMany( cascade = CascadeType.ALL , mappedBy = "student")
    @JoinColumn(name="STUDENT_PERSONID" ,nullable = true)
    private List<Book> LendBooks;

    public Student()
    {   super();

        NoOfBooksLend = 0;
        Department = "";
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
        Department = "";
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
    public void addBook(Book bookvalue)
    {
        bookvalue.setStudent(this);

        LendBooks.add(bookvalue);

    }
    public void removeBook(Book bookvalue)
    {

        LendBooks.remove(bookvalue);
        bookvalue.setStudent(null);

    }
    public void setLendBooks(List<Book> lendBooks) {
        LendBooks = lendBooks;
    }
}
