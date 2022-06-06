package EJB;

import classes.Logs;
import classes.Student;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

//eager initialization
//@Startup
//@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
//@Singleton




public class LoggerBeanBean {
    @PersistenceContext(unitName = "hellodemo")
    EntityManager em;



    @Inject
    StudentStatefullSessionBean studentStatefullSessionBean;

    private String status;
    private Logs log;

    /*@PostConstruct
    void postconstructmethod() {
    log = new Logs();
    }*/


    public LoggerBeanBean() {
    }
    //@Lock(LockType.WRITE)
    @AroundInvoke
    public Object addLog(InvocationContext ic){
        log = new Logs();
        Object[] parameters = ic.getParameters();


        String param = (String) parameters[0];
        String test = "Succesfull login of student:" + param;
        studentStatefullSessionBean.setTeststring(test);
        log.setName(test);

        log.currentTime();
        em.persist(log);
        em.flush();
        return null;
    }
}