package Control;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tinylog.Logger;
import Model.Beans.ReviewBean;
import Model.Models.ReviewModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "NewReviewServlet", value = "/newReview")
public class NewReviewServlet extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Verifica la sessione utente
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            JsonObject errorResponse = new JsonObject();
            errorResponse.addProperty("error", "Utente non autorizzato");
            resp.getWriter().write(gson.toJson(errorResponse));
            return;
        }

        // Leggi il JSON inviato dalla richiesta
        StringBuilder jsonBuffer = new StringBuilder();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
        }
        JsonObject jsonRequest = gson.fromJson(jsonBuffer.toString(), JsonObject.class);

        // Recupera i dati dal JSON
        long watchID = jsonRequest.get("watchID").getAsLong();
        int rating = jsonRequest.get("rating").getAsInt();
        String reviewText = jsonRequest.get("review").getAsString();
        long userID = jsonRequest.get("userID").getAsLong();

        // Log informazioni di debug
        Logger.info("UserID: " + userID);

        // Crea il bean della recensione
        ReviewBean review = new ReviewBean(watchID, userID, rating, reviewText, new Date());

        // Salva la recensione nel database
        ReviewModel reviewModel = new ReviewModel();
        try {
            reviewModel.doSaveOrUpdate(review);
        } catch (Exception e) {
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JsonObject errorResponse = new JsonObject();
            errorResponse.addProperty("error", "Errore durante il salvataggio della recensione");
            resp.getWriter().write(gson.toJson(errorResponse));
            return;
        }

        // Risposta di successo
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        JsonObject successResponse = new JsonObject();
        successResponse.addProperty("status", "success");
        resp.getWriter().write(gson.toJson(successResponse));
    }
}
