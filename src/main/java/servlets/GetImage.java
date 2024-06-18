package servlets;
import java.io.*;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.Beans.ImageBean;
import storage.Models.ImageModel;

@WebServlet(name = "GetImage", value="/getImage")
public class GetImage extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ImageModel im = new ImageModel();
        try {
            Long user = Long.valueOf(req.getParameter("id"));
            Long watch = Long.valueOf(req.getParameter("watch"));
            ImageBean image = im.doRetrieveByKey(List.of(user, watch));
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