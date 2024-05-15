package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import utils.Security;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

@WebServlet(name = "uploadPhoto", value="/uploadPhoto")
public class uploadPhotoServlet extends HttpServlet {


    /**
     *
     * @param request, parameters: file, watchID
     * @param response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");

        String watchID = request.getParameter("watchID");
        if(watchID == null || !checkWatchId(watchID)){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        // Ottenere il file caricato dall'utente
        Part filePart = request.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        // Controllare l'estensione del file
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!Security.allowedImageExt.contains(fileExtension)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato del file non supportato.");
            return;
        }

        // Controllare la dimensione del file
        if (filePart.getSize() > Security.maxFileLenght) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Dimensione del file troppo grande.");
            return;
        }
        byte [] file = filePart.getInputStream().readAllBytes();
        //new ImageModel().insert(watchID, file);

        // Mostrare un messaggio di successo
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>File Upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>File caricato con successo</h1>");
            out.println("</body>");
            out.println("</html>");
        }

    }

    private boolean checkWatchId(String id){
        return false;
    }




}
