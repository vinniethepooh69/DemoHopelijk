package BackingBeans;

import classes.Admin;
import classes.Student;
import EJB.AdminEJBBean;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Objects;

@Named
@RequestScoped
public class AdminFormBean {
    @Inject
    private AdminEJBBean AdminEJBBean;


//@Inject
    //private StudentStatefullSessionBean studentStatefullSessionBean;


    private boolean reportproblem;

    private Admin admin = new Admin();

    private String inlogAdmin;
    private String inlogPassword;


    public void addAdmin() {
        boolean b = AdminEJBBean.PersistAdmin(admin);
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
    public String loginAdmin() {
        admin = AdminEJBBean.Login_Admin(inlogPassword,inlogAdmin);

        if(admin.getPersonUserNumber().equals("r0000000"))
        {
            admin.resetAdminValues();

            return "badInlog.xhtml";
        }
        else{
            //studentStatefullSessionBean.assignStudent(student);
            //student.resetStudentValues();
            return "goodInlogStudent.xhtml";
        }

        //return "test2.xhtml";


    }
    public void resetInlog()
    {
        inlogAdmin="";
        inlogPassword="";
    }









    public String getInlogPassword() {
        return inlogPassword;
    }

    public void setInlogPassword(String inlogPassword) {
        this.inlogPassword = inlogPassword;
    }


    public AdminEJBBean getAdminEJBBean() {
        return AdminEJBBean;
    }

    public void setAdminEJBBean(AdminEJBBean adminEJBBean) {
        AdminEJBBean = adminEJBBean;
    }

    public boolean isReportproblem() {
        return reportproblem;
    }

    public void setReportproblem(boolean reportproblem) {
        this.reportproblem = reportproblem;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getInlogAdmin() {
        return inlogAdmin;
    }

    public void setInlogAdmin(String inlogAdmin) {
        this.inlogAdmin = inlogAdmin;
    }
    // Getters, setters
}