<%@ page import="java.util.Collection" %>
<%@ page import="storage.Models.WatchModel" %>
<%@ page import="utils.Security" %>
<%@ page import="java.util.List" %>
<%@ page import="storage.Beans.WatchBean" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Catalogue</title>
    <link rel="stylesheet" href="../homepage/style.css">
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%!
    Collection<WatchBean> watchList;
%>
<%
    String sort = request.getParameter("sort");
    try {WatchModel model = new WatchModel();
        if(sort == null) {
                watchList = model.doRetrieveAll();
        }
        else {
                watchList = model.doRetrieveByCond("order by " + sort,List.of());
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


<%@include file="../navbar.jsp"%> <!-- Navabar -->

<!-- PROBELMI DI SICUREZZA CON IL READONLY-->
<form method="post" action="${pageContext.request.contextPath}/hello-servlet">
    <input name = "csrfToken" type="hidden" value="<%=csrfToken%>">
    <% if(admin){ %>
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
                    <input style="width: 100%"  type="text"   value="<%=watch.getName()%>">
                    </a>
                </td>
                <td> <input style="width: 100%"  type="text"  value="<%=watch.getBrand()%>"></td>
                <td><input style="width: 100%"  type="text"  value="<%=watch.getDescription()%>"></td>
                <td><input name="<%=watch.getId()%>" type="hidden"value="<%=watch.toString()%>"></td>

                <td>
                    <a href="#">Delete</a>
                </td>

            </tr>

        <%}%>
    </table>
    <% } else { %>
    <table style="width: 100%">
        <tr>
            <th>Name:  <a href="${pageContext.request.contextPath}/catalogue/catalogue.jsp?sort=name">sort</a></th>
            <th>Brand: <a href="${pageContext.request.contextPath}/catalogue/catalogue.jsp?sort=brand">sort</a></th>
            <th>Description <a href="${pageContext.request.contextPath}/catalogue/catalogue.jsp?sort=description">sort</a></th>
            <th>Action</th>
        </tr>
        <% for (WatchBean watch : watchList) { %>
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}/watchpage/watch.jsp?id=<%=watch.getId()%>">
                    <%=watch.getName()%>
                </a>
            </td>
            <td><%=watch.getBrand()%></td>
            <td><%=watch.getDescription()%></td>
            <td>
                <a href="${pageContext.request.contextPath}/cart?action=add&watch=<%=watch.getId()%>&quantity=1">Add to cart</a>
            </td>
        </tr>
        <%}%>
    </table>
    <% } %>
</form>
</body>
</html>
