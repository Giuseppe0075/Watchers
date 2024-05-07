<%@ page import="storage.Beans.CartElementBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 30/04/2024
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% HttpSession session1 = request.getSession(); %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <%@include file="../navbar.jsp"%>
  <table>
    <tr>
      <td>Orologio</td>
      <td>Quantità</td>
      <td>Azioni</td>
    </tr>
    <%
      List<CartElementBean> cart = (List<CartElementBean>) session1.getAttribute("cart");
      if(cart == null) {
        cart = new ArrayList<>();
        session1.setAttribute("cart", cart);
      }%>


      <%for(CartElementBean element : cart) { %>
      <tr>
        <td><%= element.getWatch()%></td>
        <td><%= element.getQuantity() %></td>
        <td>
          <form action="${pageContext.request.contextPath}/cart" method="post">
            <input type="hidden" name="watch" value="<%= element.getWatch()%>">
            <label>
              <input type="number"  name="quantity" value="<%= element.getQuantity() %>">
            </label>
            <input type="submit" name="action" value="update">
          </form>
          <form action="${pageContext.request.contextPath}/cart" method="post">
            <input type="hidden" name="watch" value="<%= element.getWatch()%>">
            <input type="submit" name="action" value="remove">
          </form>
        </td>
      </tr>
    <% } %>
  </table>
  <table>
    <tr>Resoconto</tr>
  </table>
</body>
</html>
