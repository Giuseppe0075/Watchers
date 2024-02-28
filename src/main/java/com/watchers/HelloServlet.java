package com.watchers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        System.out.println("Init");
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.encodeRedirectURL("\"C:\\\\Users\\\\aless\\\\OneDrive\\\\Documenti\\\\Università\\\\Web\\\\src\\\\main\\\\webapp\\\\WEB-INF\\\\test.jsp\"");

        // Hello
        PrintWriter out = response.getWriter();

        List<String> lines = Files.readAllLines(Path.of("C:\\Users\\aless\\OneDrive\\Documenti\\Università\\Web\\src\\main\\webapp\\WEB-INF\\test.jsp"));
        for(String line: lines){
            //out.println(line);
        }
        out.flush();
        //out.println("<html><body>");
        //out.println("<h1>" + message + "</h1>");
        //out.println("</body></html>");
    }

    public void destroy() {
    }
}