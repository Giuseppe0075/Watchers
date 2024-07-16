<%@ page import="database.DatabaseConnectionPool" %>
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

<div id="frontPage">
    <video id="mainVideo" autoplay muted loop>
        <source src="homepage/home.webm" type="video/webm">
        Your browser does not support the video tag.
    </video>
</div>
<div class="glider-contain">
    <div class="glider">
        <div class="carousel-item">
            <a href="${pageContext.request.contextPath}/watchpage/watch.jsp?id=1"><img src="homepage/watch1.avif" class="d-block" alt="Watch 1"></a>
        </div>
        <div class="carousel-item">
            <a href="${pageContext.request.contextPath}/watchpage/watch.jsp?id=2"><img src="homepage/watch2.avif" class="d-block" alt="Watch 2"></a>
        </div>
        <div class="carousel-item">
            <a href="${pageContext.request.contextPath}/watchpage/watch.jsp?id=3"><img src="homepage/watch3.avif" class="d-block" alt="Watch 3"></a>
        </div>
        <div class="carousel-item">
            <a href="${pageContext.request.contextPath}/watchpage/watch.jsp?id=1"><img src="homepage/watch1.avif" class="d-block" alt="Watch 1"></a>
        </div>
        <div class="carousel-item">
            <a href="${pageContext.request.contextPath}/watchpage/watch.jsp?id=2"><img src="homepage/watch2.avif" class="d-block" alt="Watch 2"></a>
        </div>
        <div class="carousel-item">
            <a href="${pageContext.request.contextPath}/watchpage/watch.jsp?id=3"><img src="homepage/watch3.avif" class="d-block" alt="Watch 3"></a>
        </div>
        <div class="carousel-item">
            <a href="${pageContext.request.contextPath}/watchpage/watch.jsp?id=1"><img src="homepage/watch1.avif" class="d-block" alt="Watch 1"></a>
        </div>
        <div class="carousel-item">
            <a href="${pageContext.request.contextPath}/watchpage/watch.jsp?id=2"><img src="homepage/watch2.avif" class="d-block" alt="Watch 2"></a>
        </div>
        <div class="carousel-item">
            <a href="${pageContext.request.contextPath}/watchpage/watch.jsp?id=3"><img src="homepage/watch3.avif" class="d-block" alt="Watch 3"></a>
        </div>
    </div>

    <button aria-label="Previous" class="glider-prev"><</button>
    <button aria-label="Next" class="glider-next">></button>
    <div role="tablist" class="dots"></div>
</div>


<script>

    let test = new Glider(document.querySelector('.glider'), {
        // Mobile-first defaults
        slidesToShow: 1,
        slidesToScroll: 1,
        draggable: false,
        dots: '.dots',
        arrows: {
            prev: '.glider-prev',
            next: '.glider-next'
        },
        responsive: [
            {
                // screens greater than >= 775px
                breakpoint: 775,
                settings: {
                    // Set to `auto` and provide item width to adjust to viewport
                    slidesToShow: 3,
                    slidesToScroll: 2,
                    itemWidth: 150,
                    duration: 0.25
                }
            },{
                // screens greater than >= 1024px
                breakpoint: 1024,
                settings: {
                    slidesToShow: 4,
                    slidesToScroll: 1,
                    itemWidth: 150,
                    duration: 0.25
                }
            }
        ]
    });
</script>
<style>
    img{
        width: 100%;
        height: 100%;
    }
</style>

<%@include file="footer.html"%> <!-- Footer -->

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
