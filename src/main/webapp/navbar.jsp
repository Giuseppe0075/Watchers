<header>
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/homepage/style.css">
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
                <li><a href="${pageContext.request.contextPath}/admin/userManager.jsp">UserManager</a> </li>
                <li><a href="${pageContext.request.contextPath}/admin/productList.jsp">ProductManager</a> </li>
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


