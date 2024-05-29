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
        UserModel userModel = new UserModel();
        List<UserBean> users= (List<UserBean>) userModel.doRetrieveAll();
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

        <% for(UserBean user : users){ %>
            <tr>
                <td><a href="${pageContext.request.contextPath}/admin/userProfile.jsp?user=<%=user.getId()%>"><%= user.getEmail()%></a> </td>
                <td><%= user.getName()%></td>
                <td><%= user.getSurname()%></td>
                <td><%= user.getCAP()%></td>
                <td><%= user.getAdmin()%></td>
            </tr>
        <% } %>
    </table>
    <%@include file="../footer.html"%>
</body>
</html>
