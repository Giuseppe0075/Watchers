        <%@ page import="storage.Beans.UserBean" %>
<%@ page import="storage.Models.UserModel" %>
<%@ page import="java.util.List" %>
<header>
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/homepage/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/homepage/navbar.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />

    <nav>
        <%
            Long user =  session.getAttribute("user") == null ? 0L : (Long) session.getAttribute("user");
            UserModel userModel = new UserModel();
            UserBean userBean;
            try {
                userBean = userModel.doRetrieveByKey(List.of(user));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        %>
        <a href="${pageContext.request.contextPath}/index.jsp" id="logo">
            <img src="${pageContext.request.contextPath}/homepage/LOGO.png" alt="Logo">
        </a>
        <ul>
            <li><a href="${pageContext.request.contextPath}/catalogue/catalogue.jsp">Catalogo</a></li>
            <li><a href="${pageContext.request.contextPath}/cart/cart.jsp">Carrello</a></li>
            <li><a href="${pageContext.request.contextPath}/favourites/favourites.jsp">Preferiti</a></li>
            <% if (userBean != null && userBean.getAdmin()) { %>
            <li><a href="${pageContext.request.contextPath}/user/logout">Logout</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/userManager.jsp">UserManager</a> </li>
            <li><a href="${pageContext.request.contextPath}/admin/productList.jsp">ProductManager</a> </li>
            <% }
                if (userBean != null) {%>
            <li><a href="${pageContext.request.contextPath}/user/login.jsp">Sign-in/Sign-up</a></li>
            <% } %>
        </ul>
        <% if (userBean == null) { %>
            <a href="${pageContext.request.contextPath}/user/login.jsp">
        <% } else { %>
            <a href="${pageContext.request.contextPath}/user/personalArea.jsp" >
        <% } %>
                <img class="omino" src="${pageContext.request.contextPath}/homepage/lalal.png" alt="Omino" id="user">
            </a>
            <div class="material-symbols-outlined" id="menu">menu</div>
    </nav>
</header>

