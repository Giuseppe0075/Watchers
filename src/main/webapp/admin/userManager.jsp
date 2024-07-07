<%@ page import="Model.Beans.UserBean" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Models.UserModel" %>
<%@ page import="filters.UserFilter" %><%--
  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 22/05/2024
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Manager</title>
    <link rel="stylesheet" href="../style/styleFooter.css">
    <link rel="stylesheet" href="../style/styleUserManager.css">
    <style>
        /* Stile per il container principale */
        .container {
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 1000px;
            margin: 20px auto;
        }

        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0;
            border: 1px solid #ddd;
            border-radius: 10px;
            overflow: hidden;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #5d9ea8;
            color: white;
        }

        .table-container {
            overflow-x: auto;
        }

    </style>
    <%
        UserModel um = new UserModel();
        List<UserBean> users= null;
        try {
            users = (List<UserBean>) um.doRetrieveAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    %>
</head>
<body>
<%@include file="../navbar.jsp"%>
<div class="container">
    <h2>Gestione Utenti</h2>
    <div class="table-container">
        <table>
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
<%@include file="../footer.html"%>
</body>
</html>
