package BackingBeans;

import EJB.StudentEJBBean;
import classes.Book;
import classes.Student;
import EJB.StudentStatefullSessionBean;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Objects;

@Named
@RequestScoped
public class StudentFormBean {
    @Inject
    private StudentEJBBean studentEJBBean;

    @Inject
    private StudentStatefullSessionBean studentStatefullSessionBean;

    public boolean isReportproblem() {
        return reportproblem;
    }

    public void setReportproblem(boolean reportproblem) {
        this.reportproblem = reportproblem;
    }

    private boolean reportproblem;

    private Student student = new Student();


    public int getBookIDforBrowsing() {
        return bookIDforBrowsing;
    }

    public void setBookIDforBrowsing(int bookIDforBrowsing) {
        this.bookIDforBrowsing = bookIDforBrowsing;
    }

    private int bookIDforBrowsing;

    private String inlogStudent;
    private String inlogPassword;

    public void changestudent()
    {

    }



    public void addStudent() {
        boolean b = studentEJBBean.PersistStudent(student);
        if(b==true)
        {
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "student added","Student is succesfully added"));
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Please return to home page before logging in","Student is succesfully added"));

        }
        else{
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","YOU ARE NOT REGISTERED"));
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Make sure studentnumber is a unique value","Student is succesfully added"));


        }
    }


    public void previousBook() {
        //book = studentEJBBean.retrievefirstbook();

        //Book hulpboek = studentStatefullSessionBean.testquery();
        //inlogPassword="gelukt";
        studentStatefullSessionBean.retrieveBook(-1);

    }
    public void nextBook()
    {
        //book = studentEJBBean.retrievefirstbook();

        //Book hulpboek = studentStatefullSessionBean.testquery();
        //inlogPassword="gelukt";
        studentStatefullSessionBean.retrieveBook(1);

    }
    public String loginStudent() {
        student = studentEJBBean.Login_student(inlogPassword,inlogStudent);

        if(student.getPersonUserNumber().equals("r0000000"))
        {
            student.resetStudentValues();

            return "badInlog.xhtml";
        }
        else{
            studentStatefullSessionBean.assignStudent(student.getPersonID());
            studentStatefullSessionBean.setLowestBookID(studentEJBBean.returnlowestbookid());
            studentStatefullSessionBean.initialisation();
            //student.resetStudentValues();
            return "goodInlogStudent.xhtml";
        }

        //return "test2.xhtml";


    }

    public void retrieveAllLendBooksByStudent()
    {
        inlogPassword = "hoera";

        studentStatefullSessionBean.retrieveAllLendBooksByUser();
    }

    public void returnBook(int index)
    {
        studentStatefullSessionBean.returnBook(index);
    }

    public String registerLendOfBook()
    {
        studentStatefullSessionBean.registerLendOfBook();
        return "succesfulBookLend.xhtml";

    }

    public void testfunctie()
    {
        studentStatefullSessionBean.testfunctie();
    }

    public void resetStudent()
    {
        student.resetStudentValues();
    }
    public void resetInlog()
    {
        inlogStudent="";
        inlogPassword="";
    }

    public StudentEJBBean getStudentEJBBean() {
        return studentEJBBean;
    }
    public StudentStatefullSessionBean getStudentStatefullSessionBean() {
        return studentStatefullSessionBean;
    }

    public void setStudentStatefullSessionBean(StudentStatefullSessionBean studentStatefullSessionBean) {
        this.studentStatefullSessionBean = studentStatefullSessionBean;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student studentvalue) {
        student = studentvalue;
    }



    public String getInlogStudent() {
        return inlogStudent;
    }

    public void setInlogStudent(String inlogStudent) {
        this.inlogStudent = inlogStudent;
    }

    public String getInlogPassword() {
        return inlogPassword;
    }

    public void setInlogPassword(String inlogPassword) {
        this.inlogPassword = inlogPassword;
    }
// Getters, setters
}