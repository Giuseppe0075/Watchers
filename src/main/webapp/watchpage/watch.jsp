<%@ page import="watch.Watch" %>
<%@ page import="watch.WatchManager" %>
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
            Watch watch;
            String id = request.getParameter("id");
            if(id == null || (watch = WatchManager.retriveWatchbyID(id)) == null){
                //response.sendRedirect("../error.jsp");
                response.sendError(404);

                return;
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
                <h1><%=watch.getNome()%></h1>
                <h3><%="price: " + watch.getPrezzo()%></h3>
                <p><%="id: " +watch.getId() %></p>
                <p><%=watch.getDescrizione()%></p>
            </aside>
        </div>


    </body>
</html>