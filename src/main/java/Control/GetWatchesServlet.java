package Control;

import Model.Beans.ImageBean;
import Model.Models.ImageModel;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.Beans.WatchBean;
import Model.Models.WatchModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "GetWatchesServlet", value = "/get-watches")
@MultipartConfig
public class GetWatchesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] genders = req.getParameterValues("gender");
        String[] brands = req.getParameterValues("brand");
        Integer sizeMin = req.getParameter("sizeMin").isEmpty() ? 0 : Integer.parseInt(req.getParameter("sizeMin"));
        Integer sizeMax = req.getParameter("sizeMax").isEmpty()? Integer.MAX_VALUE : Integer.parseInt(req.getParameter("sizeMax"));
        String[] materials = req.getParameterValues("material");
        Integer priceMin = req.getParameter("priceMin").isEmpty() ? 0 : Integer.parseInt(req.getParameter("priceMin"));
        Integer priceMax = req.getParameter("priceMax").isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(req.getParameter("priceMax"));
        Float starsMin = req.getParameter("starsMin").isEmpty() ? 0f : Float.parseFloat(req.getParameter("starsMin"));
        List<Object> values = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        query.append("WHERE ");

        if(genders != null){
            query.append("sex IN (");
            for(String gender : genders){
                query.append("?,");
                values.add(gender);
            }
            query.deleteCharAt(query.length() - 1);
            query.append(") AND ");
        }
        if (brands != null) {
            query.append("brand IN (");
            for (String brand : brands) {
                query.append("?,");
                values.add(brand);
            }
            query.deleteCharAt(query.length() - 1);
            query.append(") AND ");
        }
        query.append("dimension BETWEEN ? AND ? AND ");
        values.add(sizeMin);
        values.add(sizeMax);
        if(materials != null){
            query.append("material IN (");
            for(String material : materials){
                query.append("?,");
                values.add(material);
            }
            query.deleteCharAt(query.length() - 1);
            query.append(") AND ");
        }
        query.append("price BETWEEN ? AND ? AND");
        values.add(priceMin);
        values.add(priceMax);
        query.append(" reviews_avg >= ? AND visible = 1");
        values.add(starsMin);
        WatchModel watchModel = new WatchModel();
        Collection<WatchBean> watches;
        try {
            watches = watchModel.doRetrieveByCond(query.toString(), values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<Map<String, Object>> watchesWithMainImage = new ArrayList<>();
        ImageModel imageModel = new ImageModel();

        for(WatchBean watch : watches){
            Map<String, Object> watchWithImage = new HashMap<>();
            watchWithImage.put("id", watch.getId());
            watchWithImage.put("name", watch.getName());
            watchWithImage.put("brand", watch.getBrand());
            watchWithImage.put("price", watch.getPrice());
            watchWithImage.put("dimension", watch.getDimension());
            watchWithImage.put("material", watch.getMaterial());
            watchWithImage.put("sex", watch.getSex());
            watchWithImage.put("reviews_avg", watch.getReviews_avg());
            watchWithImage.put("visible", watch.getVisible());
            try {
                ImageBean mainImage = imageModel.doRetrieveByCond("WHERE watch = ? ORDER BY id ASC LIMIT 1", List.of(watch.getId())).iterator().next();
                watchWithImage.put("mainImageId", mainImage.getId());
            } catch (Exception e) {
                watchWithImage.put("mainImageId", 0);
            }
            watchesWithMainImage.add(watchWithImage);
        }

        // Convert Java object to JSON string
        Gson gson = new Gson();
        String json = gson.toJson(watchesWithMainImage);

        // Set content type
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Write JSON string to response body
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }
}
