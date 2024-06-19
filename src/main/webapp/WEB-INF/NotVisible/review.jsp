<%--
  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 25/05/2024
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>

<style>

    #stars {
        width: 100%;
        margin: 10% auto 0;
        text-align: center;
    }

    .star {
        background-color: transparent;
        border: 0;
        outline: 0;
        display: inline-block;
        cursor: pointer;
        font-size: 3em;
        color: #aaa;
        transition: color 0.3s;
    }

    .star.active, .star:hover {
        color: #f1c40f;
    }
</style>

<!-- Qui l'utente loggato potra inserire una recensione su un prodotto -->
<div>
    <h1>Scrivi una recensione</h1>
    <form id="reviewForm">
        <label for="stars">Stelle</label>
        <div id="stars">
            <button type="button" class="star" value="1">&bigstar;</button>
            <button type="button" class="star" value="2">&bigstar;</button>
            <button type="button" class="star" value="3">&bigstar;</button>
            <button type="button" class="star" value="4">&bigstar;</button>
            <button type="button" class="star" value="5">&bigstar;</button>
        </div>
        <label for="review">Recensione</label>
        <textarea name="review" id="review" cols="30" rows="10"></textarea>
        <input type="submit" value="Invia">
    </form>
</div>

<script>
    document.addEventListener('DOMContentLoaded', () => {
        document.querySelectorAll('.star').forEach((star, index) => {
            star.addEventListener('click', () => {
                document.querySelectorAll('.star').forEach((s, i) => {
                    s.classList.toggle('active', i <= index);
                });
            });
        });

        document.getElementById('reviewForm').addEventListener('submit', function(e) {
            e.preventDefault();
            const review = document.getElementById('review').value;
            let stars = 0;
            document.querySelectorAll('.star').forEach((star, index) => {
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
                    document.getElementById('reviewForm').reset();
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Errore durante l\'invio della recensione');
                });
        });
    });
</script>
