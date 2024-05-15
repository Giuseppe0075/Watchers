package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import user.SignupDataForm;

import java.io.IOException;
import java.lang.reflect.Field;

@WebServlet(name = "registrationServlet", value = "/registration")
public class registrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //CHECK FOR CSRF

        // GET INPUT
        SignupDataForm dataForm = new SignupDataForm(request);




        // SANITIZE AND CHECK INPUT

        // SAVE NEW USER




    }
}
