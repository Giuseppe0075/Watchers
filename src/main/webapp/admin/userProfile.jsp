<%@ page import="storage.Beans.UserBean" %>
<%@ page import="storage.Models.UserModel" %>
<%@ page import="java.util.List" %>
<%@ page import="storage.Beans.PurchaseBean" %>
<%@ page import="storage.Models.PurchaseModel" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="storage.Models.WatchModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile</title>
    <%
        WatchModel watchModel = new WatchModel();
        UserBean user = null;
        Map<Long, List<PurchaseBean>> groupedById = null;

        String userIdObject = request.getParameter("id");

        if (userIdObject != null) {
            UserModel userModel = new UserModel();
            PurchaseModel purchaseModel = new PurchaseModel();
            try {
                Long userId = Long.parseLong(userIdObject);
                user = userModel.doRetrieveByKey(List.of(userId));
                groupedById = purchaseModel.doRetrieveByCond("WHERE user=?", List.of(userId)).stream()
                        .collect(Collectors.groupingBy(PurchaseBean::getId));
            } catch (Exception e) {
                e.printStackTrace(); // Stampa l'errore sulla console del server
                throw new RuntimeException("Errore durante il recupero dei dati dell'utente o degli ordini", e);
            }
            System.out.println(groupedById);
        } else {
            throw new RuntimeException("ID utente non fornito.");
        }
    %>
    <style>
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input, .form-group select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .order-total {
            font-weight: bold;
        }
    </style>
</head>
<body>
<%@include file="../navbar.jsp"%>
<% if (user != null) { %>
<form action="${pageContext.request.contextPath}/admin/updateUser" method="get">
    <input type="hidden" name="id" value="<%= user.getId() %>">
    <div class="form-group">
        <label for="email">Email</label>
        <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required>
    </div>
    <div class="form-group">
        <label for="name">Name</label>
        <input type="text" id="name" name="name" value="<%= user.getName() %>" required>
    </div>
    <div class="form-group">
        <label for="surname">Surname</label>
        <input type="text" id="surname" name="surname" value="<%= user.getSurname() %>" required>
    </div>
    <div class="form-group">
        <label for="cap">CAP</label>
        <input type="text" id="cap" name="cap" value="<%= user.getCAP() %>" required>
    </div>
    <div class="form-group">
        <label for="admin">Admin</label>
        <select id="admin" name="admin" required>
            <option value="true" <%= user.getAdmin() ? "selected" : "" %>>Yes</option>
            <option value="false" <%= !user.getAdmin() ? "selected" : "" %>>No</option>
        </select>
    </div>
    <button type="submit">Update</button>
</form>
<p>Ordini</p>
<table>
    <thead>
    <tr>
        <th>Orologio</th>
        <th>Quantita</th>
        <th>Prezzo</th>
    </tr>
    </thead>
    <% if (groupedById != null) {
        for (var entry : groupedById.entrySet()) {
            double totalOrderPrice = entry.getValue().stream().mapToDouble(PurchaseBean::getPrice).sum(); %>
    <tr class="order-total">
        <td colspan="3">Ordine #<%= entry.getKey() %> - Totale Prezzo: €<%= totalOrderPrice %></td>
    </tr>
    <% for (var el : entry.getValue()) { %>
    <tr>
        <td><%= watchModel.doRetrieveByKey(List.of(el.getWatch())).getName() %></td>
        <td><%= el.getQuantity() %></td>
        <td>€<%= el.getPrice() %></td>
    </tr>
    <% } %>
    <% }
    }
} %>
</table>

<%@include file="../footer.html"%>
</body>
</html>
