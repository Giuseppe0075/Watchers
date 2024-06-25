<%@ page import="storage.Models.WatchModel" %>
<%@ page import="storage.Beans.WatchBean" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="storage.Beans.ImageBean" %>
<%@ page import="java.util.List" %>
<%@ page import="storage.Models.ImageModel" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Watchers</title>
        <!-- <link rel="icon" type="image/x-icon" href="/images/favicon.ico"> -->
        <link rel="stylesheet" href="../style/styleHomepage.css">
    </head>
    <body>
        <%
            WatchModel watchModel = new WatchModel();
            ImageModel imageModel = new ImageModel();
            Long id = Long.parseLong(request.getParameter("id"));

            WatchBean watch = null;
            Collection<ImageBean> images = null;
            try {
                watch = watchModel.doRetrieveByKey(List.of(id));
                images = imageModel.doRetrieveByCond("WHERE watch=?", List.of(id));
            } catch (Exception e) {
                response.sendError(404);
                return;
            }

        %>
        <%@include file="../navbar.jsp"%>
        <div class="container">
            <div class="photos"> <!-- Photos Showroom-->
                <%
                    // Itera attraverso la lista di immagini associate all'orologio
                    for (ImageBean image : images) {
                %>
                <img class="photo" src="${pageContext.request.contextPath}/getImage?id=<%=image.getId()%>&watch=<%=watch.getId()%>" alt="Immagine al momento non disponibile">
                <%
                    }
                %>
            </div>

            <aside class = "infobar">
                <h1><%=watch.getName()%></h1>
                <h3><%="price: " + watch.getPrice()%></h3>
                <p><%="id: " +watch.getId() %></p>
                <p><%=watch.getDescription()%></p>
            </aside>
        </div>
        <%@include file="../WEB-INF/NotVisible/review.jsp"%>
    </body>
</html>