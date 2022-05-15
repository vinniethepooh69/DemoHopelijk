package EJB;

import classes.Admin;
import classes.Author;
import classes.Book;
import jakarta.ejb.Stateful;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;


@Named
@Stateful
@SessionScoped
public class AdminStatefullSessionBean implements Serializable {


    @PersistenceContext(unitName = "hellodemo")
    EntityManager em;

    private Admin admin;
    private Book book;
    private Author author;


    private boolean continueForm;

    public boolean isContinueForm() {
        return continueForm;
    }

    public void setContinueForm(boolean continueForm) {
        this.continueForm = continueForm;
    }

    public AdminStatefullSessionBean() {
    }
    public String retrieveBookFormTitle()
    {
        if(continueForm == true)
        {
            return "Create a book step 2/2";
        }
        else{return "Create a book step 1/2";}
    }

    public List<Author> getAllAuthors()
    {
        TypedQuery<Author> query = em.createNamedQuery("getAllAuthors",Author.class);
        return  query.getResultList();
    }
    public void assignAuthorToBook(int authorID)
    {
        book.addAuthor(em.createNamedQuery("findAuthorByID",Author.class).setParameter("1",authorID).getSingleResult());
    }
    public Admin getAdmin() {
        return admin;
    }

    public void persistBook()
    {
        em.persist(book);
        em.flush();
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
