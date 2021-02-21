package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Create a session.
        HttpSession session = request.getSession();
        
        /**
         * If the parameter "logout" exists, invalidate the session and display 
         * a message that the user has successfully logged out.
         */
        if (request.getParameter("logout") != null) {
            session.invalidate();
            request.setAttribute("message", "You have successfully logged out.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        else {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Create a service class.
        AccountService account = new AccountService();
        
        /**
         * Validate that username and password are not empty.
         */
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if(username.equals("") || password.equals("")){
            request.setAttribute("message", "Invalid Login");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        else if (username == null || password == null) {
            request.setAttribute("message", "Invalid Login");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        else {
            /* Passes the username and password parameters to the login() method
               of a service class called AccountService. */
            User user = account.login(username, password);
            
            /* If login() returns a non-null value, store the username in a
               session variable and redirect the user to the home url.*/
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("home");
            }
            /* If the authenication parameters are invalid, display an
               appropriate error message, keeping the textboxes filled in with 
               what the user had previously entered and forward the user to 
               login.jsp. */
            else{
                request.setAttribute("message", "Invalid Login");
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
        }
    }
}