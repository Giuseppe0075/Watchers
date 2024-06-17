package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.Beans.UserBean;
import storage.Models.UserModel;
import utils.Security;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

@WebServlet(name = "registrationServlet", value = "/signup")
public class registrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //CHECK FOR CSRF
//        if(!Security.checkCSRFToken(request.getSession(false), request.getParameter("CSRF-Token"))){
//            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
//            return;
//        }
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        String road = request.getParameter("road");
        String civic_number = request.getParameter("civic_number");
        String city = request.getParameter("city");
        String cap = request.getParameter("cap");

        UserBean user = new UserBean(email, password, name, surname, birthday, road, civic_number, city, cap);
        // GET INPUT
        try {
            new UserModel().doSave(user);
            response.sendRedirect("/login.jsp");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
