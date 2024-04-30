<%@ page import="java.util.Collection" %>
<%@ page import="storage.WatchModel" %>
<%@ page import="utils.Security" %>
<%@ page import="java.util.List" %>
<%@ page import="storage.WatchBean" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Catalogue</title>
    <link rel="stylesheet" href="../homepage/style.css">
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%!
    List<WatchBean> watchList;
%>
<%
    String sort = request.getParameter("sort");
    try {
        if(sort == null) {
                watchList = (List<WatchBean>) new WatchModel().doRetrieveAll();
        }
        else {
                watchList = (List<WatchBean>) new WatchModel().doRetrieveByCond("order by " + sort);
        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
%>
<%
    boolean admin = session != null && session.getAttribute("admin") != null && session.getAttribute("admin").equals(true);
    String csrfToken= Security.getCSRFToken();
    request.getSession().setAttribute("csrfToken", csrfToken);
%>


<%@include file="../navbar.html"%> <!-- Navabar -->


<!-- PROBELMI DI SICUREZZA CON IL READONLY-->
<form method="post" action="${pageContext.request.contextPath}/hello-servlet">
    <input name = "csrfToken" type="hidden" value="<%=csrfToken%>">
    <table style="width: 100%">
        <tr>
            <th>Name:  <a href="${pageContext.request.contextPath}/catalogue/catalogue.jsp?sort=name">sort</a></th>
            <th>Brand: <a href="${pageContext.request.contextPath}/catalogue/catalogue.jsp?sort=brand">sort</a></th>
            <th>Description <a href="${pageContext.request.contextPath}/catalogue/catalogue.jsp?sort=description">sort</a></th>
        </tr>
        <% for (WatchBean watch : watchList) { %>
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/watchpage/watch.jsp?id=<%=watch.getId()%>">
                    <input style="width: 100%" <%=!admin ? "readonly" : ""%> type="text"   value="<%=watch.getName()%>">
                    </a>
                </td>
                <td> <input style="width: 100%" <%=!admin ? "readonly" : ""%> type="text"  value="<%=watch.getBrand()%>"></td>
                <td><input style="width: 100%" <%=!admin ? "readonly" : ""%> type="text"  value="<%=watch.getDescription()%>"></td>
                <td><input name ="<%=watch.getId()%>" type="hidden" name="productID"  <%=!admin ? "readonly" : ""%> value="<%=watch.toJson()%>"></td>
                <% if(admin){ %>
                <td>
                    <a href="#">Delete</a>
                </td>
                <% } %>
            </tr>
        <%}%>
    </table>
    <input type="submit" value="Salva">
</form>



</body>
</html>
