<%@ page import="storage.Models.PurchaseModel" %>
<%@ page import="java.util.Collection" %>
<%@ page import="storage.Beans.PurchaseBean" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="org.tinylog.Logger" %><%--
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
        Collection<PurchaseBean> purchaseBeans;
        try {
            purchaseBeans = purchaseModel.doRetrieveByCond("WHERE user = ? ORDER BY user DESC", List.of(userId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Iterator<PurchaseBean> iterator = purchaseBeans.iterator();
    %>

    <div id="container">
        <h1>Order History</h1>
        <% while(iterator.hasNext()) {
            PurchaseBean purchaseBean = iterator.next();
            long orderId = purchaseBean.getId();
            while(purchaseBean.getId() == orderId) {
                Logger.debug("Order: {}", purchaseBean.getId());
            %>
                <div class="orderGroup">
                    <h2>Order <%= orderId %></h2>
                    <div class="details">
                        <p> Product: <%=purchaseBean.getWatch()%></p>
                        <p> Quantity: <%=purchaseBean.getQuantity()%></p>
                        <p> Price: <%=purchaseBean.getPrice()%></p>
                        <p> Date: <%=purchaseBean.getDate()%></p>
                    </div>
                </div>
            <%
                if(iterator.hasNext()) {
                    purchaseBean = iterator.next();
                } else {
                    break;
                }
            }
        }
        %>
    </div>
    <%@include file="/footer.html" %>
</body>
</html>
