package Filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebFilter("/webApplication/inlogAdmin.xhtml")
public class RequestLoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        request.setAttribute("HasAttemptedAdmin",true);

        FileWriter fw = new FileWriter("C://Users//olivi/IdeaProjects/logger.txt",  true);
        PrintWriter out = new PrintWriter(fw);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        filterChain.doFilter(request,response);

        // Append the name the time and ip address to the file
        out.println("attempt has been made to login as admin at: " + dtf.format(now) + " by: " + request.getRemoteAddr());
        // Close the file
        out.close();
    }

    @Override
    public void destroy() {
        // nothing special
    }

}

