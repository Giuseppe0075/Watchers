<%@ page import="storage.Beans.UserBean" %>
<%@ page import="java.util.List" %>
<%@ page import="storage.Models.UserModel" %><%--
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
    <table style="border: solid 2px;">
        <thead>
            <td>Email</td>
            <td>Name</td>
            <td>Cognome</td>
            <td>Cap</td>
            <td>Admin</td>
        </thead>

        <% for(UserBean userr : users){ %>
            <tr>
                <td><a href="${pageContext.request.contextPath}/admin/userProfile.jsp?id=<%=userr.getId()%>"><%= userr.getEmail()%></a> </td>
                <td><%= userr.getName()%></td>
                <td><%= userr.getSurname()%></td>
                <td><%= userr.getCAP()%></td>
                <td><%= userr.getAdmin()%></td>
            </tr>
        <% } %>
    </table>
    <%@include file="../footer.html"%>
</body>
</html>
