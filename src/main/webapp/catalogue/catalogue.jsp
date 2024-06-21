<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.Collection" %>
<%@ page import="storage.Models.WatchModel" %>
<%@ page import="utils.Security" %>
<%@ page import="java.util.List" %>
<%@ page import="storage.Beans.WatchBean" %>
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
    WatchModel model = new WatchModel();
    try {
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
                <form id="cartForm" method="post" action="${pageContext.request.contextPath}/cart-servlet">
                    <input type="hidden" name="quantity" value="1">
                    <input type="hidden" name="watch" value="<%= watch.getId()%>">
                    <% if(watch.getStock() == 0){
                        %> <p>Out of stock</p>
                    <% } else { %>
                        <button type="submit" name="action" value="add">Add to cart</button>
                    <%}%>
                </form>
                <form id="favoriteForm" method="post" action="${pageContext.request.contextPath}/favourites-servlet">
                    <input type="hidden" name="url" value="${pageContext.request.contextPath}/catalogue/catalogue.jsp">
                    <input type="hidden" name="watch" value="<%= watch.getId()%>">
                    <button type="submit" name="action" value="add">Add to favourites</button>
                </form>
            </td>
        </tr>
        <%}%>
    </table>
</body>
</html>
