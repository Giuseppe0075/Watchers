package Servlet;
import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.ImageBean;
import storage.ImageModel;

@WebServlet(name = "getPhoto", value="/getPhoto")
public class getPhoto extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        ImageModel im = new ImageModel();
        try {
            FileInputStream fis = new FileInputStream("Immagini/mucca blu.jpg");
            ImageBean img = new ImageBean(4,1, fis.readAllBytes());
            im.doSave(img);
            ImageBean image = im.doRetrieveByKey("4", "1");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
