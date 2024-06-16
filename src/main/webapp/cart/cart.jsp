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
  <title>Shopping Cart</title>
  <style>
    .container {
      width: 80%;
      margin: 20px auto;
      background: #fff;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      padding: 20px;
      border-radius: 8px;
    }
    .categories {
      display: flex;
      justify-content: space-between;
      padding: 10px 0;
      border-bottom: 2px solid #ddd;
    }
    .table-header {
      font-weight: bold;
      font-size: 1.2em;
    }
    .elements {
      padding: 20px 0;
    }
    .element {
      display: flex;
      justify-content: space-between;
      padding: 10px 0;
      border-bottom: 1px solid #eee;
    }
    .details {
      flex: 2;
      font-size: 1em;
    }
    .quantity {
      flex: 1;
      font-size: 1em;
      text-align: center;
    }
    .element img {
      max-width: 100px;
      margin-right: 20px;
    }
    .details, .quantity {
      display: flex;
      align-items: center;
    }
  </style>
</head>
<body>
<%@include file="../navbar.jsp"%>
<%
  List<CartElementBean> cart = new ShoppingCart(session).getCart();
  WatchModel watchModel = new WatchModel();
%>

<!-- Container -->
<div class="container">
  <h1>Shopping Cart</h1>

  <!-- Categories -->


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
        <img src="" alt="<%=watch.getName()%>">
        <div>
          <div><strong><%=watch.getName()%></strong></div>
          <div><%=watch.getBrand()%></div>
        </div>
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
