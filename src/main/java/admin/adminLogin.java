package admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tinylog.Logger;
import storage.AdminBean;

import java.io.IOException;
import static utils.Security.sanitizeInput;

@WebServlet(name = "adminLoginServlet", value = "/admin-login-servlet")
public class adminLogin extends HttpServlet {
    //TODO Add password hashing

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = sanitizeInput(req.getParameter("email"));
        String password = sanitizeInput(req.getParameter("password"));

        HttpSession session1 = req.getSession(false);
        if( session1 != null && session1.getAttribute("admin") != null && session1.getAttribute("admin").equals(true)){
            resp.sendRedirect(req.getContextPath()  + "/admin/adminPage.jsp");
        }

        // check if authenticated
        if(authenticate(email, password)){
            Logger.info("Login of " + email);
            HttpSession session = req.getSession();
            session.setAttribute("admin", true);
            session.setMaxInactiveInterval(1000);
            resp.sendRedirect(req.getContextPath() +"/admin/adminPage.jsp");
        }else{
            Logger.info("Login fallita, email: " + email);
            resp.sendRedirect(req.getContextPath() + "/adminLogin.jsp");
        }

    }


    private static boolean authenticate(String email, String psw){
        return AdminBean.retriveAll(AdminBean.class).stream()
                .anyMatch(adminBean -> adminBean.getEmail().equals(email) && adminBean.getPassword().equals(psw));
    }
}
