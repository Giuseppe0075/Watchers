<%@ page import="storage.WatchModel" %>
<%@ page import="storage.WatchBean" %>
<%@ page import="java.sql.SQLException" %>
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
            WatchModel model = new WatchModel();
            String id = request.getParameter("id");

            WatchBean watch = null;
            try {
                watch = model.getWatchById(Integer.parseInt(id));
            } catch (Exception e) {
                response.sendError(404);
            }
        %>
        <%@include file="../navbar.html"%>
        <div class="container">
            <div class="photos"> <!-- Photos Showroom-->
                <img class = "mainPhoto" src = <%="watchpic/" + id + "/pic1.png"%> >
                <img class = "photo" src= <%="watchpic/" + id + "/pic2.png"%> >
                <img class = "photo" src= <%="watchpic/" + id + "/pic3.png"%> >
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