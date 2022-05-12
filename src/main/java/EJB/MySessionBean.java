package EJB;

import classes.Student;
import classes.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@jakarta.ejb.Stateless(name = "MySessionBeanEJB")
public class MySessionBean {

    @PersistenceContext(unitName = "hellodemo")
    EntityManager em;


    public MySessionBean() {

    }
    public int doSomething(int a,int b)
    {
        return a+b;
    }

    public void persistClient(Student student) {


        em.persist(student);

    }
}
