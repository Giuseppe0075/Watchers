<%--
  Created by IntelliJ IDEA.
  User: giuse
  Date: 29/04/2024
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test Photo</title>
</head>
<body>
    <h1>Image:</h1>
    <img src = "${pageContext.request.contextPath}/getPhoto" alt="Not available" id="photo">
</body>
</html>
