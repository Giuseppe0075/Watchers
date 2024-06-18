package servlets;

import FavouritesCart.FavouritesCart;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tinylog.Logger;
import storage.Beans.UserBean;
import storage.Models.UserModel;
import utils.Security;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel userModel = new UserModel();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            // Check if the user exists
            Collection<UserBean> userBeans =  userModel.doRetrieveByCond("WHERE email = ?", List.of(email));
            if (userBeans.isEmpty()) {
                req.setAttribute("loginError", "1");
                req.getRequestDispatcher("/user/login.jsp").forward(req, resp);
                return;
            }
            UserBean userBean = userBeans.iterator().next();

            // Check if the password is correct
            byte[] hashedPassword = userBean.getPsw();
            if(Security.verifyPassword(password, hashedPassword)){
                HttpSession session = req.getSession();
                session.setAttribute("user", userBean.getId());
                session.setAttribute("admin", userBean.getAdmin());
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            }
            else{
                req.setAttribute("loginError", "1");
                req.getRequestDispatcher("/user/login.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            Logger.warn(e);
            req.setAttribute("loginError", "1");
            req.getRequestDispatcher("/user/login.jsp").forward(req, resp);
        }
    }
}
