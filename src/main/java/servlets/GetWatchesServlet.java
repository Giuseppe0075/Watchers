package servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.tinylog.Logger;
import storage.Beans.WatchBean;
import storage.Models.ImageModel;
import storage.Models.WatchModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@WebServlet(name = "GetWatchesServlet", value = "/get-watches")
public class GetWatchesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WatchModel watchModel = new WatchModel();
        Collection<WatchBean> watches;
        try {
            watches = watchModel.doRetrieveAll();
            
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
