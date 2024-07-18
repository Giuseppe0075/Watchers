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
        /* Stile per il container principale */
        .user-container {
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin:auto;
        }

        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0;
            border: 1px solid #ddd;
            border-radius: 10px;
            overflow: hidden;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }



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
    <h2 style="text-align: center">Gestione Utenti</h2>
    <div>
    <div class="table-container">
        <table style="overflow-x: scroll">
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
