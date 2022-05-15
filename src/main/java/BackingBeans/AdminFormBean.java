package BackingBeans;

import classes.Admin;
import classes.Author;
import classes.Book;
import classes.Student;
import EJB.AdminEJBBean;
import EJB.AdminStatefullSessionBean;
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

    @Inject
    private AdminStatefullSessionBean adminStatefullSessionBean;

    public AdminStatefullSessionBean getAdminStatefullSessionBean() {
        return adminStatefullSessionBean;
    }

    public void setAdminStatefullSessionBean(AdminStatefullSessionBean adminStatefullSessionBean) {
        this.adminStatefullSessionBean = adminStatefullSessionBean;
    }
//@Inject
    //private StudentStatefullSessionBean studentStatefullSessionBean;



    private Admin admin = new Admin();

    private Book book= new Book();

    private Author author = new Author();
    private String inlogAdmin="test";
    private String inlogPassword;

    public void createBook()
    {
        inlogAdmin="ietsal";

        AdminEJBBean.createBook(book);
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "student added","This amazing book has been added to our collection, nice job!"));
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Please return to home page before logging in","Let's see how long it takes before it gets reserved :p"));

    }

    public void createAuthor()
    {
        boolean b = AdminEJBBean.persistAuthor(author);
        if(b==true)
        {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "author added","author is succesfully added"));

        }
        else{
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","AUTHOR is ARE NOT REGISTERED"));
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Make sure author usernummer is a unique value","Make sure author usernummer is a unique value"));


        }


    }

    public void addAdmin() {
        inlogAdmin = "jaj?";

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
            adminStatefullSessionBean.setAdmin(admin);
            //student.resetStudentValues();
            return "goodInlogAdmin.xhtml";
        }


        //return "test2.xhtml";


    }
    public void NextPage()
    {
        adminStatefullSessionBean.setBook(book);
        adminStatefullSessionBean.setContinueForm(true);

    }

    public void persistBook()
    {
        adminStatefullSessionBean.persistBook();
    }
    public void assignAuthorToBook(int authorID)
    {
        adminStatefullSessionBean.assignAuthorToBook(authorID);

    }
    public String testfunctie()
    {
        resetInlog();
        return "Home.xhtml";
    }
    public void resetInlog()
    {
        inlogAdmin="";
        inlogPassword="";
    }


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }



    // Getters, setters


}