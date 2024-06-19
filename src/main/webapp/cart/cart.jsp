<%@ page import="storage.Beans.CartElementBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ShoppingCart.ShoppingCart" %>
<%@ page import="storage.Beans.WatchBean" %>
<%@ page import="storage.Models.WatchModel" %>
<%@ page import="storage.Models.ImageModel" %>
<%@ page import="storage.Beans.ImageBean" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.net.http.HttpResponse" %><%--
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
    ImageModel imageModel = new ImageModel();
  %>

  <!-- Container -->
  <div class="container">
    <h1>Shopping Cart</h1>

    <!-- Cart elements -->
    <form method="post" action="#" class="elements">
      <% for(CartElementBean element : cart) {
        WatchBean watch = null;
        // Get the watch
        try {
          watch = watchModel.doRetrieveByKey(List.of(element.getWatch()));
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
        Collection<ImageBean> images = null;
        // Get the images
        try {
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
            <input type="number" class="quantity" value="<%=element.getQuantity()%>" min="1" max="99" style="width: 50px; text-align: center;">
          </label>
        </div>
        <button type="button" name="remove" onclick="removeItem(<%=watch.getId()%>)">Remove</button>
      </div>
      <% }
      if(cart.isEmpty()){ %>
        <h2>Your cart is empty</h2>
      <% } else { %>
        <button type="submit" name="checkout" onclick="">Checkout</button>
      <% } %>
    </form>
  </div>
  <script src="cart.js"></script>
  </body>
</html>