package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import storage.Beans.ImageBean;
import storage.Beans.WatchBean;
import storage.Models.ImageModel;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

@WebServlet("/admin/uploadImage")
@MultipartConfig(maxFileSize = 16177215) // 16MB
public class UploadImageAdminServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the watch ID from the request
        Long watchId = Long.valueOf(request.getParameter("watch"));

        // Get the uploaded file part
        Part filePart = request.getPart("image");
        if (filePart != null) {
            // Get input stream of the upload file
            InputStream inputStream = filePart.getInputStream();

            // Create a Blob from the input stream
            Blob imageBlob = null;
            try {
                imageBlob = new javax.sql.rowset.serial.SerialBlob(inputStream.readAllBytes());
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create Blob");
                return;
            }

            // Create ImageBean and set its properties
            ImageBean imageBean = new ImageBean();
            imageBean.setWatch(watchId);
            try {
                imageBean.setImage(imageBlob.getBytes(1, (int) imageBlob.length()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Save the image to the database
            ImageModel imageModel = new ImageModel();
            try {
                imageModel.doSave(imageBean);

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to save image");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No file uploaded");
        }
        response.sendRedirect("/admin/modifyWatch.jsp?id=" + watchId);
    }
}
