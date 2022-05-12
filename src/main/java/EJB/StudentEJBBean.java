package EJB;

import classes.Student;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Named
@Stateless
public class StudentEJBBean {
    @PersistenceContext(unitName = "hellodemo")
    EntityManager em;

    public StudentEJBBean()
    {

    }
    public void PersistStudent(Student student)
    {
        em.persist(student);
        em.flush();
    }
    public Student Login_student(String password, String studentNumber)
    {
        Query query = em.createNamedQuery("findWithParam",Student.class);
        query.setParameter("fname",studentNumber);
        if(query.getResultList().size() !=0)
        {
            Student hulpstudent = (Student) query.getSingleResult();
            if(hulpstudent.getPassword().equals(password))
            {
                return hulpstudent;
                }
            else{
                return new Student();}

        }
        else{
            return new Student();}




        //em.find(Student.class,)
    }


}
