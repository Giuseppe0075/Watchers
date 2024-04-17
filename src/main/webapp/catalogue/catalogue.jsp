<%@ page import="storage.WatchDao" %>
<%@ page import="java.util.List" %>
<%@ page import="watch.Watch" %>
<%@ page import="java.util.Collection" %><%--
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
<%!Collection<Watch> watchList = %>


<%@include file="../navbar.html"%> <!-- Navabar -->

<table style="width: 100%">
    <tr>
        <th>Name: </th>
        <th>Brand: </th>
        <th>Description</th>
    </tr>
    <% for (Watch watch : watchList) { %>
        <tr >
            <td><%=watch.getNome()%></td>
            <td><%=watch.getBrand()%></td>
            <td><%=watch.getDescrizione()%></td>
        </tr>

    <%}%>

</table>



</body>
</html>
