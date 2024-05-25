<%--
  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 25/05/2024
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>

<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<style>
    body {
        font-family: Arial, sans-serif; /* Ensure a standard font is used */
    }

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
    $(document).ready(function() {
        $('.star').on('click', function() {
            const selectedIndex = $(this).index();
            $('.star').each(function(index) {
                if (index <= selectedIndex) {
                    $(this).addClass('active');
                } else {
                    $(this).removeClass('active');
                }
            });
        });

        $('#reviewForm').on('submit', function(e) {
            e.preventDefault(); // Prevent the default form submission
            const rating = $('#rating').val();
            const review = $('#review').val();
            let stars = 0;
            $(this).find('.star').val(function(index, value) {
                if ($(this).hasClass('active')) {
                    stars = value;
                }
            });

            console.log(stars, review)
            $.ajax({
                type: 'POST',
                url: '/your-backend-endpoint', // Replace with your backend URL
                data: { rating: rating, review: review },
                success: function(response) {
                    // Handle success response
                },
                error: function(error) {
                    alert('Si e\' verificato un errore. Riprova.');
                    // Handle error response
                },
            });
        });
    });
</script>

