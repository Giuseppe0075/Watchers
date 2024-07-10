<%--
  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 22/05/2024
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="java.util.List" %>
<%@ page import="FavouritesCart.FavouritesCart" %>
<%@ page import="org.tinylog.Logger" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Beans.FavouriteBean" %>
<%@ page import="Model.Beans.WatchBean" %>
<%@ page import="Model.Models.WatchModel" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Favourites</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        /* Stile per il container principale */
        #favourites-container {
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 1000px;
            margin: 20px auto;
        }

        #favourites-table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0;
            border: 1px solid #ddd;
            border-radius: 10px;
            overflow: hidden;
        }

        #favourites-table th, #favourites-table td {
            padding: 8px;
            text-align: left;
            border: 1px solid #ddd;
        }

        #favourites-table th {
            background-color: #f2f2f2;
        }

        #favourites-table-container {
            overflow-x: auto;
        }
    </style>
</head>
<body>
<%
    FavouritesCart favouritesCart = new FavouritesCart(request.getSession(false));
    Set<FavouriteBean> t = favouritesCart.getFavourites();

    List<WatchBean> list = new ArrayList<WatchBean>();
    t.forEach(favouriteBean -> {
        try {
            list.add(new WatchModel().doRetrieveByKey(List.of(favouriteBean.getWatch())));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    });
%>

<%@include file="../navbar.jsp"%> <!-- Navabar -->

<div id="favourites-container">
    <h2>Preferiti</h2>
    <div id="favourites-table-container">
        <table id="favourites-table">
            <thead>
            <tr>
                <th>Nome</th>
                <th>Marca</th>
                <th>Descrizione</th>
                <th>Azione</th>
            </tr>
            </thead>
            <tbody>
            <% for (WatchBean element : list) { %>
            <tr>
                <td><%= element.getName() %></td>
                <td><%= element.getBrand() %></td>
                <td><%= element.getDescription() %></td>
                <td>
                    <form action="${pageContext.request.contextPath}/favourites-servlet" method="post">
                        <input type="hidden" name="watch" value="<%= element.getId() %>">
                        <input type="submit" name="action" value="remove">
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

<%@include file="../footer.html"%> <!-- Footer -->
</body>
</html>
