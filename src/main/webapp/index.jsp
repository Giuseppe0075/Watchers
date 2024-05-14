<%@ page import="database.DatabaseConnectionPool" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Watchers</title>
    <!-- <link rel="icon" type="image/x-icon" href="/images/favicon.ico"> -->
    <link rel="stylesheet" href="homepage/style.css">

</head>
<body>
<%@include file="navbar.jsp"%> <!-- Navabar -->
<%DatabaseConnectionPool.getInstance();%>
<div id="frontPage">
    <<video id="mainVideo" autoplay muted loop>
        <source src="homepage/home.webm" type="video/webm">
        Your browser does not support the video tag.
    </video>

    <div class="itemPage"> <!-- Item main page -->
        <ul>
            <li><a href="${pageContext.request.contextPath}/watchpage/watch.jsp?id=1"><img src="homepage/watch1.avif"></a></li>
            <li><a href="${pageContext.request.contextPath}/watchpage/watch.jsp?id=2"><img src="homepage/watch2.avif"></a></li>
            <li><a href="${pageContext.request.contextPath}/watchpage/watch.jsp?id=3"><img src="homepage/watch3.avif"></a></li>
        </ul>
    </div>
</div>
</body>
</html>
