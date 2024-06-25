<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.Collection" %>
<%@ page import="storage.Models.WatchModel" %>
<%@ page import="utils.Security" %>
<%@ page import="java.util.List" %>
<%@ page import="storage.Beans.WatchBean" %>
<html>
<head>
    <title>Catalogue</title>
    <link rel="stylesheet" href="../style/styleCatalogue.css" type="text/css">
    <link rel="stylesheet" href="../style/styleHomepage.css">

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

<main>
    <section id="catalogo">
        <% for (WatchBean watch : watchList) { %>
        <div class="orologio">
            <h2><a href="${pageContext.request.contextPath}/watchpage/watch.jsp?id=<%=watch.getId()%>">
                <%=watch.getName()%></a></h2>
            <p>Brand: <%=watch.getBrand()%></p>
            <p>Descrizione: <%=watch.getDescription()%></p>
            <form method="post" action="${pageContext.request.contextPath}/cart-servlet">
                <input type="hidden" name="quantity" value="1">
                <input type="hidden" name="watch" value="<%= watch.getId()%>">
                <% if(watch.getStock() == 0) { %>
                <p>Out of stock</p>
                <% } else { %>
                <button type="submit" name="action" value="add">Aggiungi a Carrello</button>
                <% } %>
            </form>
            <form method="post" action="${pageContext.request.contextPath}/favourites-servlet">
                <input type="hidden" name="url" value="${pageContext.request.contextPath}/catalogue/catalogue.jsp">
                <input type="hidden" name="watch" value="<%= watch.getId()%>">
                <button type="submit" name="action" value="add">Aggiungi a Preferiti</button>
            </form>
        </div>
        <% } %>
    </section>
</main>

<%@include file="../footer.html"%> <!-- Footer -->

</body>
</html>
