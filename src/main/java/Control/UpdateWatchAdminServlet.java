package Control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.tinylog.Logger;
import Model.Beans.BrandBean;
import Model.Beans.WatchBean;
import Model.Models.BrandModel;
import Model.Models.WatchModel;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateWatch", value = "/admin/updateWatch")
public class UpdateWatchAdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recupera i dati modificati dal form
        try {
            Long watchId = Long.parseUnsignedLong(request.getParameter("id"));
            String name = request.getParameter("name");
            String brand = request.getParameter("brand");
            if ("new".equals(brand)) {
                brand = request.getParameter("newBrand");
                // Check if brand exists, if not, create it
                BrandModel brandModel = new BrandModel();
                BrandBean existingBrand = brandModel.doRetrieveByKey(List.of(brand));
                if (existingBrand == null) {
                    BrandBean newBrand = new BrandBean(brand,brand,"");
                    brandModel.doSave(newBrand);
                }
            }
            String description = request.getParameter("description");
            Double reviews_avg = Double.parseDouble(request.getParameter("reviews_avg"));
            Double price = Double.parseDouble(request.getParameter("price"));
            String material = request.getParameter("material");
            Integer stock = Integer.parseInt(request.getParameter("stock"));
            Double dimension = Double.parseDouble(request.getParameter("dimension"));
            Integer IVA = Integer.parseInt(request.getParameter("IVA"));
            String sex = request.getParameter("sex");
            Boolean visible = request.getParameter("visible") != null;

            WatchModel watchModel = new WatchModel();
            WatchBean watch = new WatchBean(watchId,name,brand, description, reviews_avg, price,  material,  stock,  dimension,  IVA, sex,  visible);
            watchModel.doSaveOrUpdate(watch);

            request.setAttribute("watch", watch);
            request.getRequestDispatcher(request.getContextPath()+"/admin/productList.jsp").forward(request, response);

        } catch (NumberFormatException | NullPointerException e) {
            Logger.warn(e, "Invalid parameter received");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameter received");
        } catch (Exception e) {
            Logger.error(e, "Error updating watch");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating watch");
        }
    }

}
