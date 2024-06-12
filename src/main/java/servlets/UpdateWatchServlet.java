package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.tinylog.Logger;
import storage.Beans.WatchBean;
import storage.Models.WatchModel;

import java.io.IOException;

@WebServlet(name = "UpdateWatch", value = "/admin/updateWatch")
public class UpdateWatchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recupera i dati modificati dal form
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");
            String brand = request.getParameter("brand");
            String description = request.getParameter("description");
            Double reviews_avg = Double.parseDouble(request.getParameter("reviews_avg"));
            Double price = Double.parseDouble(request.getParameter("price"));
            String material = request.getParameter("material");
            Integer stock = Integer.parseInt(request.getParameter("stock"));
            Double dimension = Double.parseDouble(request.getParameter("dimension"));
            Integer IVA = Integer.parseInt(request.getParameter("IVA"));
            String sex = request.getParameter("sex");
            Boolean visible = request.getParameter("visible") != null;

            // Aggiorna il bean (in un caso reale, salveresti queste modifiche in un database)
            WatchBean watch = new WatchBean(id,name,brand, description, reviews_avg, price,  material,  stock,  dimension,  IVA, sex,  visible);
            WatchModel wm = new WatchModel();
            wm.doSaveOrUpdate(watch);

            request.setAttribute("watch", watch);
            request.getRequestDispatcher("/admin/modifyWatch.jsp?id=" + id).forward(request, response);

        } catch (NumberFormatException | NullPointerException e) {
            Logger.warn(e, "Invalid parameter received");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameter received");
        } catch (Exception e) {
            Logger.error(e, "Error updating watch");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating watch");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recupera i dati modificati dal form
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("nome");
            String brand = request.getParameter("brand");
            String description = request.getParameter("description");
            Double reviews_avg = Double.parseDouble(request.getParameter("reviews_avg"));
            Double price = Double.parseDouble(request.getParameter("price"));
            String material = request.getParameter("material");
            Integer stock = Integer.parseInt(request.getParameter("stock"));
            Double dimension = Double.parseDouble(request.getParameter("dimension"));
            Integer IVA = Integer.parseInt(request.getParameter("IVA"));
            String sex = request.getParameter("sex");
            Boolean visible = request.getParameter("visible") != null;

            // Aggiorna il bean (in un caso reale, salveresti queste modifiche in un database)
            WatchBean watch = new WatchBean(id,name,brand, description, reviews_avg, price,  material,  stock,  dimension,  IVA, sex,  visible);
            WatchModel wm = new WatchModel();
            wm.doSaveOrUpdate(watch);

            request.setAttribute("watch", watch);
            request.getRequestDispatcher("/admin/modifyWatch.jsp?id=" + id).forward(request, response);

        } catch (NumberFormatException | NullPointerException e) {
            Logger.warn(e, "Invalid parameter received");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameter received");
        } catch (Exception e) {
            Logger.error(e, "Error updating watch");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating watch");
        }
    }
}
