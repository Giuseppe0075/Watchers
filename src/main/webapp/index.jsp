<%@ page import="database.DatabaseConnectionPool" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Watchers</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="style/styleHomepage.css">
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
    <div id="carouselExample" class="carousel slide" data-ride="carousel" style="width: 30vh" y>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <a href="${pageContext.request.contextPath}/watchpage/watch.jsp?id=1"><img src="homepage/watch1.avif" class="d-block" alt="Watch 1"></a>
            </div>
            <div class="carousel-item">
                <a href="${pageContext.request.contextPath}/watchpage/watch.jsp?id=2"><img src="homepage/watch2.avif" class="d-block" alt="Watch 2"></a>
            </div>
            <div class="carousel-item">
                <a href="${pageContext.request.contextPath}/watchpage/watch.jsp?id=3"><img src="homepage/watch3.avif" class="d-block" alt="Watch 3"></a>
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExample" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExample" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>



<%@include file="footer.html"%> <!-- Footer -->

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
