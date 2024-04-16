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
</head>
<body>

        <%@include file="navbar.html"%>
        <div id="mainBox">
            <div id="username">
                <h1>Time <%= new Date()%></h1>
                username
                <input type="text" id="inputUser" name="username"><br>
            </div>
            <div id="password">
                password
                <input type="text" id="inputPassword" name="password"><br>
            </div>
        </div>
</body>
</html>
