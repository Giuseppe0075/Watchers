<%@ page import="storage.Beans.WatchBean" %>
<%@ page import="storage.Models.WatchModel" %>
<%@ page import="java.util.List" %>
<%@ page import="storage.Models.BrandModel" %>
<%@ page import="storage.Beans.BrandBean" %><%--
  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 30/05/2024
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica Prodotto</title>
</head>
<body>
<%@include file="../navbar.jsp"%>
<%
    BrandModel brandModel = new BrandModel();
    List<BrandBean> brands = (List<BrandBean>) brandModel.doRetrieveAll();
    Long id = Long.valueOf(request.getParameter("id"));
    WatchModel wm = new WatchModel();
    WatchBean watch = null;
    try {
        watch = wm.doRetrieveByKey(List.of(id));
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    System.out.println(watch);
%>
<form action="${pageContext.request.contextPath}/admin/updateWatch" method="get">

    <input type="hidden" id="id" name="id" value="<%=watch.getId()%>"/><br/>
    <input type="hidden" id="reviews_avg" name="reviews_avg" value="<%=watch.getReviews_avg()%>">

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="<%=watch.getName()%>" required /><br/>

    <label for="brand">Brand:</label>
    <select id="brand" name="brand" required>
        <% for (BrandBean brand : brands) { %>
        <option value="<%=brand.getBusiness_name()%>" <%= brand.getBusiness_name().equals(watch.getBrand()) ? "selected" : "" %>><%=brand.getName()%></option>
        <% } %>
    </select><br/>
    <label for="description">Description:</label>
    <input type="text" id="description" name="description" value="<%=watch.getDescription()%>" required /><br/>

    <label for="price">Price:</label>
    <input type="number" step="0.01" id="price" name="price" value="<%=watch.getPrice()%>" required /><br/>

    <label for="material">Material:</label>
    <input type="text" id="material" name="material" value="<%=watch.getMaterial()%>" required /><br/>

    <label for="stock">Stock:</label>
    <input type="number" id="stock" name="stock" value="<%=watch.getStock()%>" required /><br/>

    <label for="dimension">Dimension (in mm):</label>
    <input type="number" step="0.01" id="dimension" name="dimension" value="<%=watch.getDimension()%>" required /><br/>

    <label for="IVA">IVA:</label>
    <input type="number" id="IVA" name="IVA" value="<%=watch.getIVA()%>" required /><br/>

    <label for="sex">Sex:</label>
    <select id="sex" name="sex" required>
        <option value="MAN" <% if(watch.getSex().equals("MAN")){%> selected <% } %>>MAN</option>
        <option value="WOMEN" <% if(watch.getSex().equals("WOMAN")){%> selected <% } %>>WOMEN</option>
        <option value="UNISEX" <% if(watch.getSex().equals("UNISEX")){%> selected <% } %>>UNISEX</option>
    </select><br/>

    <label for="visible">Visible:</label>
    <input type="checkbox" id="visible" name="visible" <% if(watch.getVisible()){%> checked <% } %> /><br/>

    <input type="submit" value="Update"/>
</form>

<%@include file="../footer.html"%>
</body>
</html>
