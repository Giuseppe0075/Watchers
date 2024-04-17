package admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "adminLoginServlet", value = "/admin-login-servlet")
public class adminLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println("email: " + email + " \npassword: " + password);
        HttpSession session1 = req.getSession(false);
        if( session1 != null && session1.getAttribute("admin") != null && session1.getAttribute("admin").equals(true)){
            resp.sendRedirect(req.getContextPath()  + "/admin/adminPage.jsp");
        }
        // check if authenticated
        if(email.equals("root@gmail.com") && password.equals("root")){
            System.out.println("login buona");
            HttpSession session = req.getSession();
            session.setAttribute("admin", true);
            session.setMaxInactiveInterval(1000); // TODO choose a time
            resp.sendRedirect(req.getContextPath() +"/admin/adminPage.jsp");
        }else{
            System.out.println("Login fallita");
            System.out.println(req.getContextPath());
            resp.sendRedirect(req.getContextPath() + "/adminLogin.jsp");
        }

    }
}
