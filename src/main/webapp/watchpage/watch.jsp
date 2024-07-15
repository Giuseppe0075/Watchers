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
        .watch-container {
            display: flex;
            flex-wrap: wrap;
        }

        .watch-photos {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            grid-template-rows: repeat(2, 1fr);
            gap: 10px;
            flex: 1;
        }

        .watch-photo {
            width: 100%;
            height: auto;
            object-fit: cover;
        }

        .watch-infobar {
            flex: 1;
            padding: 20px;
        }

        @media (max-width: 700px) {
            .watch-photos {
                display: flex;
                overflow-x: auto;
                scroll-snap-type: x mandatory;
            }

            .watch-photo {
                flex: 0 0 100%;
                scroll-snap-align: center;
            }

            .watch-infobar {
                width: 100%;
            }
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
        images = imageModel.doRetrieveByCond("WHERE watch=?", List.of(id));
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
%>
<%@include file="../navbar.jsp"%>
<div class="watch-container">
    <div class="watch-photos"> <!-- Photos Showroom-->
        <%
            for (ImageBean image : images) {
        %>
        <img class="watch-photo" src="${pageContext.request.contextPath}/getImage?id=<%=image.getId()%>&watch=<%=watch.getId()%>" alt="Immagine al momento non disponibile">
        <%
            }
        %>
    </div>

    <aside class="watch-infobar">
        <h1><%=watch.getName()%></h1>
        <h3><%="price: " + watch.getPrice()%></h3>
        <p><%="id: " + watch.getId() %></p>
        <p><%=watch.getDescription()%></p>
        <p><a href="${pageContext.request.contextPath}/cart-servlet?action=add&watch=<%=watch.getId()%>">Aggiungi al carrello</a></p>
    </aside>
</div>
<%@include file="../WEB-INF/NotVisible/review.jsp"%>
<%@include file="../footer.html"%> <!-- Footer -->
</body>
</html>
