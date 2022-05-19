package EJB;

import classes.Student;
import classes.Book;
import jakarta.ejb.Stateful;
import jakarta.enterprise.context.SessionScoped;
import java.util.Date;
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


    public int getLowestBookID() {
        return lowestBookID;
    }

    public void setLowestBookID(int lowestBookID) {
        this.lowestBookID = lowestBookID;
    }

    private int lowestBookID;
    private int hulp5;

    public int getHulp5() {
        return hulp5;
    }

    public void setHulp5(int hulp5) {
        this.hulp5 = hulp5;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    private int bookID = lowestBookID;

    private Book book;

    public int getHulp1() {
        return hulp1;
    }

    public void setHulp1(int hulp1) {
        this.hulp1 = hulp1;
    }

    public int getHulp2() {
        return hulp2;
    }

    public void setHulp2(int hulp2) {
        this.hulp2 = hulp2;
    }

    public int getInvokecounter() {
        return invokecounter;
    }

    public void setInvokecounter(int invokecounter) {
        this.invokecounter = invokecounter;
    }

    private int invokecounter;

    private int hulp1;
    private int hulp2;

    public int getHulp3() {
        return hulp3;
    }

    public void setHulp3(int hulp3) {
        this.hulp3 = hulp3;
    }

    private int hulp3;
    private String hulp4;
    /*public List<Book> showAllBooksLend()
    {
        List<Book> a =
        return
    }*/

    public String getHulp4() {
        return hulp4;
    }

    public void setHulp4(String hulp4) {
        this.hulp4 = hulp4;
    }
    public void yolo()
    {

        hulp4 = student.getPersonUserNumber();
    }

    public void retrieveAllLendBooksByUser()
    {
        hulp4 = "heeeee";
        TypedQuery<Book> query = em.createNamedQuery("findBooksbyStudentID",Book.class).setParameter("1",student.getPersonID());
        hulp3 = query.getResultList().size();
        student.setLendBooks(query.getResultList());

    }

    public void initialisation()
    {
        book = new Book();

    }
    public void registerLendOfBook()
    {
        //student= em.find(Student.class,student.getPersonID());
        em.merge(student);
        if(em.contains(book))
        {
            student.setDepartment("En deze zegt ook al contains");
        }
        else{student.setDepartment("En deze zegt van niet");}

        hulp4 =student.getFirstName();
        book.setLendDate(new Date());
        student.addBook(book);
        student.setNoOfBooksLend(student.getNoOfBooksLend()+1);
        book.setStudent(student);
        em.merge(student);
        em.flush();

    }



    public void returnBook(int index)
    {
        hulp4 = "returnedbookid"+index;
        Book book = student.getLendBooks().remove(index);
        book.setLendDate(null);
        student.setNoOfBooksLend(student.getLendBooks().size());
        em.merge(student);

    }
    public boolean isTest12() {
        return test12;
    }

    public void setTest12(boolean test12) {
        this.test12 = test12;
    }

    private boolean test12;
    public void testmanaged()
    {
        test12= em.contains(student);
    }
    public void detachstudent()
    {
        em.clear();
        testmanaged();
    }
    public void attachtudent()
    {
        em.merge(student);
        testmanaged();
    }

    public void testfunctie()
    {
        student.setDepartment("Gelukt");
        em.flush();
    }

    public void retrieveBook(int IDchange)
    {
        //TypedQuery<Book> query = em.createNamedQuery("findBookbyBookID",Book.class).setParameter("1",2);
        //return query.getSingleResult();

        invokecounter +=1;
        bookID += IDchange;
        if(bookID<lowestBookID)
        {bookID=lowestBookID;}
        hulp1 = bookID;
        TypedQuery<Book> query = em.createNamedQuery("findBookbyBookID",Book.class).setParameter("1",bookID);

        //Query query = em.createNamedQuery("findStudentWithParam",Student.class);
        //query.setParameter("fname",student.getPersonUserNumber());

        if(query.getResultList().size()==0)
        {
//            bookID -=IDchange;
            book=new Book();

        }
        else {
            book=query.getSingleResult();
            System.out.println("book loaded: " + book.getStudent() + "::" +  book.getBookID());
            book=query.getSingleResult();
        }

    }

    public boolean isBookMappedToStudent()
    {
        if(book.getStudent() != null)
        {return true;}
        else{return false;}
    }



    public Book retrievefirstbook()
    {
        TypedQuery<Book> query = em.createNamedQuery("findBookbyBookID",Book.class).setParameter("1",2);
        return query.getSingleResult();
    }
    public void LendBookByStudent()
    {
 //       student.addBook(book);
    }
    public void assignStudent(int studentvalue)
    {
        student=em.find(Student.class,studentvalue);
        if(em.contains(student))
        {
            student.setDepartment("deze zegt wel contains");
        }

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
