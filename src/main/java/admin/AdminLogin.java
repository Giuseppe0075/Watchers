package admin;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tinylog.Logger;
import storage.Beans.AdminBean;
import storage.Models.AdminModel;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static utils.Security.sanitizeInput;

@WebServlet(name = "adminLoginServlet", value = "/admin-login-servlet")
public class AdminLogin extends HttpServlet {
    //TODO Add password hashing

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String email = sanitizeInput(req.getParameter("email"));
        String password = sanitizeInput(req.getParameter("password"));

        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("admin") != null && session.getAttribute("admin").equals(true)) {
            resp.sendRedirect(req.getContextPath() + "/admin/adminPage.jsp");
        }

        // check if authenticated
        if (authenticate(email, password)) {
            Logger.info("Login of " + email);
            session = req.getSession();
            session.setAttribute("admin", true);
            session.setMaxInactiveInterval(1000);
            resp.sendRedirect(req.getContextPath() + "/admin/adminPage.jsp");
        } else {
            Logger.info("Login fallita, email: " + email);
            resp.sendRedirect(req.getContextPath() + "/adminLogin.jsp");
        }

    }


    //TODO: Add password hashing
    private static boolean authenticate(String email, String psw) {
        AdminModel adminModel = new AdminModel();
        Collection<AdminBean> admins = null;
        try {
            admins = adminModel.doRetrieveByCond("WHERE email = ?", List.of(email) );
        } catch (Exception e) {
            return false;
        }
        if(admins.isEmpty()){
            return false;
        }
        AdminBean admin = admins.iterator().next();
        return admin.getPsw().equals(psw);
    }
}