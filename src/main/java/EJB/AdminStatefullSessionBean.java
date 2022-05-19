package EJB;

import classes.Admin;
import classes.Author;
import classes.Book;
import jakarta.ejb.Stateful;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named
@Stateful
@SessionScoped
public class AdminStatefullSessionBean implements Serializable {




    private List<Author> AttachedAuthors=new ArrayList<Author>();


    private List<Author> UnAttachedAuthors = new ArrayList<Author>();

    private Admin admin;
    private Book book;
    private Author author;


    @Inject
    private AdminEJBBean adminEJBBean;
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
        else{
            setUnAttachedAuthors(adminEJBBean.getAllAuthors());
            return "Create a book step 1/2";}
    }

    public List<Author> getAllAuthors()
    {
        return  adminEJBBean.getAllAuthors();
    }


    public void assignAuthorToBook(int authorID)
    {
        Author localAuthor =adminEJBBean.RetrieveAuthorByAuthorID(authorID);
        AttachedAuthors.add(localAuthor);
        UnAttachedAuthors.remove(localAuthor);
        book.addAuthor(localAuthor);
    }
    public void UnassignAuthorToBook(int authorID)
    {
        Author localAuthor =adminEJBBean.RetrieveAuthorByAuthorID(authorID);
        AttachedAuthors.remove(localAuthor);
        UnAttachedAuthors.add(localAuthor);
        book.removeAuthor(localAuthor);
    }
    public boolean persistBook()
    {
        return adminEJBBean.persistBook(book);
    }

    public void resetValuesregisterBook()
    {
        UnAttachedAuthors = adminEJBBean.getAllAuthors();
        AttachedAuthors = new ArrayList<Author>();
        book = new Book();
        continueForm = false;
    }

    public List<Author> getUnAttachedAuthors() {
        return UnAttachedAuthors;
    }

    public void setUnAttachedAuthors(List<Author> unAttachedAuthors) {
        UnAttachedAuthors = unAttachedAuthors;
    }
    public List<Author> getAttachedAuthors() {
        return AttachedAuthors;
    }

    public void setAttachedAuthors(List<Author> attachedAuthors) {
        AttachedAuthors = attachedAuthors;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
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
