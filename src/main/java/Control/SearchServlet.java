package Control;

import Model.Beans.ImageBean;
import Model.Beans.WatchBean;
import Model.Models.ImageModel;
import Model.Models.WatchModel;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Search", value = "/search")
public class SearchServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WatchModel watchModel = new WatchModel();
        try{
            String text = req.getParameter("search-input");
            List<WatchBean> watches = (List<WatchBean>) watchModel.doRetrieveByCond("WHERE name LIKE '%"+text+"%' OR brand LIKE '%"+text+"%'", List.of(text,text));
            List<Map<String, Object>> watchesResult = new ArrayList<>();

            for(WatchBean watch : watches){
                Map<String, Object> watchRes = new HashMap<>();
                watchRes.put("id", watch.getId());
                watchRes.put("name", watch.getName());

                watchesResult.add(watchRes);
            }

            // Convert Java object to JSON string
            Gson gson = new Gson();
            String json = gson.toJson(watchesResult);

            // Set content type
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            // Write JSON string to response body
            PrintWriter out = resp.getWriter();
            out.print(json);
            out.flush();
        }
        catch(Exception e){
            throw new ServletException("Error during search", e);
        }
    }
}
