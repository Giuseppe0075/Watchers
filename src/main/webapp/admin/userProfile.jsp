<%@ page import="Model.Beans.UserBean" %>
<%@ page import="Model.Models.UserModel" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Beans.PurchaseBean" %>
<%@ page import="Model.Models.PurchaseModel" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="Model.Models.WatchModel" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile</title>
    <%
        Long sessionUser = (Long) session.getAttribute("user");
        WatchModel watchModel = new WatchModel();
        UserBean userModify = null;
        Map<Long, List<PurchaseBean>> groupedById = null;

        String userIdObject = request.getParameter("id");

        if (userIdObject != null) {
            UserModel userModel = new UserModel();
            Long userId = Long.parseLong(userIdObject);
            PurchaseModel purchaseModel = new PurchaseModel();
            try {
                userModify = userModel.doRetrieveByKey(List.of(userId));
                groupedById = purchaseModel.doRetrieveByCond("WHERE user=?", List.of(userId)).stream()
                        .collect(Collectors.groupingBy(PurchaseBean::getId));
            } catch (Exception e) {
                throw new RuntimeException("Errore durante il recupero dei dati dell'utente o degli ordini", e);
            }
            System.out.println(groupedById);
        } else {
            throw new RuntimeException("ID utente non fornito.");
        }
    %>
    <style>
        /* Stile per il container principale */
        .container {
            padding: 10px;
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: space-between;
        }

        .form-container,
        .order-list-container {
            flex: 1 1 45%;
            box-sizing: border-box;
        }

        /* Ensure form and order list stack vertically on small screens */
        @media (max-width: 768px) {
            .form-container,
            .order-list-container {
                flex: 1 1 100%;
            }
        }

        .form-container, .order-list-container {
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-group input, .form-group select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        button[type="submit"] {
            background-color: #5d9ea8;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }
        button[type="submit"]:hover {
            background-color: #498e99;
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

        .order-total {
            font-weight: bold;
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
<%@include file="../navbar.jsp"%>
<div class="container">
    <div class="form-container">
        <% if (userModify != null) { %>
        <form action="${pageContext.request.contextPath}/admin/updateUser" method="post">
            <input type="hidden" name="id" value="<%= userModify.getId() %>">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" value="<%= userModify.getEmail() %>" required>
            </div>
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" value="<%= userModify.getName() %>" required>
            </div>
            <div class="form-group">
                <label for="surname">Surname</label>
                <input type="text" id="surname" name="surname" value="<%= userModify.getSurname() %>" required>
            </div>
            <div class="form-group">
                <label for="cap">CAP</label>
                <input type="text" id="cap" name="cap" value="<%= userModify.getCAP() %>" required>
            </div>
            <% if (!userModify.getId().equals(sessionUser)) { %>
            <div class="form-group">
                <label for="admin">Admin</label>
                <select id="admin" name="admin" required>
                    <option value="true" <%= userModify.getAdmin() ? "selected" : "" %>>Yes</option>
                    <option value="false" <%= !userModify.getAdmin() ? "selected" : "" %>>No</option>
                </select>
            </div>
            <% } %>
            <button type="submit">Update</button>
        </form>
        <% } %>
    </div>

    <div class="order-list-container">
        <p>Ordini</p>
        <table>
            <thead>
            <tr>
                <th>Orologio</th>
                <th>Quantita</th>
                <th>Prezzo</th>
            </tr>
            </thead>
            <tbody>
            <% if (groupedById != null) {
                for (var entry : groupedById.entrySet()) {
                    Date dataOrdine = entry.getValue().get(0).getDate();
                    double totalOrderPrice = entry.getValue().stream().mapToDouble(PurchaseBean::getPrice).sum(); %>
            <tr class="order-total">
                <td colspan="3">Ordine #<%= entry.getKey() %> - Totale Prezzo: €<%= totalOrderPrice %>  --- <%= dataOrdine %> </td>
            </tr>
            <% for (var el : entry.getValue()) { %>
            <tr>
                <td><%= watchModel.doRetrieveByKey(List.of(el.getWatch())).getName() %></td>
                <td><%= el.getQuantity() %></td>
                <td>€<%= el.getPrice() %></td>
            </tr>
            <% } %>
            <% }
            } %>
            </tbody>
        </table>
    </div>
</div>
<%@include file="../footer.html"%>
</body>
</html>