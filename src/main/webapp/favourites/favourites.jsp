<%--
  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 22/05/2024
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="storage.Models.WatchModel" %>
<%@ page import="java.util.List" %>
<%@ page import="storage.Beans.WatchBean" %>
<%@ page import="FavouritesCart.FavouritesCart" %>
<%@ page import="org.tinylog.Logger" %>
<%@ page import="storage.Beans.FavouriteBean" %>
<%@ page import="java.util.Set" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Favourites</title>
</head>
<body>
<%
    FavouritesCart favouritesCart = new FavouritesCart(request.getSession(false));
    Set<FavouriteBean> t = favouritesCart.getFavourites();

%>


<%@include file="../navbar.jsp"%> <!-- Navabar -->

<table style="width: 100%">
    <tr>
        <th>Name:  </th>
        <th>Brand: </th>
        <th>Description </th>
        <th>Action</th>
    </tr>
    <% for(var element : t){ %>
        <tr>
            <td></td>
            <td></td>
            <td><%=element.getWatch()%></td>
            <td>
                <form action="${pageContext.request.contextPath}/favourites-servlet" method="post">
                    <input type="hidden" name="watch" value="<%= element.getWatch()%>">
                    <input type="submit" name="action" value="remove">
                </form>
            </td>
        </tr>
    <% } %>
</table>
<%@include file="../footer.html"%> <!-- Footer -->
</body>
</html>
