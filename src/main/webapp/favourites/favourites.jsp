<%--
  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 22/05/2024
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="java.util.List" %>
<%@ page import="FavouritesCart.FavouritesCart" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Beans.FavouriteBean" %>
<%@ page import="Model.Beans.WatchBean" %>
<%@ page import="Model.Models.WatchModel" %>
<%@ page import="Model.Models.ImageModel" %>
<%@ page import="Model.Beans.ImageBean" %>

<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Favourites</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/styleFavorites.css">
</head>
<body>

<div class="flex-container">
    <div class="flex-content">
        <%
            FavouritesCart favouritesCart = new FavouritesCart(request.getSession(false));
            Set<FavouriteBean> t = favouritesCart.getFavourites();
            ImageModel imageModel = new ImageModel();
            ImageBean image;
            List<WatchBean> list = new ArrayList<>();
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
            <h1>Favorites</h1>
            <% for (WatchBean element : list) { %>
            <div class="details-favorite">
                <%
                    try {
                        image = imageModel.doRetrieveByCond("WHERE watch = ? ORDER BY id ASC", List.of(element.getId())).iterator().next();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                %>
                <a href="${pageContext.request.contextPath}/watchpage/watch.jsp?id=<%=element.getId()%>">
                    <img src="${pageContext.request.contextPath}/getImage?id=<%=image.getId()%>&watch=<%=element.getId()%>" alt="watch" class="Image not available">
                </a>
                <div>
                    <%= element.getName() %>
                    <%= element.getBrand() %>
                </div>
                <div>
                    <form action="${pageContext.request.contextPath}/favourites-servlet" method="post">
                        <input type="hidden" name="watch" value="<%= element.getId() %>">
                        <input type="submit" name="action" value="Remove">
                    </form>
                </div>
            </div>
            <% } %>


        </div>
    </div>

<%@include file="../footer.html"%> <!-- Footer -->

</body>
</html>
