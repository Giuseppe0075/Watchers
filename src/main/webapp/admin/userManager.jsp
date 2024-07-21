<%@ page import="Model.Beans.UserBean" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Models.UserModel" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 22/05/2024
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User Manager</title>
    <link rel="stylesheet" href="../style/styleUserManager.css">
    <style>




    </style>
    <%
        UserModel um = new UserModel();
        List<UserBean> users;
        try {
            users = (List<UserBean>) um.doRetrieveAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    %>
</head>
<body>
<%@include file="../navbar.jsp"%>
<div class="user-container">
    <h2 style="text-align: center">User Manager</h2>
    <div style="overflow-x: scroll">
    <div class="table-container">
        <table >
            <thead>
            <tr>
                <th>Email</th>
                <th>Nome</th>
                <th>Cognome</th>
                <th>CAP</th>
                <th>Admin</th>
            </tr>
            </thead>
            <tbody>
            <% for(UserBean userr : users) { %>
            <tr>
                <td><a href="${pageContext.request.contextPath}/admin/userProfile.jsp?id=<%=userr.getId()%>"><%= userr.getEmail()%></a></td>
                <td><%= userr.getName()%></td>
                <td><%= userr.getSurname()%></td>
                <td><%= userr.getCAP()%></td>
                <td><%= userr.getAdmin()%></td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
    </div>
</div>
<%@include file="../footer.html"%>
</body>
</html>
