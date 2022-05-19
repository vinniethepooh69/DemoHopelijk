package classes;


import jakarta.persistence.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NamedQuery(name = "findBookbyBookID", query = "SELECT b FROM Book b where b.BookID = ?1")
@NamedQuery(name = "findBooksbyStudentID", query = "SELECT b FROM Book b where b.BookLendByStudentID =?1")
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



    @ManyToMany
    @JoinTable(name = "JoinTableBookIDAuthorID", joinColumns = @JoinColumn(name = "BookID"), inverseJoinColumns = @JoinColumn(name = "AuthorIS"))
    private List<Author> createdByAuthors;
    public Integer getBookLendByStudentID() {
        return BookLendByStudentID;
    }

    public void setBookLendByStudentID(Integer bookLendByStudentID) {
        BookLendByStudentID = bookLendByStudentID;
    }
    private Integer BookLendByStudentID;

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

    public Date getLendDate() {
        return lendDate;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    public Book()
    {
        title_of_Book="";
        numberOfPages=0;
        language="";
        subject="";
        fiction=false;
        BookLendByStudentID = null;
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
