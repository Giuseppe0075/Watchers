package Control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.Beans.UserBean;
import Model.Models.UserModel;
import utils.Security;

import java.io.IOException;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "RegistrationServlet", value = "/signup")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        String road = request.getParameter("road");
        String civic_number = request.getParameter("civic_number");
        String city = request.getParameter("city");
        String cap = request.getParameter("cap");

        byte[] hashedPassword = Security.hashPassword(password);

        UserBean user = new UserBean(email, hashedPassword, name, surname, birthday, road, civic_number, city, cap);
        // GET INPUT
        try {
            UserModel userModel = new UserModel();
            Collection<UserBean> beans = userModel.doRetrieveByCond("WHERE email = ?", List.of(email));
            if(!beans.isEmpty()) {
                request.setAttribute("name", name);
                request.setAttribute("surname", surname);
                request.setAttribute("birthday", birthday);
                request.setAttribute("road", road);
                request.setAttribute("civic_number", civic_number);
                request.setAttribute("city", city);
                request.setAttribute("cap", cap);
                request.setAttribute("registrationError", "Email already in use");
                request.getRequestDispatcher("/user/signup.jsp").forward(request, response);
            }
            else {
                userModel.doSave(user);
                response.sendRedirect(request.getContextPath() + "/user/login.jsp?success=true");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
