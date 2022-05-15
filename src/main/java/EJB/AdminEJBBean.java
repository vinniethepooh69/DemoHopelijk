package EJB;

import classes.Admin;
import classes.Author;
import classes.Book;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Named
@Stateless
public class AdminEJBBean {
    @PersistenceContext(unitName = "hellodemo")
    EntityManager em;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    private String test="test";
    public AdminEJBBean()
    {



    }
    public boolean PersistAdmin(Admin admin)
    {
        test = "yolo";
        Query query = em.createNamedQuery("findAdminWithParam",Admin.class);
        query.setParameter("fname",admin.getPersonUserNumber());
        if(query.getResultList().size() !=0)
        {
            return false;
        }
        else{

            em.persist(admin);
            em.flush();
            return true;
        }


    }

    public boolean persistAuthor(Author author)
    {
        //Query query = em.createNamedQuery("findAuthorWithParam",Author.class);
        //query.setParameter("fname",author.getPersonUserNumber());
        //if(query.getResultList().size() !=0)
        //{
        //    return false;
        //}
        //else{

            em.persist(author);
            em.flush();
            return true;
        //}



    }
    public Admin Login_Admin(String password, String adminNumber)
    {
        Query query = em.createNamedQuery("findAdminWithParam",Admin.class);
        query.setParameter("fname",adminNumber);
        if(query.getResultList().size() !=0)
        {
            Admin hulpAdmin = (Admin) query.getSingleResult();
            if(hulpAdmin.getPassword().equals(password))
            {
                return hulpAdmin;
            }
            else{
                return new Admin();}

        }
        else{
            return new Admin();}




        //em.find(Student.class,)
    }
    public void createBook(Book book)
    {
        test = "guccii";
        em.persist(book);
        em.flush();
    }


}
