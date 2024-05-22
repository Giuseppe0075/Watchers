<!DOCTYPE html>
<html>
<head>
    <title>Navigation bar DEMO</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/homepage/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<header>
    <a href="${pageContext.request.contextPath}/index.jsp">
    <img src="${pageContext.request.contextPath}/homepage/LOGO.png" alt="Logo">
    </a>
    <nav>
        <div>
            <ul>
                <li><a href="${pageContext.request.contextPath}/catalogue/catalogue.jsp">Catalogo</a></li>
                <li><a href="${pageContext.request.contextPath}/cart/cart.jsp">Carrello</a></li>
                <li><a href="${pageContext.request.contextPath}/favourites/favourites.jsp">Preferiti</a></li>
                <% if (session.getAttribute("user") != null) { %>
                <li><a href="${pageContext.request.contextPath}/user/logout">Logout</a></li>
                <% } else { %>
                <li><a href="${pageContext.request.contextPath}/user/login.jsp">Sign-in/Sign-up</a></li>
                <% } %>
            </ul>
        </div>
    </nav>

    <% if(session.getAttribute("user") == null) { %>
    <a href="${pageContext.request.contextPath}/user/login.jsp"><img class="omino" src="${pageContext.request.contextPath}/homepage/lalal.png" alt="Omino"></a>
    <% }else{ %>
    <a href="${pageContext.request.contextPath}/user/personalArea.jsp"><img class="omino" src="${pageContext.request.contextPath}/homepage/lalal.png" alt="Omino"></a>
    <% }%>

</header>
</body>
</html>

