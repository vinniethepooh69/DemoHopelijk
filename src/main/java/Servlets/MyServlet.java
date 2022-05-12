package Servlets;

import BackingBeans.StudentFormBean;
import EJB.MySessionBean;
import EJB.StudentEJBBean;
import classes.Student;
import jakarta.ejb.EJB;
import jakarta.persistence.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "MyHelloPage", urlPatterns = "/hello")
public class MyServlet extends HttpServlet {
    @EJB
    MySessionBean bean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter writer = response.getWriter();
    writer.println("<!DOCTYPE html>");
    writer.println("<html><head><title>yur</title></head>\n</body>");
    writer.println("<h1>hello</h1>");
    writer.println("<p>the result is"+ bean.doSomething(10,10)+"</p>");
    writer.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
