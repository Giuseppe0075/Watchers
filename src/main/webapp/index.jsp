<%@ page import="Model.Beans.ImageBean" %>
<%@ page import="Model.Models.ImageModel" %>
<%@ page import="Model.Models.WatchModel" %>
<%@ page import="Model.Beans.WatchBean" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Watchers</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/glider-js/1.7.9/glider.js" integrity="sha512-iBTjquFGC3DUyi04utYzS9qZNPVTpUkWNX2ubbbXPeD9UF86QN9M8vrPdvKydHb8qlVfzBtQnLDNPXqT40z0+A==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/glider-js/1.7.9/glider.css" integrity="sha512-nfkkRjU7urjt0UPiMZpiFlK1SAy657MtPOG1DdM9kvBbwdspZ4dH+Gsu43U3Kry8UsF8eyjqjBppcw2wx7TU3w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<!-- Navbar -->
<%@include file="navbar.jsp"%>
<style>
    #mainVideo {
        position: relative; /* Cambiato da absolute a relative per permettere agli elementi successivi di posizionarsi sotto di esso */
        top: 0;
        left: 0;
        width: 100vw;
        height: 70vh; /* Ridotto l'altezza per lasciare spazio al carousel sotto */
        object-fit: cover;
        z-index: -1;
    }

    #frontPage {
        position: relative; /* Assicura che il contenitore del video abbia un posizionamento relativo */
        height: auto; /* Permette al contenitore di adattarsi all'altezza del video */
    }

    .glider-contain {
        width: 90%;
        margin: 0 auto;
    }

    .glider-prev, .glider-next {
        cursor: pointer;
        position: absolute;
        top: 50%;
        width: auto;
        padding: 16px;
        margin-top: -22px;
        color: #498e99;
        font-weight: bold;
        font-size: 18px;
        transition: 0.6s ease;

        user-select: none;
    }

    .glider-prev {
        border-radius: 3px 0 0 3px;
    }

    .glider-next{
        border-radius: 0 3px 3px 0;
    }

    .glider-prev:hover, .glider-next:hover {
        background-color: #35757d;
        color: white;
    }

    .slider img {
        width: 100%;
        height: 400px;
        object-fit: contain;
    }
</style>
<div id="frontPage">
    <video id="mainVideo" autoplay muted loop>
        <source src="homepage/home.webm" type="video/webm">
        Your browser does not support the video tag.
    </video>
</div>
<div class="glider-contain">
    <button class="glider-prev">&#10094;</button>
    <div class="glider">
        <%
            WatchModel watchModel = new WatchModel();
            ImageModel imageModel = new ImageModel();
            List<WatchBean> watches = null;
            try {
                watches = (List<WatchBean>) watchModel.doRetrieveByCond("WHERE visible=?", List.of(true));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            for(var watch : watches) {
                List<ImageBean> images = (List<ImageBean>) imageModel.doRetrieveByCond("WHERE watch=? ", List.of(watch.getId()));
        %>
        <div>
            <img src="${pageContext.request.contextPath}/getImage?id=<%=images.get(0).getId()%>&watch=<%=watch.getId()%>" alt="Immagine al momento non disponibile">
        </div>
        <%
            }
        %>
    </div>
    <button class="glider-next">&#10095;</button>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        new Glider(document.querySelector('.glider'), {
            slidesToShow: 1,
            slidesToScroll: 1,
            dots: '.dots',
            arrows: {
                prev: '.glider-prev',
                next: '.glider-next'
            },
            responsive: [
                {
                    breakpoint: 600,
                    settings: {
                        slidesToShow: 2,
                        slidesToScroll: 2
                    }
                },
                {
                    breakpoint: 900,
                    settings: {
                        slidesToShow: 3,
                        slidesToScroll: 3
                    }
                }
            ]
        });
    });
</script>

<%@include file="footer.html"%> <!-- Footer -->

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
