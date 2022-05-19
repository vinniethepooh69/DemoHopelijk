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

import java.util.List;
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
    private String inlogAdmin;
    private String inlogPassword;
    private String inlogSecurityCode;



    public String createAuthor()
    {
        boolean b = AdminEJBBean.persistAuthor(author);
        if(b==true)
        {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "author added","author is succesfully added"));
            return "goodInlogAdmin.xhtml";

        }
        else{
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","AUTHOR is ARE NOT REGISTERED"));
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Make sure author usernummer is a unique value","Make sure author usernummer is a unique value"));
            return "goodInlogAdmin.xhtml";


        }


    }

    public void addAdmin() {

        boolean b = AdminEJBBean.PersistAdmin(admin);
        if(b==true)
        {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Admin added","Admin is succesfully added"));
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Please return to home page before logging in","Please return to home page before logging in"));

        }
        else{
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","YOU ARE NOT REGISTERED"));
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Make sure Adminnumber is a unique value","Make sure Adminnumber is a unique value"));


        }
    }
    public String loginAdmin() {
        admin = AdminEJBBean.Login_Admin(inlogAdmin,inlogPassword,inlogSecurityCode);
        if(admin.getPersonUserNumber().equals(""))
        {
            admin.resetAdminValues();
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login UNSUCCESFULL","LOGIN WAS UNSUCCESFULL"));
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "TRY AGAIN " , "Return and try again"));

            return "";
        }
        else{
            adminStatefullSessionBean.setAdmin(admin);
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "LOGIN SUCCESFUL" , "WELCOME"));

            //student.resetStudentValues();
            return "goodInlogAdmin.xhtml";
        }




    }
    public void NextPage()
    {
        adminStatefullSessionBean.setBook(book);
        adminStatefullSessionBean.setContinueForm(true);

    }

    public List<Author> RetrieveAvailableAuthors()
    {
        return adminStatefullSessionBean.getUnAttachedAuthors();
    }

    public List<Author> RetrieveAssignedAuthors()
    {
        return adminStatefullSessionBean.getAttachedAuthors();
    }




    public String persistBook()
    {
        boolean b= adminStatefullSessionBean.persistBook();
        if(b==true)
        {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Book added","Book is succesfully added"));

        }
        else{
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Book is NOT REGISTERED","Book is NOT REGISTERED"));
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "An instance using this title: " +book.getTitle_of_Book()+  "already existed","Return and try again"));


        }
        book = new Book();
        adminStatefullSessionBean.resetValuesregisterBook();

        return "goodInlogAdmin.xhtml";
    }
    public List<Author> getAllAuthors()
    {
        return adminStatefullSessionBean.getAllAuthors();
    }
    public void assignAuthorToBook(int authorID)
    {
        adminStatefullSessionBean.assignAuthorToBook(authorID);

    }
    public void UnassignAuthorToBook(int authorID)
    {
        adminStatefullSessionBean.UnassignAuthorToBook(authorID);

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


    public String getInlogSecurityCode() {
        return inlogSecurityCode;
    }

    public void setInlogSecurityCode(String inlogSecurityCode) {
        this.inlogSecurityCode = inlogSecurityCode;
    }
}