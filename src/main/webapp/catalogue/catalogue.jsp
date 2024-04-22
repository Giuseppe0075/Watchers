<%@ page import="storage.WatchDao" %>
<%@ page import="java.util.List" %>
<%@ page import="storage.WatchBeen" %>
<%@ page import="java.util.Collection" %>
<%@ page import="storage.WatchBeen" %><%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 17/04/2024
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Catalogue</title>
    <link rel="stylesheet" href="../homepage/style.css">
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%!Collection<WatchBeen> watchList = %>


<%@include file="../navbar.html"%> <!-- Navabar -->

<table style="width: 100%">
    <tr>
        <th>Name: </th>
        <th>Brand: </th>
        <th>Description</th>
    </tr>
    <% for (WatchBeen watch : watchList) { %>
        <tr>
            <td><%=watch.getName()%></td>
            <td><%=watch.getBrand()%></td>
            <td><%=watch.getDescription()%></td>
            <td><img src="<%= watch.getImage()%>" alt="Immagine Prodotto"></td>
            <td><input type="hidden" name="productID" value="<%= watch.getId()%>"></td>
        </tr>

    <%}%>



</table>



</body>
</html>
