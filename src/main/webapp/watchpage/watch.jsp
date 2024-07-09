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
        <!-- <link rel="icon" type="image/x-icon" href="/images/favicon.ico"> -->
        <link rel="stylesheet" href="../style/styleHomepage.css">
        <link rel="stylesheet" href="../style/styleFooter.css">
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
                throw new RuntimeException(e);
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
        <%@include file="../footer.html"%> <!-- Footer -->
    </body>
</html>