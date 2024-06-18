package  servlets;
import org.tinylog.Logger;
import storage.Beans.ImageBean;
import storage.Models.ImageModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/deleteImage")
public class DeleteImageAdminServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the image ID and watch ID from the request parameters
        Long imageId = Long.valueOf(request.getParameter("image"));
        Long watchId = Long.valueOf(request.getParameter("watch"));

        // Create ImageBean and set its properties
        ImageBean imageBean = new ImageBean();
        imageBean.setId(imageId);
        imageBean.setWatch(watchId);

        Logger.info("Deleting image with ID: " + imageId + " for watch with ID: " + watchId);

        // Delete the image from the database
        ImageModel imageModel = new ImageModel();
        try {
            imageModel.doDelete(imageBean);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete image");
        }
        response.sendRedirect(request.getContextPath() + "/admin/modifyWatch.jsp?id="+ watchId);
    }
}
