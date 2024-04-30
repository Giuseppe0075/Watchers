<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: giuse
  Date: 28/02/2024
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="homepage/style.css">
</head>
<body>
<%

    if(session.getAttribute("admin") == null || session.getAttribute("admin").equals(false)){
        response.sendRedirect(request.getContextPath() + "admin/adminPage.jsp");
    }
%>

        <%@include file="navbar.jsp"%>

        <form action="${pageContext.request.contextPath}/login-servlet" method="post">
            <label for="email">Email</label><br>
            <input type="email" id="email" name="email"><br>
            <label for="password">Password</label><br>
            <input type="password" id="password" name="password"><br>
            <input type="submit"><br>
        </form>

</body>
</html>
