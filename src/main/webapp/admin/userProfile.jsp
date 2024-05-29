<%@ page import="storage.Beans.UserBean" %>
<%@ page import="storage.Models.UserModel" %>
<%@ page import="java.util.List" %>
<%@ page import="storage.Beans.PurchaseBean" %>
<%@ page import="storage.Models.PurchaseModel" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="storage.Models.WatchModel" %>
<%--

  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 29/05/2024
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile</title>
    <%
        WatchModel watchModel = new WatchModel();
        UserBean user = null;
        List<PurchaseBean> orders = null;
        Map<Long, List<PurchaseBean>> groupedById = null;
        String userIdObject = (String) request.getParameter("user");
        System.out.println(userIdObject);
        if(userIdObject != null){
            UserModel userModel = new UserModel();
            PurchaseModel purchaseModel = new PurchaseModel();
            Long userId = Long.parseUnsignedLong(userIdObject);
            try {
                user = userModel.doRetrieveByKey(List.of(userId));
                groupedById = purchaseModel.doRetrieveByCond("WHERE user=?",List.of(userIdObject)).stream()
                        .collect(Collectors.groupingBy(PurchaseBean::getId));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(groupedById);
        }
    %>
</head>
<body>
    <%@include file="../navbar.jsp"%>
    <table>
        <thead>
            <td>Email</td>
            <td>Name</td>
            <td>Cognome</td>
            <td>Cap</td>
            <td>Admin</td>
        </thead>
        <tr>
            <td><a href="${pageContext.request.contextPath}/admin/userProfile.jsp"><%= user.getEmail()%></a></td>
            <td><%= user.getName()%></td>
            <td><%= user.getSurname()%></td>
            <td><%= user.getCAP()%></td>
            <td><%= user.getAdmin()%></td>
        </tr>
    </table>

    <p>Ordini</p>
    <table>
        <thead>
            <td>Orologio</td>
            <td>Quantita</td>
        </thead>
        <% assert groupedById != null;
            for(var entry : groupedById.entrySet()){%>
                <tr>
                    <td colspan="2"><%= entry.getKey()%></td>
                </tr>
                <% for(var el: entry.getValue()){%>
                    <tr>
                        <td><%=watchModel.doRetrieveByKey(List.of(el.getWatch())).getName()%></td>
                        <td><%=el.getQuantity()%></td>
                    </tr>
                <%}%>
        <% } %>
    </table>

    <%@include file="../footer.html"%>
</body>
</html>
