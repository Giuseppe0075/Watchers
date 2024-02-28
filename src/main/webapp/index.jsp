<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h2>Hello World!</h2>
<a href="hello-servlet">hello</a>
<a href="login-servlet">login</a>
<form id = "name" action="hello-servlet" method="get">
    <label>Nome:</label><br>
    <input type="text">
    <input type="submit">
</form>
</body>
</html>
