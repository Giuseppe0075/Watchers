<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Models.PurchaseModel" %>
<%@ page import="Model.Beans.PurchaseBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="Model.Models.WatchModel" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 18/06/2024
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Ordini</title>
    <link rel="stylesheet" href="../style/styleOrderList.css">
</head>
<body>
<%
    UserModel userModelFilter = new UserModel();
    WatchModel watchModel = new WatchModel();
    PurchaseModel orderModel = new PurchaseModel();
    Map<Long, List<PurchaseBean>> orders;
    List<UserBean> users;
    try {
       users = (List<UserBean>) userModelFilter.doRetrieveAll();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }

    String dateParam = request.getParameter("date");
    Long userIdFilter = Long.parseUnsignedLong(request.getParameter("user") == null ? "0" : request.getParameter("user"));
    Date filterDate = null;
    if (dateParam != null && !dateParam.isEmpty()) {
        try {
            filterDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dateParam);
        } catch (Exception e) {
            // Handle parsing error
        }
    }

    try {
        List<PurchaseBean> allOrders = (List<PurchaseBean>) orderModel.doRetrieveAll();

        // Applica il filtro per utente se selezionato
        if (userIdFilter != 0) {
            allOrders = allOrders.stream()
                    .filter(order -> order.getUser().equals(userIdFilter))
                    .collect(Collectors.toList());
        }

        // Applica il filtro per data, se specificata
        if (filterDate != null) {
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(filterDate);

            allOrders = allOrders.stream()
                    .filter(order -> {
                        cal2.setTime(order.getDate());
                        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
                    })
                    .collect(Collectors.toList());
        }

        orders = allOrders.stream().collect(Collectors.groupingBy(PurchaseBean::getId));
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
%>

<%@include file="../navbar.jsp"%>
<div class="container">
    <div class="form-filter">
        <p>Ordini</p>

        <!-- Form per selezionare la data -->
        <form method="GET" action="" class="filter-form">
            <label for="date">Filtra per data:</label>
            <input type="date" id="date" name="date" value="<%= dateParam != null ? dateParam : "" %>">
            <div class="form-group">
                <label for="user">User:</label>
                <select id="user" name="user" required>
                    <option value="0">ALL</option>
                    <% for(var user : users) {%>
                    <option value="<%=user.getId()%>"><%=user.getName()%></option>
                    <%}%>
                </select>
            </div>

            <input type="submit" value="Filtra">
        </form>
    </div>
    <div style="overflow-x: auto;">
        <table>
            <thead>
            <tr>
                <th>Orologio</th>
                <th>Quantita</th>
                <th>Prezzo</th>
                <th>Iva</th>
            </tr>
            </thead>
            <tbody>
            <% if (orders != null) {
                for (var entry : orders.entrySet()) {
                    Date dataOrdine = entry.getValue().get(0).getDate();
                    double totalOrderPrice = entry.getValue().stream().mapToDouble(PurchaseBean::getPrice).sum(); %>
            <tr class="order-total">
                <td colspan="4">Ordine #<%= entry.getKey() %> - Totale Prezzo: €<%= totalOrderPrice %>  --- <%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(dataOrdine) %>-- <%=userModelFilter.doRetrieveByKey(List.of(entry.getValue().getFirst().getUser())).getName()%> </td>
            </tr>
            <% for (var el : entry.getValue()) { %>
            <tr>
                <td><%= watchModel.doRetrieveByKey(List.of(el.getWatch())).getName() %></td>
                <td><%= el.getQuantity() %></td>
                <td>€<%= el.getPrice() %></td>
                <td><%= el.getIVA() %></td>
            </tr>
            <% }
            }
            } %>
            </tbody>
        </table>
    </div>
</div>

<%@include file="../footer.html"%> <!-- Footer -->

</body>
</html>
