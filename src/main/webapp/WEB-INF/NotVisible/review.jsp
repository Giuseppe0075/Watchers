<%@ page import="Model.Models.ReviewModel" %>
<%@ page import="Model.Beans.ReviewBean" %>
<%@ page import="java.sql.Date" %><%--
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
    text-align: left;
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

.review-star.active {
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

<%
    //Getting the reviews
    ReviewModel reviewModel = new ReviewModel();
    Collection<ReviewBean> reviews;
    ReviewBean userReview;
    long userId = session.getAttribute("user") != null ? (long) session.getAttribute("user") : 0;
    try {
        //Get all reviews for the current watch
        reviews = reviewModel.doRetrieveByCond("WHERE watch=? AND user!=?", List.of(watch.getId(), userId));
        userReview = reviewModel.doRetrieveByKey(List.of(watch.getId(), userId));
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
%>
<br><br>
<div class="review-container">
    <% if (userReview != null) { %>
        <h1>Your review</h1>
    <% } else { %>
        <h1>Write a review</h1>
    <% } %>
    <!-- Form to submit a review -->
    <form id="review-form">
        <label for="review-rating-stars">Stars</label>
        <div id="review-rating-stars">
            <button type="button" class="review-star" value="1">&bigstar;</button>
            <button type="button" class="review-star" value="2">&bigstar;</button>
            <button type="button" class="review-star" value="3">&bigstar;</button>
            <button type="button" class="review-star" value="4">&bigstar;</button>
            <button type="button" class="review-star" value="5">&bigstar;</button>
        </div>
        <% if (userReview != null) { %>
            <p> Date: <%= userReview.getDate()%> </p>
        <% } %>
        <label for="review-text">Review</label>
        <textarea name="review" id="review-text" cols="30" rows="10"><% if(userReview != null){ %><%= userReview.getDescription() %><% } %></textarea>
        <% if(userReview == null){ %>
            <input type="submit" value="Submit">
        <% } else { %>
            <input type="submit" value="Update">
        <% } %>
    </form>
    <%
        UserModel userModel = new UserModel();
        //Show all reviews except the one of the user itself, because it's already shown in the form
        for (ReviewBean review : reviews) {
            UserBean user;
            try{
                //Get the user that wrote the review
                user = userModel.doRetrieveByKey(List.of(review.getUser()));
            } catch (Exception e) {
                user = new UserBean("none",new byte[0],"Anonymous","",new Date(0), "none", "none", "none", "none");
            }
    %>
    <div class="review">
        <div class="user"><%= user.getName() + " " + user.getSurname() %></div>
        <div class="date"><%= review.getDate() %></div>
        <div class="rating">
            <%
                for (int i = 0; i < review.getStars(); i++) {
                    //Print full stars
                %>
                    &bigstar;
                <%
                }
                for(int i = review.getStars(); i < 5; i++) {
                    //Print empty stars
                %>
                    &star;
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
        const userReviewStars = <% if(userReview != null) { %> <%= userReview.getStars() %> <% } else { %> 0 <% }; %>;

        const setInitialStars = (starsCount) => {
            document.querySelectorAll('.review-star').forEach((star, index) => {
                star.classList.toggle('active', index < starsCount);
            });
        };

        setInitialStars(userReviewStars);

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
                    alert(data.message);
                    location.reload();
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert(error);
                });
        });
    });
</script>
