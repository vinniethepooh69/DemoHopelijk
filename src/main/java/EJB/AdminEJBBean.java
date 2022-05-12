package EJB;

import classes.Admin;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Named
@Stateless
public class AdminEJBBean {
    @PersistenceContext(unitName = "hellodemo")
    EntityManager em;

    public AdminEJBBean()
    {

    }
    public boolean PersistAdmin(Admin admin)
    {
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


}
