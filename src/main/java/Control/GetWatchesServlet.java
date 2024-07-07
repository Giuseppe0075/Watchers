package Control;

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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

        // Convert the collection of watches to JSON
        Gson gson = new Gson();
        String json = gson.toJson(watches);

        // Set the response content type to JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Write the JSON to the response
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }
}
