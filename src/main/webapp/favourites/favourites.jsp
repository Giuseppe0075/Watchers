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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/styleFavorites.css">
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


<div class="favourites-container">
    <h1>Preferiti</h1>
            <% for (WatchBean element : list) { %>
            <div class="details-favorite">
                <img src=""   alt="watch">
                    <div>
                        <%= element.getName() %>
                        <%= element.getBrand() %>
                    </div>
                <div>
                    <form action="${pageContext.request.contextPath}/favourites-servlet" method="post">
                        <input type="hidden" name="watch" value="<%= element.getId() %>">
                        <input type="submit" name="action" value="remove">
                    </form>
                </div>
            </div>
            <% } %>


</div>

<%@include file="../footer.html"%> <!-- Footer -->
</body>
</html>
