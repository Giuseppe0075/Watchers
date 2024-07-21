package Control;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import org.tinylog.Logger;
import Model.Beans.BrandBean;
import Model.Beans.WatchBean;
import Model.Models.BrandModel;
import Model.Models.WatchModel;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddWatch", value = "/admin/addWatch")
@MultipartConfig
public class AddWatchAdminServlet extends HttpServlet {
    private final WatchModel watchModel = new WatchModel();
    private final BrandModel brandModel = new BrandModel();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String name = request.getParameter("name");
            String brand = request.getParameter("brand");
            if ("new".equals(brand)) {
                brand = request.getParameter("newBrand");
                // Check if brand exists, if not, create it
                BrandBean existingBrand = brandModel.doRetrieveByKey(List.of(brand));
                if (existingBrand == null) {
                    BrandBean newBrand = new BrandBean(brand,brand,"");
                    brandModel.doSave(newBrand);
                }
            }
            String description = request.getParameter("description");
            Double reviews_avg = 0.0;
            Double price = Double.parseDouble(request.getParameter("price"));
            String material = request.getParameter("material").toLowerCase();
            Integer stock = Integer.parseInt(request.getParameter("stock"));
            Double dimension = Double.parseDouble(request.getParameter("dimension"));
            Integer IVA = Integer.parseInt(request.getParameter("IVA"));
            String sex = request.getParameter("sex");
            Boolean visible = request.getParameter("visible") != null;

            WatchBean watch = new WatchBean(0L, name, brand, description, reviews_avg, price, material, stock, dimension, IVA, sex, visible);
            watchModel.doSave(watch);

            response.sendRedirect("/admin/productList.jsp");

        } catch (NumberFormatException | NullPointerException e) {
            Logger.warn(e, "Invalid parameter received");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameter received");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
