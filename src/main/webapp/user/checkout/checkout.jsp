<%@ page import="Model.Beans.WatchBean" %>
<%@ page import="Model.Models.WatchModel" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Models.ImageModel" %>
<%@ page import="java.util.Collection" %>
<%@ page import="Model.Beans.ImageBean" %><%--
  Created by IntelliJ IDEA.
  User: giuse
  Date: 20/06/2024
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Checkout</title>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const message = <%=request.getParameter("message")%>;
            if("<%= request.getParameter("error")%>" === "1"){
                toastr["error"](message!==""?message:"An error occurred");
            }
        });
    </script>
    <link rel="stylesheet" href="../../style/styleCheckout.css">
</head>
<body>
    <%@include file="../../navbar.jsp"%> <!-- Navabar -->

    <%
        String[] watches = request.getParameterValues("watch");
        String[] quantities = request.getParameterValues("quantity");
        if(watches == null || quantities == null){
            String message = "No watches to checkout";
            response.sendRedirect("/cart/cart.jsp?error=1&message="+message);
            return;
        }
        int n = watches.length;
        WatchModel watchModel = new WatchModel();
        ImageModel imageModel = new ImageModel();
    %>
    <div class="container-cart">
        <h2> Checkout </h2><br>
        <form action="${pageContext.request.contextPath}/checkout" method="post">
            <button type="submit">Order</button>
            <%
                for(int i = 0; i < n; i++) {
                    String watchId = watches[i];
                    int quantity = Integer.parseInt(quantities[i]);

                    // Get the watch from the database
                    try {
                        WatchBean watchBean = watchModel.doRetrieveByKey(List.of(watchId));
                        if(watchBean.getStock() < quantity){
                            quantity = watchBean.getStock();
                        }
                        Double price = watchBean.getPrice();
                        String name = watchBean.getName();
                        String brand = watchBean.getBrand();
                        Collection<ImageBean> images = imageModel.doRetrieveByCond("WHERE watch=?",List.of(watchId));
                        // Get the first image
                        ImageBean image = images.stream().findFirst().orElse(null);
                        if(image == null){
                            image = new ImageBean();
                        }

                        if(quantity == 0){
                            String message = "The watch " + name + " is not available in the quantity requested";
                            response.sendRedirect("/cart/cart.jsp?error=1&message="+message);
                            return;
                        }
            %>

                        <div class="element" id="watch<%=watchId%>">
                            <label>
                                <input hidden class="priceForOne" value="<%=price%>">
                            </label>
                            <label>
                                <input hidden class="watchId" name="watchId" value="<%=watchId%>">
                            </label>
                            <div class="details">
                                <img class="photo" src="${pageContext.request.contextPath}/getImage?id=<%=image.getId()%>&watch=<%=watchId%>" alt="Immagine al momento non disponibile">

                                <div>
                                    <div><strong><%=watchBean.getName()%></strong></div>
                                    <div><%=watchBean.getBrand()%></div>
                                </div>
                            </div>
                            <div>
                                <label>
                                    <input hidden type="number" name="quantity" class="quantity" value="<%=quantity%>" min="1" max="<%=watchBean.getStock()%>" style="width: 50px; text-align: center;">
                                </label>
                                <div class="quantity"> <%=quantity%> </div>
                                <div class="price" data-single-price="<%=price%>"> <%=price * quantity%> € </div>
                            </div>
                            <button type="button" name ="remove" onclick="removeItem(<%=watchId%>)">Remove</button>
                        </div><br>
            <%
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            %>

        </form>
    </div>

    <script src="checkout.js"></script>
    <%@include file="../../footer.html"%> <!-- Footer -->
</body>
</html>
