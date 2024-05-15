package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import storage.Beans.UserBean;
import storage.Models.UserModel;
import user.SignupDataForm;
import utils.Security;

import java.io.IOException;
import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "registrationServlet", value = "/signup")
public class registrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //CHECK FOR CSRF
        if(!Security.checkCSRFToken(request.getSession(false), request.getParameter("CSRF-Token"))){
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return;
        }
        // GET INPUT
        try {
            SignupDataForm dataForm = new SignupDataForm(request);

            new UserModel().doSave(new UserBean(dataForm));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
