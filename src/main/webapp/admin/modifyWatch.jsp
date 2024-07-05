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
    <link rel="stylesheet" href="../style/styleFooter.css">
    <link rel="stylesheet" href="../style/styleForm.css"> <!-- Nuovo file CSS per lo stile del form -->
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
<div class="form-container">
    <form action="${pageContext.request.contextPath}/admin/updateWatch" method="post">

        <input type="hidden" id="id" name="id" value="<%=watch.getId()%>"/><br/>
        <input type="hidden" id="reviews_avg" name="reviews_avg" value="<%=watch.getReviews_avg()%>">

        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="<%=watch.getName()%>" required />
        </div>

        <div class="form-group">
            <label for="brand">Brand:</label>
            <select id="brand" name="brand" required onchange="toggleBrandInput(this)">
                <option value="" disabled selected>Select a brand</option>
                <% for (BrandBean brand : brands) { %>
                <option value="<%=brand.getBusiness_name()%>" <%= brand.getBusiness_name().equals(watch.getBrand()) ? "selected" : "" %>><%=brand.getName()%></option>
                <% } %>
                <option value="new">Other (specify below)</option>
            </select>
            <input type="text" id="newBrandInput" name="newBrand" placeholder="Enter new brand" style="display:none;">
        </div>

        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" id="description" name="description" value="<%=watch.getDescription()%>" required />
        </div>

        <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" step="0.01" id="price" name="price" value="<%=watch.getPrice()%>" required />
        </div>

        <div class="form-group">
            <label for="material">Material:</label>
            <input type="text" id="material" name="material" value="<%=watch.getMaterial()%>" required />
        </div>

        <div class="form-group">
            <label for="stock">Stock:</label>
            <input type="number" id="stock" name="stock" value="<%=watch.getStock()%>" required />
        </div>

        <div class="form-group">
            <label for="dimension">Dimension (in mm):</label>
            <input type="number" step="0.01" id="dimension" name="dimension" value="<%=watch.getDimension()%>" required />
        </div>

        <div class="form-group">
            <label for="IVA">IVA:</label>
            <input type="number" id="IVA" name="IVA" value="<%=watch.getIVA()%>" required />
        </div>

        <div class="form-group">
            <label for="sex">Sex:</label>
            <select id="sex" name="sex" required>
                <option value="MAN" <% if(watch.getSex().equals("MAN")){%> selected <% } %>>MAN</option>
                <option value="WOMEN" <% if(watch.getSex().equals("WOMAN")){%> selected <% } %>>WOMEN</option>
                <option value="UNISEX" <% if(watch.getSex().equals("UNISEX")){%> selected <% } %>>UNISEX</option>
            </select>
        </div>

        <div class="form-group">
            <label for="visible">Visible:</label>
            <input type="checkbox" id="visible" name="visible" <% if(watch.getVisible()){%> checked <% } %> />
        </div>

        <button type="submit">Update</button>
    </form>
</div>

<jsp:include page="/WEB-INF/NotVisible/imageManager.jsp">
    <jsp:param name="watchId" value="${id}" />
</jsp:include>

<script>
    function toggleBrandInput(select) {
        var brandInput = document.getElementById('newBrandInput');
        if (select.value === 'new') {
            brandInput.style.display = 'inline';
            brandInput.required = true;
        } else {
            brandInput.style.display = 'none';
            brandInput.required = false;
        }
    }
</script>

<%@include file="../footer.html"%> <!-- Footer -->
</body>
<style>
    /* Stile per il container del form */
    .form-container {
        background-color: #f9f9f9;
        padding: 20px;
        margin: 20px auto;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        max-width: 600px;
    }

    .form-group {
        margin-bottom: 15px;
    }

    .form-group label {
        display: block;
        font-weight: bold;
        margin-bottom: 5px;
    }

    .form-group input, .form-group select {
        width: 100%;
        padding: 8px;
        border: 1px solid #ddd;
        border-radius: 5px;
    }

    button[type="submit"] {
        background-color: #4CAF50;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        width: 100%;
        font-size: 16px;
    }

    button[type="submit"]:hover {
        background-color: #45a049;
    }

    /* Stile per le immagini dei prodotti */
    .product-image-container {
        position: relative;
        display: inline-block;
        width: 150px;
        height: 150px;
        margin: 10px;
    }

    .product-image {
        width: 100%;
        height: 100%;
        object-fit: cover;
        border-radius: 5px;
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
    }

    .delete-button {
        display: none;
        position: absolute;
        top: 5px;
        right: 5px;
        background-color: rgba(255, 0, 0, 0.7);
        color: white;
        border: none;
        border-radius: 50%;
        padding: 5px 10px;
        cursor: pointer;
    }

    .product-image-container:hover .delete-button {
        display: block;
    }

</style>
</html>
