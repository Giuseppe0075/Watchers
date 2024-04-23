<%@ page import="storage.WatchDao" %>
<%@ page import="java.util.List" %>
<%@ page import="storage.WatchBeen" %>
<%@ page import="java.util.Collection" %>
<%@ page import="storage.WatchBeen" %>
<%@ page import="storage.AdminBeen" %><%--
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
<%!Collection<WatchBeen> watchList = WatchBeen.retriveAll(WatchBeen.class);%>
<%Boolean admin = session != null && session.getAttribute("admin") != null && session.getAttribute("admin").equals(true);%>


<%@include file="../navbar.html"%> <!-- Navabar -->
<form method="post" action="/admin-login-servlet">
    <table style="width: 100%">
        <tr>
            <th>Name: </th>
            <th>Brand: </th>
            <th>Description</th>
        </tr>
        <% for (WatchBeen watch : watchList) { %>
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/watchpage/watch.jsp?id=<%=watch.getId()%>">
                    <input name="name_<%=watch.getId()%>" <%=!admin ? "readonly" : ""%> type="text"   value="<%=watch.getName()%>">
                    </a>
                </td>
                <td> <input name="brand_<%=watch.getId()%>" <%=!admin ? "readonly" : ""%> type="text"  value="<%=watch.getBrand()%>"></td>
                <td><input name="price_<%=watch.getId()%>" <%=!admin ? "readonly" : ""%> type="text"  value="<%=watch.getDescription()%>"></td>
                <td><input type="hidden" name="productID"  <%=!admin ? "readonly" : ""%> value="<%= watch.getId()%>"></td>
            </tr>

        <%}%>
    </table>
    <input type="submit" value="Salva">
</form>



</body>
</html>
