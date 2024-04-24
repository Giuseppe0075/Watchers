<%@ page import="storage.WatchBeen" %>
<%@ page import="java.util.Collection" %>
<%@ page import="storage.WatchBeen" %>
<%@ page import="utils.Security" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Catalogue</title>
    <link rel="stylesheet" href="../homepage/style.css">
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%!
    Collection<WatchBeen> watchList = WatchBeen.retriveAll(WatchBeen.class);
    String csrfToken = Security.getCSRFToken();
%>
<%
    boolean admin = session != null && session.getAttribute("admin") != null && session.getAttribute("admin").equals(true);
    request.getSession().setAttribute("csrfToken", csrfToken);
%>


<%@include file="../navbar.html"%> <!-- Navabar -->
<form method="post" action="${pageContext.request.contextPath}/hello-servlet">
    <input name = "csrfToken" type="hidden" value="<%=csrfToken%>">
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
                    <input <%=!admin ? "readonly" : ""%> type="text"   value="<%=watch.getName()%>">
                    </a>
                </td>
                <td> <input  <%=!admin ? "readonly" : ""%> type="text"  value="<%=watch.getBrand()%>"></td>
                <td><input <%=!admin ? "readonly" : ""%> type="text"  value="<%=watch.getDescription()%>"></td>
                <td><input name ="<%=watch.getId()%>" type="hidden" name="productID"  <%=!admin ? "readonly" : ""%> value="<%=watch.toJson()%>"></td>
            </tr>

        <%}%>
    </table>
    <input type="submit" value="Salva">
</form>



</body>
</html>
