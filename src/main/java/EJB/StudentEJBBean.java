package EJB;

import classes.Book;
import classes.Student;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Named
@Stateless
public class StudentEJBBean {
    @PersistenceContext(unitName = "hellodemo")
    EntityManager em;


    public int returnlowestbookid()
    {
        return (int) em.createNamedQuery("findMinBookID").getSingleResult();

    }
    public StudentEJBBean()
    {

    }

    public boolean PersistStudent(Student student)
    {
        /*
        Query query = em.createNamedQuery("findStudentWithParam",Student.class);
        query.setParameter("fname",student.getPersonUserNumber());
        if(query.getResultList().size() !=0)
        {
            return false;
        }
        else{*/

            em.persist(student);
            em.flush();
            return true;



    }
    public Student Login_student(String password, String studentNumber)
    {
        Query query = em.createNamedQuery("findStudentWithParam",Student.class);
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
