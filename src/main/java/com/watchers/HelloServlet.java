package com.watchers;

import java.io.*;
import java.util.Arrays;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        System.out.println("Init");
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        ServletOutputStream out = response.getOutputStream();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
        request.getParameterMap().forEach((k,v) -> System.out.println(k+ " : " + Arrays.toString(v)));
        response.setHeader("test","ciao");

        System.out.println(response.getHeader("test"));

        request.authenticate(response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        ServletOutputStream out = response.getOutputStream();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
        request.getParameterMap().forEach((k,v) -> System.out.println(k+ " : " + Arrays.toString(v)));
        response.setHeader("test","ciao");

        System.out.println(response.getHeader("test"));

        request.authenticate(response);
    }

    public void destroy() {
    }
}