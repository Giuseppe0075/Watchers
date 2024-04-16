package com.watchers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        request.isRequestedSessionIdValid();

        if(session == null) {
            System.out.println("NO SESSIONE");
        }
        else{
            System.out.println("Loggato");
            System.out.println("sessione creata: " + session.getCreationTime());
        }
        session.setAttribute("username", "marco");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    public void destroy() {
    }

    public static void main(String[] args) {

    }
}
