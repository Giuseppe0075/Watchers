<%@ page import="storage.Beans.CartElementBean" %>
<%@ page import="java.util.List" %>
<%@ page import="ShoppingCart.ShoppingCart" %>
<%@ page import="storage.Beans.WatchBean" %>
<%@ page import="storage.Models.WatchModel" %>
<%@ page import="storage.Models.ImageModel" %>
<%@ page import="storage.Beans.ImageBean" %>
<%@ page import="java.util.Collection" %><%--
  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 30/04/2024
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
  <head>
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="../style/styleFooter.css">
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
    Long userId = session.getAttribute("user") != null ? Long.parseUnsignedLong(String.valueOf(session.getAttribute("user"))) : 0;
    WatchModel watchModel = new WatchModel();
    ImageModel imageModel = new ImageModel();
  %>

  <!-- Container -->
  <div class="container">
    <h1>Shopping Cart</h1>

    <!-- Cart elements -->
    <form method="post" action="${pageContext.request.contextPath}/user/checkout/checkout.jsp" class="elements">
      <label for="userId"></label>
      <input hidden type="text" name="userId" id="userId" value="<%=userId%>">

      <% for(CartElementBean element : cart) {
        WatchBean watch = null;
        Collection<ImageBean> images = null;
        // Get the watch and the images
        try {
          watch = watchModel.doRetrieveByKey(List.of(element.getWatch()));
          images = imageModel.doRetrieveByCond("WHERE watch=? ", List.of(watch.getId()));
        } catch (Exception e) {
          throw new RuntimeException(e);
        }

        // Get the first image
        ImageBean image = images.stream().findFirst().orElse(null);
        if(image == null){
          image = new ImageBean();
        }
      %>
      <!-- Single watch -->
      <div class="element" id="watch<%=watch.getId()%>">
        <!-- Watch details -->
        <div class="details">

          <!-- Hidden, used for getting watch id from javascript -->
          <label>
            <input hidden type="text" name="watch" class="watch" value="<%=watch.getId()%>">
          </label>


          <img class="photo" src="${pageContext.request.contextPath}/getImage?id=<%=image.getId()%>&watch=<%=watch.getId()%>" alt="Immagine al momento non disponibile">

          <div>
            <div><strong><%=watch.getName()%></strong></div>
            <div><%=watch.getBrand()%></div>
          </div>

        </div>

        <!-- Quantity -->
        <div>
          <label>
            <input type="number" name="quantity" class="quantity" value="<%=element.getQuantity()%>" min="1" max="<%=watch.getStock()%>" style="width: 50px; text-align: center;">
          </label>
        </div>
        <button type="button" name="remove" onclick="removeItem(<%=watch.getId()%>)">Remove</button>
      </div>
      <% }
      if(cart.isEmpty()){ %>
        <h2>Your cart is empty</h2>
      <% } else { %>
        <button type="submit" name="checkout">Checkout</button>
      <% } %>
    </form>
  </div>
  <script src="cart.js"></script>
  </body>
</html>