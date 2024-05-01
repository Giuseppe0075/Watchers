package admin;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
public class SaveChangesServlet extends HttpServlet {

    /*
    Input json example:
    [
    {
        id : 1,
        name: "watch1",

    }
    ]
     */

    /**
     *
     * @param req an HTTP request with a jsonArray of modified watches
     * @param resp null, or an error if failed
     * @throws IOException if failed to update the watch
     */
    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // converting the array of watch in a JsonArray
        JsonArray watches = new Gson().toJsonTree(req.getParameter("watchesJson")).getAsJsonArray();

        // for each watch we save them into the database
        for(JsonElement watchElement : watches){
            WatchBean watchBeen = new WatchBean(watchElement.getAsJsonObject());
            try {
                new WatchModel().doSaveOrUpdate(watchBeen);
            }catch (Exception e){
                Logger.error("Failed to update watch", e);
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }

    }

     */
}
