<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="Model.Models.PurchaseModel" %>
<%@ page import="Model.Beans.PurchaseBean" %>
<%@ page import="Model.Models.WatchModel" %>
<%@ page import="java.sql.Date" %>
<%@ page import="Model.Beans.WatchBean" %>
<%@ page import="java.util.TreeMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.NavigableMap" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 18/06/2024
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/styleOrderList.css">
</head>
<body>
<%@include file="../navbar.jsp"%>

<%
    UserModel userModel = new UserModel();
    PurchaseModel purchaseModel = new PurchaseModel();
    WatchModel watchModel = new WatchModel();
    java.util.Collection<PurchaseBean> purchaseBeans;
    List<UserBean> userBeans= new ArrayList<>();
    String minDateString = request.getParameter("minDate");
    String maxDateString = request.getParameter("maxDate");
    Date minDate = minDateString != null && !minDateString.isEmpty() ? Date.valueOf(minDateString) : Date.valueOf("1970-01-01");
    Date maxDate = maxDateString != null && !maxDateString.isEmpty() ? Date.valueOf(maxDateString) : Date.valueOf("2100-01-01");
    Long userId = request.getParameter("user") != null ? Long.parseLong(request.getParameter("user")) : 0L;



    try {
        userBeans = (List<UserBean>) userModel.doRetrieveAll();
        if(userId != 0L)
            purchaseBeans = purchaseModel.doRetrieveByCond("WHERE user = ? AND date >= ? AND date <= ? ORDER BY date DESC", List.of(userId, minDate, maxDate));
        else
            purchaseBeans = purchaseModel.doRetrieveByCond("WHERE date >= ? AND date <= ? ORDER BY date DESC", List.of(minDate, maxDate));
    } catch (Exception e) {
        throw new RuntimeException(e);
    }

    //Division of the purchases by order
    java.util.TreeMap<java.util.Date, List<PurchaseBean>> orderMap = new TreeMap<>();
    for(PurchaseBean purchaseBean : purchaseBeans) {
        if(orderMap.containsKey(purchaseBean.getDate())) {
            orderMap.get(purchaseBean.getDate()).add(purchaseBean);
        } else {
            List<PurchaseBean> list = new ArrayList<>();
            list.add(purchaseBean);
            orderMap.put(purchaseBean.getDate(), list);
        }
    }

    // Reverse the order of the TreeMap using descendingMap()
    NavigableMap<java.util.Date, List<PurchaseBean>> reversedOrderMap = orderMap.descendingMap();
%>
<div class="order-container">
    <div class="form-filter">
        <h1>Orders</h1>

        <!-- Form per selezionare la data -->
        <form method="GET" action="orderList.jsp" class="filter-form">
            <label for="minDate">From: </label>
            <input type="date" id="minDate" name="minDate" value="<%=minDateString != null ? minDateString : ""%>">
            <label for="maxDate">To: </label>
            <input type="date" id="maxDate" name="maxDate" value="<%=maxDateString != null ? maxDateString : ""%>">
            <label for="user">User</label>
            <select id="user" name="user">
                <option selected>All</option>
                <% for(var user : userBeans){ %>
                <option value="<%=user.getId()%>"><%=user.getEmail()%></option>
                <% } %>
            </select>

            <input type="submit" value="Filter">
        </form>
    </div>

    <div id="container" style="overflow-x: scroll">
        <h1>Order History</h1>
        <table>
            <tr>
                <th>Product</th>
                <th>Date </th>
                <th>Quantity</th>
                <th>Price</th>
                <th>IVA</th>
                <th>Total</th>
            </tr>
            <% for(java.util.Date i : reversedOrderMap.keySet()) {
                List<PurchaseBean> purchaseBeansList = orderMap.get(i);

                for(PurchaseBean purchaseBean : purchaseBeansList) {
                    WatchBean watchBean;
                    try {
                        watchBean = watchModel.doRetrieveByKey(List.of(purchaseBean.getWatch()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
            %>
            <tr>
                <td><%= watchBean.getName() %></td>
                <td><%= purchaseBean.getDate() %></td>
                <td><%= purchaseBean.getQuantity() %></td>
                <td><%= purchaseBean.getPrice() %>€</td>
                <td><%= purchaseBean.getIVA() %>%</td>
                <td><%= purchaseBean.getQuantity() * purchaseBean.getPrice() %>€</td>
            </tr>
            <% }
            } %>
        </table>
    </div>
</div>

<%@include file="../footer.html"%> <!-- Footer -->

</body>
</html>
