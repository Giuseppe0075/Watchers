<%@ page import="storage.Models.PurchaseModel" %>
<%@ page import="storage.Beans.PurchaseBean" %>
<%@ page import="java.util.*" %>
<%@ page import="storage.Models.WatchModel" %>
<%@ page import="storage.Beans.WatchBean" %><%--
  Created by IntelliJ IDEA.
  User: giuse
  Date: 27/06/2024
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Order History</title>
</head>
<body>
    <%@include file="/navbar.jsp" %>

    <%
        Long userId = Long.parseUnsignedLong(String.valueOf(session.getAttribute("user")));
        PurchaseModel purchaseModel = new PurchaseModel();
        WatchModel watchModel = new WatchModel();
        Collection<PurchaseBean> purchaseBeans;
        try {
            purchaseBeans = purchaseModel.doRetrieveByCond("WHERE user = ? ORDER BY user DESC", List.of(userId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Division of the purchases by order
        HashMap<Long, List<PurchaseBean>> orderMap = new HashMap<>();
        for(PurchaseBean purchaseBean : purchaseBeans) {
            if(orderMap.containsKey(purchaseBean.getId())) {
                orderMap.get(purchaseBean.getId()).add(purchaseBean);
            } else {
                List<PurchaseBean> list = new ArrayList<>();
                list.add(purchaseBean);
                orderMap.put(purchaseBean.getId(), list);
            }
        }
    %>

    <div id="container">
        <h1>Order History</h1>
        <br><br>
        <% for(Map.Entry<Long, List<PurchaseBean>> entry : orderMap.entrySet()) { %>
            <div class="order">
                <h2>Order <%= entry.getKey() %></h2>
                <table>
                    <tr>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                    </tr>
                    <% for(PurchaseBean purchaseBean : entry.getValue()) {
                        WatchBean watchBean;
                        try {
                             watchBean = watchModel.doRetrieveByKey(List.of(purchaseBean.getWatch()));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    %>
                        <tr>
                            <td><%= watchBean.getName() %></td>
                            <td><%= purchaseBean.getQuantity() %></td>
                            <td><%= purchaseBean.getPrice() %>€</td>
                            <td><%= purchaseBean.getQuantity() * purchaseBean.getPrice() %>€</td>
                        </tr>
                    <% } %>
                </table>
            </div>
        <% } %>
    </div>
    <%@include file="/footer.html" %>
</body>
</html>
