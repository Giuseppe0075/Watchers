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

            ImageBean image = im.doRetrieveByKey(req.getParameter("id"), req.getParameter("watch"));
            resp.setContentType("image/*");
            ServletOutputStream out = resp.getOutputStream();
            out.write(image.getImage());
            out.close();
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
