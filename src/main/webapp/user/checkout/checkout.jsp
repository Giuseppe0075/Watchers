<%@ page import="storage.Beans.WatchBean" %>
<%@ page import="storage.Models.WatchModel" %>
<%@ page import="java.util.List" %>
<%@ page import="storage.Models.ImageModel" %>
<%@ page import="java.util.Collection" %>
<%@ page import="storage.Beans.ImageBean" %><%--
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
</head>
<body>
    <%@include file="../../navbar.jsp"%> <!-- Navabar -->
    <%
        String[] watches = request.getParameterValues("watch");
        String[] quantities = request.getParameterValues("quantity");
        int n = watches.length;
        WatchModel watchModel = new WatchModel();
        ImageModel imageModel = new ImageModel();
    %>
    <div class="container">
        <h2> Checkout </h2><br>
        <form action="#" method="post">

            <%
                for(int i = 0; i < n; i++) {
                    String watchId = watches[i];
                    int quantity = Integer.parseInt(quantities[i]);

                    // Get the watch from the database
                    try {
                        WatchBean watchBean = watchModel.doRetrieveByKey(List.of(watchId));
                        Double price = watchBean.getPrice();
                        String name = watchBean.getName();
                        Collection<ImageBean> images = imageModel.doRetrieveByCond("WHERE watch=?",List.of(watchId));
                        // Get the first image
                        ImageBean image = images.stream().findFirst().orElse(null);
                        if(image == null){
                            image = new ImageBean();
                        }
            %>

                        <div class="element" id="watch<%=watchId%>">
                            <label>
                                <input hidden class="priceForOne" value="<%=price%>">
                            </label>
                            <div class="details">
                                <img class="photo" src="${pageContext.request.contextPath}/getImage?id=<%=image.getId()%>&watch=<%=watchId%>" alt="Immagine al momento non disponibile">

                                <div> <%=name%> </div>
                            </div>
                            <div>
                                <label>
                                    <input type="number" name="quantity" class="quantity" value="<%=quantity%>" min="1" max="<%=watchBean.getStock()%>" style="width: 50px; text-align: center;">
                                </label>
                                <div class="price" data-single-price="<%=price%>"> <%=price * quantity%> € </div>
                            </div>
                            <button type="button" onclick="removeItem(<%=watchId%>)">Remove</button>
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
</body>
</html>
