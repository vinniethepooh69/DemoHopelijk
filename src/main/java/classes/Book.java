package classes;


import jakarta.persistence.*;
import classes.Book;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@NamedQuery(name = "findBookIDValues", query = "SELECT b.BookID FROM Book b")
@NamedQuery(name = "findBookbyBookTitle", query = "SELECT b FROM Book b where b.title_of_Book =:fname")
@NamedQuery(name = "findBookbyBookID", query = "SELECT b FROM Book b where b.BookID = ?1")
@NamedQuery(name = "findBooksbyStudentID", query = "SELECT b FROM Book b where b.student.personID =?1")
@NamedQuery(name = "findMinBookID", query = "SELECT min(b.BookID) FROM Book b")

@Entity
@Table(name="Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer BookID;


    @Column(nullable = false, unique = true)
    private String title_of_Book;
    private int numberOfPages;
    private String language; //later mss enum maken: engels/nederlands/frans
    private String subject; //later mss string van maken: psychologie;computer science; ...
    private Boolean fiction;

    @Temporal(TemporalType.DATE)
    private Date lendDate;

    @ManyToOne
    //@JoinColumn(name="STUDENT_PERSONID" ,nullable = true)
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToMany
    @JoinTable(name = "JoinTableBookIDAuthorID", joinColumns = @JoinColumn(name = "BookID"), inverseJoinColumns = @JoinColumn(name = "AuthorIS"))
    private List<Author> createdByAuthors;


    public List<Author> getCreatedByAuthors() {
        return createdByAuthors;
    }

    public void setCreatedByAuthors(List<Author> createdByAuthors) {
        this.createdByAuthors = createdByAuthors;
    }
    public void addAuthor(Author author)
    {
        if(!createdByAuthors.contains(author))
        {
        createdByAuthors.add(createdByAuthors.size(),author);
        }
    }
    public void removeAuthor(Author author)
    {
        if(createdByAuthors.contains(author))
        {
            createdByAuthors.remove(author);
        }
    }

    public Book()
    {
        title_of_Book="";
        numberOfPages=0;
        language="";
        subject="";
        fiction=false;
        lendDate = null;
        createdByAuthors= new ArrayList<Author>();
    }
    public Book(String titlevalue, int numberOfPagesvalue, String languagevalue, String subjectvalue, Boolean fictionvalue)
    {
        title_of_Book=titlevalue;
        numberOfPages = numberOfPagesvalue;
        language=languagevalue;
        subject=subjectvalue;
        fiction=fictionvalue;
    }

    @Override

    public boolean equals(Object o)
    {
        if (!(o instanceof Book)) {
            return false;
        }
        Book other = (Book) o;
        return getBookID() == (other.getBookID());

    }

    public Date getLendDate() {
        return lendDate;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }


    public String getTitle_of_Book() {
        return title_of_Book;
    }

    public void setTitle_of_Book(String title_of_Book) {
        this.title_of_Book = title_of_Book;
    }

    public Integer getBookID() {
        return BookID;
    }

    public void setBookID(Integer bookID) {
        BookID = bookID;
    }


    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Boolean getFiction() {
        return fiction;
    }

    public void setFiction(Boolean fiction) {
        this.fiction = fiction;
    }
}
