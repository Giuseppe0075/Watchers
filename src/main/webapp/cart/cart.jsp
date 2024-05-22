<%@ page import="storage.Beans.CartElementBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ShoppingCart.ShoppingCart" %>
<%@ page import="storage.Beans.WatchBean" %>
<%@ page import="storage.Models.WatchModel" %><%--
  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 30/04/2024
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <%@include file="../navbar.jsp"%>
  <%
    List<CartElementBean> cart = new ShoppingCart(session).getCart();
    WatchModel watchModel = new WatchModel();
  %>

  <!-- Container -->
  <div class="container">

    <!-- Categories -->
    <div class="categories">
      <div class="table-header">Watch</div>
      <div class="table-header">Quantity</div>
    </div>

    <!--All Watches -->
    <div class="elements">
      <% for(CartElementBean element : cart) {
        WatchBean watch = null;
          try {
               watch = watchModel.doRetrieveByKey(List.of(element.getWatch()));
          } catch (Exception e) {
              throw new RuntimeException(e);
          }
      %>
      <!-- Single watch -->
        <div class="element">
          <!-- Watch details -->
          <div class="details">
            <%=watch.getName()%>

            <%=watch.getBrand()%>
          </div>
          <!-- Quantity -->
          <div class="quantity">
            <%=element.getQuantity()%>
          </div>
        </div>
      <% } %>
    </div>
  </div>
</body>
</html>