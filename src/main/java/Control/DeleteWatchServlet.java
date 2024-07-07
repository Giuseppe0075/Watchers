package Control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.Beans.WatchBean;
import Model.Models.WatchModel;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeletWatchServlet",value = "/admin/deleteWatch")
public class DeleteWatchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String watchId = req.getParameter("id"); // Ottieni l'ID dall'oggetto richiesta
        WatchModel watchModel = new WatchModel();
        try {
            WatchBean watchBean = watchModel.doRetrieveByKey(List.of(watchId));
            watchBean.setVisible(false);
            watchModel.doSaveOrUpdate(watchBean);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }
    }
}
