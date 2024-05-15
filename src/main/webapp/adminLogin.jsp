<%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 17/04/2024
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Login</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/admin-login-servlet" method="post">
    <label for="email">Email</label><br>
    <input type="email" id="email" name="email"><br>
    <label for="password">Password</label><br>
    <input type="password" id="password" name="password"><br>
    <input type="submit"><br>
</form>

</body>
</html>
