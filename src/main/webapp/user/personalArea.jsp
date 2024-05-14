<%@ page import="storage.Models.UserModel" %>
<%@ page import="storage.Beans.UserBean" %>
<%@ page import="java.util.List" %>
<%@ page import="org.tinylog.Logger" %><%--
  Created by IntelliJ IDEA.
  User: giuse
  Date: 14/05/2024
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Personal Area</title>
</head>
<body>
    <%@include file="../navbar.jsp"%>
    <%
        UserBean user;
        try {
            Long userId = Long.parseUnsignedLong(String.valueOf(session.getAttribute("user")));
            UserModel userModel = new UserModel();
            user = userModel.doRetrieveByKey(List.of(userId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    %>
    <div class="container">
        <div class="mainData">
            <div class="header"> <%=user.getName()%></div>
            <div class="header"> <%=user.getSurname()%></div>
        </div>
        <div class="details">
            <div class="detail"> <%=user.getEmail()%> </div>
            <div class="detail"> <%=user.getBirthday()%> </div>
            <div class="detail"> <%=user.getRoad()%>, <%=user.getCivic_number()%>, <%=user.getCAP()%> </div>
            <div class="detail"> <%=user.getCity()%> </div>
        </div>
    </div>
</body>
</html>
