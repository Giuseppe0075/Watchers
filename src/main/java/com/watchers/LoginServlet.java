package com.watchers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tinylog.Logger;
import storage.Beans.UserBean;
import storage.Models.UserModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet(name = "LoginServlet", value = "/user/login")
public class LoginServlet extends HttpServlet {
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel userModel = new UserModel();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Logger.debug("email: " + email + " password: " + password);

        if (email == null || password == null) {
            doGet(req, resp);
        }
        try {
            Optional<UserBean> user = userModel.doRetrieveByCond("WHERE email=? AND psw=?", List.of(email,password)).stream().findFirst();
            if (user.isPresent()){
                HttpSession session = req.getSession();
                session.setAttribute("user",user.get().getId());
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            } else {
                resp.sendRedirect(req.getContextPath() + "/user/login.jsp");
            }
        } catch (Exception e) {
            Logger.warn(e);
        }
    }

    public void destroy() {
    }

}
