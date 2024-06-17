package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.Beans.UserBean;
import storage.Models.UserModel;
import utils.Security;

import java.io.IOException;

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
            //new UserModel().doSave(new UserBean());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
