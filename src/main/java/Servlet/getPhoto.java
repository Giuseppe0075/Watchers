package Servlet;
import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.ImageBean;
import storage.ImageModel;

@WebServlet(name = "getPhoto", value="/getPhoto")
public class getPhoto extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ImageModel im = new ImageModel();
        try {
            ImageBean image = im.doRetrieveByKey("1", "1");
            System.out.println("Immagine letta correttamente");
            resp.setContentType("image/jpeg");
            ServletOutputStream out = resp.getOutputStream();
            out.write(image.getImage());
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
