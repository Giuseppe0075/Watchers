package Control;

import Model.Beans.PurchaseBean;
import Model.Beans.WatchBean;
import Model.Beans.ReviewBean;
import Model.Models.PurchaseModel;
import Model.Models.WatchModel;
import Model.Models.ReviewModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tinylog.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

// Defines the servlet that handles new review submissions
@WebServlet(name = "NewReviewServlet", value = "/newReview")
public class NewReviewServlet extends HttpServlet {
    private final Gson gson = new Gson(); // Gson instance for JSON parsing

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Verify user session
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null || session.getAttribute("user").equals("0")){
            sendErrorMessage(resp, "You must be logged in to leave a review");
            return;
        }

        // Read JSON from the request
        StringBuilder jsonBuffer = new StringBuilder();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
        }
        JsonObject jsonRequest = gson.fromJson(jsonBuffer.toString(), JsonObject.class);

        // Retrieve data from JSON
        long watchID = jsonRequest.get("watchID").getAsLong();
        int rating = jsonRequest.get("rating").getAsInt();
        String reviewText = jsonRequest.get("review").getAsString();
        long userID = jsonRequest.get("userID").getAsLong();

        // Create the review bean
        ReviewBean review = new ReviewBean(watchID, userID, rating, reviewText, new Date());

        // Check if the user has purchased the watch
        PurchaseModel purchaseModel = new PurchaseModel();
        try {
            Collection<PurchaseBean> purchase = purchaseModel.doRetrieveByCond("WHERE watch = ? AND user = ?", List.of(watchID, userID));
            if (purchase.isEmpty()) {
                sendErrorMessage(resp, "You must have bought the watch to leave a review");
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Check if the user has selected a rating
        if (rating < 1 || rating > 5) {
            sendErrorMessage(resp, "Invalid rating value. Please select a rating");
            return;
        }

        // Save the review in the database
        ReviewModel reviewModel = new ReviewModel();
        try {
            reviewModel.doSaveOrUpdate(review);
        } catch (Exception e) {
            sendErrorMessage(resp, "An error occurred while saving the review. Please try again later.");
            return;
        }

        // Update the watch rating
        WatchModel watchModel = new WatchModel();
        try{
            WatchBean watch = watchModel.doRetrieveByKey(List.of(watchID));
            Collection<ReviewBean> reviews = reviewModel.doRetrieveByCond("WHERE watch = ?", List.of(watchID));
            int totalRating = 0;
            for (ReviewBean r : reviews) {
                totalRating += r.getStars();
            }
            watch.setReviews_avg((double) totalRating / (double)reviews.size());
            watchModel.doSaveOrUpdate(watch);
        }catch (Exception e) {
            Logger.error(e, "Error updating watch rating");
        }

        // Successful response
        sendSuccessMessage(resp);
    }

    private void sendErrorMessage(HttpServletResponse resp, String message) throws IOException {
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        JsonObject errorResponse = new JsonObject();
        errorResponse.addProperty("status", "error");
        errorResponse.addProperty("message", message);
        resp.getWriter().write(gson.toJson(errorResponse));
    }

    private void sendSuccessMessage(HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        JsonObject errorResponse = new JsonObject();
        errorResponse.addProperty("status", "success");
        errorResponse.addProperty("message", "Review saved successfully");
        resp.getWriter().write(gson.toJson(errorResponse));
    }
}