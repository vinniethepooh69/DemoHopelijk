package EJB;

import classes.Student;
import classes.Book;
import jakarta.ejb.Stateful;
import jakarta.enterprise.context.SessionScoped;
import java.util.Date;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.client.ClientRequestContext;
import java.time.LocalDateTime;
import jakarta.ws.rs.core.Application;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Named
@Stateful
@SessionScoped
public class StudentStatefullSessionBean implements Serializable {

    @PersistenceContext(unitName = "hellodemo")
    EntityManager em;
    private Student student;

    @Inject
    private StudentEJBBean studentEJBBean;


    private List<Integer> BookIDValues;


    private Book book;

    /*public List<Book> showAllBooksLend()
        {
            List<Book> a =
            return
        }*/
    private int index_list;


    public void initialisation(Student studentvalue)
    {
        student = studentvalue;
        index_list = 0;
        setBookIDValues(studentEJBBean.getBookIDValues());
        book = new Book();

    }
    public void registerLendOfBook()
    {
        //student= em.find(Student.class,student.getPersonID());

        book.setLendDate(new Date());
        student.addBook(book);
        student.setNoOfBooksLend(student.getNoOfBooksLend()+1);
        //book.setStudent(student);
        em.merge(student);
        em.flush();

    }


    public List<Book> getLendBooks()
    {
        return student.getLendBooks();
    }
    public void returnBook(int bookid)
    {
        //book.setLendDate(null);
        //book.setStudent(null);
        book = em.find(Book.class,bookid);
        book.setLendDate(null);
        student.removeBook(book);
        student.setNoOfBooksLend(student.getLendBooks().size());
        em.merge(student);
        //em.merge(book);
        em.flush();

    }


    public void retrieveBook(int IDchange)
    {
        //TypedQuery<Book> query = em.createNamedQuery("findBookbyBookID",Book.class).setParameter("1",2);
        //return query.getSingleResult();

        index_list += IDchange;
        if(index_list<0)
        {index_list=0;}
        else if(index_list>=getBookIDValues().size())
        {index_list = getBookIDValues().size()-1;}
        TypedQuery<Book> query = studentEJBBean.returnBookQuery(getBookIDValues().get(index_list));

        //Query query = em.createNamedQuery("findStudentWithParam",Student.class);
        //query.setParameter("fname",student.getPersonUserNumber());

        if(query.getResultList().size()==0)
        {
            book=new Book();

        }
        else {
            book=query.getSingleResult();
        }

    }

    public boolean isBookMappedToStudent()
    {
        if(book.getStudent() != null)
        {return true;}
        else{return false;}
    }

    private String teststring;

    public String getTeststring() {
        return teststring;
    }

    public void setTeststring(String teststring) {
        this.teststring = teststring;
    }

    public Book retrievefirstbook()
    {
        return studentEJBBean.retrievefirstbook();
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


    public List<Integer> getBookIDValues() {
        return BookIDValues;
    }

    public void setBookIDValues(List<Integer> bookIDValues) {
        BookIDValues = bookIDValues;
    }


    public int getIndex_list() {
        return index_list;
    }

    public void setIndex_list(int index_list) {
        this.index_list = index_list;
    }

}
