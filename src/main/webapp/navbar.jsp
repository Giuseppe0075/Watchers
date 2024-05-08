<!DOCTYPE html>
<html>
<head>
    <title>Navigation bar DEMO</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/homepage/style.css">
</head>
<body>
<header>
    <a href="${pageContext.request.contextPath}/index.jsp">
    <img src="${pageContext.request.contextPath}/homepage/LOGO.png">
    </a>
    <nav>
        <div>
            <ul>
                <li><a href="${pageContext.request.contextPath}/catalogue/catalogue.jsp">Catalogo</a></li>
                <li><a href="${pageContext.request.contextPath}/cart/cart.jsp">Carrello</a></li>
                <li><a href="#">Menu2</a></li>
                <li><a href="#">Logout</a></li>
            </ul>
        </div>
    </nav>

    <a href=""><img class="omino" src="${pageContext.request.contextPath}/homepage/lalal.png"></a>
</header>
</body>
</html>

