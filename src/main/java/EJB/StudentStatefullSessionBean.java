package EJB;

import classes.Student;
import jakarta.ejb.Stateful;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.awt.print.Book;
import java.util.List;

@Named
@Stateful
public class StudentStatefullSessionBean {

    @PersistenceContext(unitName = "hellodemo")
    EntityManager em;
    Student student;


    Book book;

    /*public List<Book> showAllBooksLend()
    {
        List<Book> a =
        return
    }*/


    public void assignStudent(Student studentvalue)
    {
        student=studentvalue;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}
