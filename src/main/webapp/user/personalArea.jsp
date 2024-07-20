<%@ page import="Model.Models.UserModel" %>
<%@ page import="Model.Beans.UserBean" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Models.PurchaseModel" %>
<%@ page import="Model.Beans.PurchaseBean" %>
<%@ page import="java.util.*" %>
<%@ page import="Model.Models.WatchModel" %>
<%@ page import="Model.Beans.WatchBean" %><%--
  Created by IntelliJ IDEA.
  User: giuse
  Date: 14/05/2024
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Personal Area</title>
    <link rel="stylesheet" type="text/css" href="../style/stylePersonalArea.css">
</head>
<body>
    <!-- Navbar -->
    <%@include file="../navbar.jsp"%>

    <%
        UserBean user;
        try {
            Long userId = Long.parseUnsignedLong(String.valueOf(session.getAttribute("user")));
            UserModel userModel = new UserModel();
            user = userModel.doRetrieveByKey(List.of(userId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    %>
    <div class="userContainer">
        <div class="mainDetails">
            <div class="header"> <%=user.getName()%></div>
            <div class="header"> <%=user.getSurname()%></div>
        </div>
        <div class="details">
            <div class="detail"> <%=user.getEmail()%> </div>
            <div class="detail"> <%=user.getBirthday()%> </div>
            <div class="detail"> <%=user.getRoad()%>, <%=user.getCivic_number()%>, <%=user.getCAP()%> </div>
            <div class="detail"> <%=user.getCity()%> </div>
        </div>


        <button onclick="makeEditable()">Modifica</button>

    </div>



    <%
        Long userId = Long.parseUnsignedLong(String.valueOf(session.getAttribute("user")));
        PurchaseModel purchaseModel = new PurchaseModel();
        WatchModel watchModel = new WatchModel();
        Collection<PurchaseBean> purchaseBeans;
        UserModel userModel = new UserModel();
        UserBean userBean;

        try {
            purchaseBeans = purchaseModel.doRetrieveByCond("WHERE user = ? ORDER BY id DESC", List.of(userId));
            userBean = userModel.doRetrieveByKey(List.of(userId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long hid = purchaseBeans.iterator().next().getId();

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
        <% for(long i = hid; i >= 1; i--) {
            if(!orderMap.containsKey(i)) {
                continue;
            }
            List<PurchaseBean> purchaseBeansList = orderMap.get(i);
        %>
        <div class="order" style="overflow-x: scroll">
            <h2>Order <%= i %></h2>
            <table>
                <tr>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>IVA</th>
                    <th>Total</th>
                </tr>
                <% for(PurchaseBean purchaseBean : purchaseBeansList) {
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
                    <td><%= purchaseBean.getIVA() %>%</td>
                    <td><%= purchaseBean.getQuantity() * purchaseBean.getPrice() %>€</td>
                </tr>

                <div id="hidden-data-for-<%=i%>" hidden>
                    <div class="date"><%=purchaseBean.getDate()%></div>
                    <div class="user-name"><%=userBean.getName()%></div>
                    <div class="user-surname"><%=userBean.getSurname()%></div>
                    <div class="user-address"><%=userBean.getRoad()%>,<%=userBean.getCivic_number()%></div>
                </div>
                <% } %>
            </table>
            <button onclick="createInvoice(this)">Scarica Fattura</button>
        </div>
        <% } %>
    </div>

    <script src="orderHistory.js"></script>
    <!-- Footer -->
    <%@include file="../footer.html"%>
</body>
</html>
