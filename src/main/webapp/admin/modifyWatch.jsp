<%@ page import="Model.Beans.WatchBean" %>
<%@ page import="Model.Models.WatchModel" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Models.BrandModel" %>
<%@ page import="Model.Beans.BrandBean" %><%--
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
    <link rel="stylesheet" href="../style/styleModifyWatch.css">
    <link rel="stylesheet" href="../style/styleImageManager.css">

    <style>
        /* Container for form and image manager */
        .container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: space-between;
        }

        .form-container,
        .image-manager-container {
            flex: 1 1 45%;
            box-sizing: border-box;
        }

        @media (max-width: 768px) {
            .form-container,
            .image-manager-container {
                flex: 1 1 100%;
            }
        }

        .image-manager-container {
            flex: 1 1 45%;
            box-sizing: border-box;
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
    </style>
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
<div class="container">
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

    <div class="image-manager-container">
        <jsp:include page="/WEB-INF/NotVisible/imageManager.jsp">
            <jsp:param name="watchId" value="${id}" />
        </jsp:include>
    </div>
</div>

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
</html>
