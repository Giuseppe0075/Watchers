package admin;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.tinylog.Logger;
import storage.WatchBeen;
import storage.WatchModel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;

public class SaveChangesServlet extends HttpServlet {

    /*
    Input json example:
    [
    {
        id : 1,
        name: "orologip",

    }
    ]
     */

    /**
     *
     * @param req an HTTP request with a jsonArray of modified watches
     * @param resp null, or an error if failed
     * @throws IOException if failed to update the watch
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // converting the array of watch in a JsonArray
        JsonArray watches = new Gson().toJsonTree(req.getParameter("watchesJson")).getAsJsonArray();

        // for each watch we save them into the database
        for(JsonElement watchElement : watches){
            WatchBeen watchBeen = new WatchBeen(watchElement.getAsJsonObject());
            try {
                new WatchModel().updateWatch(watchBeen);
            }catch (Exception e){
                Logger.error("Failed to update watch", e);
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }

    }
}
