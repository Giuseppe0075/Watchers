<%@ page import="storage.WatchModel" %>
<%@ page import="storage.WatchBean" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="storage.ImageBean" %>
<%@ page import="java.util.List" %>
<%@ page import="storage.ImageModel" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Watchers</title>
        <!-- <link rel="icon" type="image/x-icon" href="/images/favicon.ico"> -->
        <link rel="stylesheet" href="../homepage/style.css">

    </head>
    <body>
        <%
            WatchModel watchModel = new WatchModel();
            ImageModel imageModel = new ImageModel();
            String id = request.getParameter("id");

            WatchBean watch = null;
            Collection<ImageBean> images = null;
            try {
                watch = watchModel.doRetrieveByKey(Integer.parseInt(id));
                images = imageModel.doRetrieveByCond("watch="+ watch.getId());
                assert(images != null);
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
                <img class="photo" src="${pageContext.request.contextPath}/getPhoto?id=<%=image.getId() %>&watch=<%= watch.getId() %>">
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


    </body>
</html>