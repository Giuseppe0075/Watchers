package com.watchers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.ImageBeen;

import java.io.IOException;
@WebServlet(name = "imageServlet", value = "/get-image")
public class ImageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String imageId = request.getParameter("id");
        if (imageId == null) {
            response.sendError(404);
            return;
        }

        try {
            ImageBeen imageBeen = ImageBeen.retriveById(ImageBeen.class, imageId);
            if (imageBeen != null) {
                //TODO store into the database also the extension
                response.setContentType("image/jpeg");
                // TODO Convert from BASE64 to text
                response.getOutputStream().write(imageBeen.getImage());
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
