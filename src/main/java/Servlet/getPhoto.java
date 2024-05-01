package Servlet;
import java.io.*;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.Beans.ImageBean;
import storage.Models.ImageModel;

@WebServlet(name = "getPhoto", value="/getPhoto")
public class getPhoto extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ImageModel im = new ImageModel();
        try {
            List<Object> keys = List.of(req.getParameter("id"), req.getParameter("watch"));
            ImageBean image = im.doRetrieveByKey(keys);
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
