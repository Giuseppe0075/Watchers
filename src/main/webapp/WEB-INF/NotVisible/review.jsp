<%@ page import="Model.Models.ReviewModel" %>
<%@ page import="Model.Beans.ReviewBean" %><%--
  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 25/05/2024
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>

<style>

.review-container {
    background-color: #fff;
    border-radius: 15px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    padding: 20px;
    width: 100%;
}

.review-container h1 {
    text-align: center;
    font-size: 1.5em;
    margin-bottom: 20px;
}

#review-rating-stars {
    width: 100%;
    margin: 10px auto;
    text-align: center;
}

.review-star {
    background-color: transparent;
    border: 0;
    outline: 0;
    display: inline-block;
    cursor: pointer;
    font-size: 2em;
    color: #aaa;
    transition: color 0.3s;
}

.review-star.active, .review-star:hover {
    color: #f1c40f;
}

label {
    display: block;
    margin-top: 10px;
    font-weight: bold;
}

textarea {
    width: 100%;
    padding: 10px;
    margin-top: 5px;
    border-radius: 5px;
    border: 1px solid #ccc;
    resize: vertical;
}

input[type="submit"] {
    display: block;
    width: 100%;
    padding: 10px;
    margin-top: 20px;
    border: 0;
    border-radius: 5px;
    background-color: #5d9ea8;
    color: white;
    font-size: 1em;
    cursor: pointer;
    transition: background-color 0.3s;
}

input[type="submit"]:hover {
    background-color: #498e99;
}
</style>

<!-- Qui l'utente loggato potra inserire una recensione su un prodotto -->
<br><br>
<div class="review-container">
    <h1>Write a review</h1>
    <form id="review-form">
        <label for="review-rating-stars">Stelle</label>
        <div id="review-rating-stars">
            <button type="button" class="review-star" value="1">&bigstar;</button>
            <button type="button" class="review-star" value="2">&bigstar;</button>
            <button type="button" class="review-star" value="3">&bigstar;</button>
            <button type="button" class="review-star" value="4">&bigstar;</button>
            <button type="button" class="review-star" value="5">&bigstar;</button>
        </div>
        <label for="review-text">Recensione</label>
        <textarea name="review" id="review-text" cols="30" rows="10" required></textarea>
        <input type="submit" value="Invia">
    </form>
    <%
        //Getting the reviews
        ReviewModel reviewModel = new ReviewModel();
        Collection<ReviewBean> reviews;
        try {
             reviews = reviewModel.doRetrieveByCond("WHERE watch=?", List.of(watch.getId()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (ReviewBean review : reviews) {
    %>
    <div class="review">
        <div class="user"><%= review.getUser() %></div>
        <div class="date"><%= review.getDate() %></div>
        <div class="rating">
            <%
                for (int i = 0; i < review.getStars(); i++) {
            %>
            &bigstar;
            <%
                }
            %>
        </div>
        <div class="text"><%= review.getDescription() %></div>
    </div>
    <%
        }
    %>
</div>

<script>
    document.addEventListener('DOMContentLoaded', () => {
        document.querySelectorAll('.review-star').forEach((star, index) => {
            star.addEventListener('click', () => {
                document.querySelectorAll('.review-star').forEach((s, i) => {
                    s.classList.toggle('active', i <= index);
                });
            });
        });

        document.getElementById('review-form').addEventListener('submit', function(e) {
            e.preventDefault();
            const review = document.getElementById('review-text').value;
            let stars = 0;
            document.querySelectorAll('.review-star').forEach((star, index) => {
                if (star.classList.contains('active')) {
                    stars = index + 1;
                }
            });

            const reviewData = {
                watchID: '<%= request.getParameter("id") %>',
                rating: stars,
                review: review,
                userID: '<%= session.getAttribute("user") %>'
            };

            fetch('<%= request.getContextPath() %>/newReview', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(reviewData)
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    document.getElementById('review-form').reset();
                    document.querySelectorAll('.review-star').forEach(star => star.classList.remove('active'));
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Errore durante l\'invio della recensione');
                });
        });
    });
</script>
