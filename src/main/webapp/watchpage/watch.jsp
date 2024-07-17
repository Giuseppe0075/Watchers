<%@ page import="Model.Models.WatchModel" %>
<%@ page import="Model.Beans.WatchBean" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="Model.Beans.ImageBean" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Models.ImageModel" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Watchers</title>
    <link rel="stylesheet" href="../style/styleHomepage.css">
    <style>
        .container {
            display: flex;
            flex-wrap: wrap;
            max-width: 1200px;
            margin: auto;
            border: 1px solid #ddd;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .slider {
            position: relative;
            flex: 1;
            max-width: 600px;
            margin: auto;
        }

        .slides {
            display: flex;
            transition: transform 0.5s ease-in-out;
        }

        .slide {
            min-width: 100%;
            box-sizing: border-box;
            display: none;
        }

        .slider img {
            width: 100%;
            height: 400px;
            object-fit: contain;
        }

        .prev, .next {
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
            border-radius: 0 3px 3px 0;
            user-select: none;
        }

        .next {
            right: 0;
            border-radius: 3px 0 0 3px;
        }

        .prev:hover, .next:hover {
            color: white;
            background-color: rgb(93, 158, 168);
        }

        .watch-infobar {
            flex: 1;
            padding: 20px;
        }

        @media (max-width: 700px) {
            .container {
                flex-direction: column;
            }

            .slider {
                max-width: 100%;
            }

            .watch-infobar {
                width: 100%;
            }
        }

        .btn {
            display: inline-block;
            background-color: #000;
            color: #fff;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<%
    WatchModel watchModel = new WatchModel();
    ImageModel imageModel = new ImageModel();
    Long id = Long.parseLong(request.getParameter("id"));

    WatchBean watch;
    Collection<ImageBean> images;
    try {
        watch = watchModel.doRetrieveByKey(List.of(id));
        if(!watch.getVisible()) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
        images = imageModel.doRetrieveByCond("WHERE watch=?", List.of(id));
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
%>
<%@include file="../navbar.jsp"%>
<div class="container">
    <div class="slider">
        <div class="slides">
            <%
                for (ImageBean image : images) {
            %>
            <div class="slide">
                <img src="${pageContext.request.contextPath}/getImage?id=<%=image.getId()%>&watch=<%=watch.getId()%>" alt="Immagine al momento non disponibile">
            </div>
            <%
                }
            %>
        </div>
        <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
        <a class="next" onclick="plusSlides(1)">&#10095;</a>
    </div>

    <aside class="watch-infobar">
        <h2><%=watch.getName()%></h2>
        <h3><%="price: " + watch.getPrice()%></h3>
        <p><%=watch.getDescription()%></p>
        <% if (watch.getStock() > 0) {
            if(watch.getStock() > 10) { %>
                <p style="color: #4CAF50">Available</p>
            <% } else { %>
                <p style="color: #ff3333"> <%="Only " + watch.getStock() + " left"%></p>
            <% } %>
            <p><a href="${pageContext.request.contextPath}/cart-servlet?action=add&watch=<%=watch.getId()%>" class="btn">Add to cart</a></p>
        <% } else { %>
            <p class="btn">Out of stock</p>
        <% } %>
    </aside>
</div>
<%@include file="../WEB-INF/NotVisible/review.jsp"%>
<%@include file="../footer.html"%> <!-- Footer -->
<script>
    let slideIndex = 0;
    showSlides(slideIndex);

    function plusSlides(n) {
        showSlides(slideIndex += n);
    }

    function showSlides(n) {
        let slides = document.getElementsByClassName("slide");
        if (n >= slides.length) { slideIndex = 0 }
        if (n < 0) { slideIndex = slides.length - 1 }
        for (let i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        slides[slideIndex].style.display = "block";
    }
</script>
</body>
</html>
