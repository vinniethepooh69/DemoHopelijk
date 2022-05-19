package EJB;

import classes.Book;
import classes.Student;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Typed;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Named
@Stateless
public class StudentEJBBean {
    @PersistenceContext(unitName = "hellodemo")
    EntityManager em;



    public StudentEJBBean()
    {

    }
    public Book retrievefirstbook()
    {
        TypedQuery<Book> query = em.createNamedQuery("findBookbyBookID",Book.class).setParameter("1",2);
        return query.getSingleResult();
    }

    public TypedQuery<Book> returnBookQuery(int bookID)
    {
        return em.createNamedQuery("findBookbyBookID",Book.class).setParameter("1",bookID);

    }
    public boolean PersistStudent(Student student) {

        Query query = em.createNamedQuery("findStudentByUserNumber", Student.class);
        query.setParameter("fname", student.getPersonUserNumber());
        if (query.getResultList().size() != 0) {
            return false;
        } else {

            em.persist(student);
            em.flush();
            return true;


        }
    }

    public List<Integer> getBookIDValues()
    {
        return em.createNamedQuery("findBookIDValues").getResultList();
    }
    public Student Login_student(String password, String studentNumber)
    {
        Query query = em.createNamedQuery("findStudentByUserNumber",Student.class);
        query.setParameter("fname",studentNumber);
        if(query.getResultList().size() !=0)
        {
            Student hulpstudent = (Student) query.getSingleResult();
            if((hulpstudent.getPassword()).equals(password))
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
