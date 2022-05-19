package EJB;

import classes.Admin;
import classes.Author;
import classes.Book;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Named
@Stateless
public class AdminEJBBean {
    @PersistenceContext(unitName = "hellodemo")
    EntityManager em;

    public AdminEJBBean()
    {



    }
    public boolean PersistAdmin(Admin admin) {

        Query query = em.createNamedQuery("findAdminWithAdminUserNumber", Admin.class);
        query.setParameter("fname", admin.getPersonUserNumber());
        if (query.getResultList().size() != 0) {
            return false;
        } else {

            em.persist(admin);
            em.flush();
            return true;


        }
    }

    public Author RetrieveAuthorByAuthorID(int authorID)
    {
        return em.createNamedQuery("findAuthorByID",Author.class).setParameter("1",authorID).getSingleResult();
    }
    public List<Author> getAllAuthors()
    {
        TypedQuery<Author> query = em.createNamedQuery("getAllAuthors",Author.class);
        return  query.getResultList();
    }


    public boolean persistBook(Book book)
    {
        Query query = em.createNamedQuery("findBookbyBookTitle",Book.class);
        query.setParameter("fname",book.getTitle_of_Book());
        if(query.getResultList().size() !=0)
        {
            return false;
        }
        else{

            em.persist(book);
            em.flush();
            return true;
        }



    }

    public boolean persistAuthor(Author author)
    {
        Query query = em.createNamedQuery("findAuthorByUserNumber",Author.class);
        query.setParameter("fname",author.getPersonUserNumber());
        if(query.getResultList().size() !=0)
        {
            return false;
        }
        else{

            em.persist(author);
            em.flush();
            return true;
        }



    }
    public Admin Login_Admin(String adminNumber, String password, String SecurityCode)
    {
        Query query = em.createNamedQuery("findAdminWithAdminUserNumber",Admin.class);
        query.setParameter("fname",adminNumber);
        if(query.getResultList().size() !=0)
        {
            Admin hulpAdmin = (Admin) query.getSingleResult();
            if((hulpAdmin.getPassword()).equals(password) && hulpAdmin.getSecurityCode().equals(SecurityCode))
            {
                return hulpAdmin;
            }
            else
            {     return new Admin();
            }

        }
        else
        {       return new Admin();
        }




        //em.find(Student.class,)
    }

}
