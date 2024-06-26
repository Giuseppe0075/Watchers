<%@ page import="storage.Models.PurchaseModel" %>
<%@ page import="storage.Beans.PurchaseBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="storage.Models.WatchModel" %><%--
  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 18/06/2024
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ordini</title>
    <link rel="stylesheet" href="../style/styleFooter.css">
</head>
<body>
<%
    WatchModel watchModel = new WatchModel();
    PurchaseModel orderModel = new PurchaseModel();
    Map<Long, List<PurchaseBean>> orders;

    String dateParam = request.getParameter("date");
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
<p>Ordini</p>

<!-- Form per selezionare la data -->
<form method="GET" action="">
    <label for="date">Filtra per data:</label>
    <input type="date" id="date" name="date" value="<%= dateParam != null ? dateParam : "" %>">
    <input type="submit" value="Filtra">
</form>

<table>
    <thead>
    <tr>
        <th>Orologio</th>
        <th>Quantita</th>
        <th>Prezzo</th>
        <th>Iva</th>
    </tr>
    </thead>
    <% if (orders != null) {
        for (var entry : orders.entrySet()) {
            Date dataOrdine = entry.getValue().get(0).getDate();
            double totalOrderPrice = entry.getValue().stream().mapToDouble(PurchaseBean::getPrice).sum(); %>
    <tr class="order-total">
        <td colspan="3">Ordine #<%= entry.getKey() %> - Totale Prezzo: €<%= totalOrderPrice %>  --- <%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(dataOrdine) %> </td>
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
</table>

</body>
</html>
