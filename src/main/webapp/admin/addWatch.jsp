<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: giuse
  Date: 29/04/2024
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Add Watch</title>
    <link rel="stylesheet" href="../style/styleFooter.css">
</head>
<body>

    <%@include file="../navbar.jsp"%>
    <h2>Watch Registration</h2>
    <form action="${pageContext.request.contextPath}/admin/addWatch" method="get" enctype="multipart/form-data">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" placeholder="Enter watch name" required><br><br>

        <label for="brand">Brand:</label>
        <select id="brand" name="brand" required onchange="toggleBrandInput(this)">
            <option value="" disabled selected>Select a brand</option>
            <option value="brand1">Brand 1</option>
            <option value="brand2">Brand 2</option>
            <option value="brand3">Brand 3</option>
            <option value="new">Other (specify below)</option>
        </select>
        <input type="text" id="newBrandInput" name="newBrand" placeholder="Enter new brand" style="display:none;">

        <label for="description">Description:</label>
        <textarea id="description" name="description" placeholder="Enter description" required></textarea><br><br>

        <label for="reviews_avg">Average Reviews:</label>
        <input type="number" step="0.1" max="5" id="reviews_avg" name="reviews_avg" placeholder="Enter average reviews" required><br><br>

        <label for="price">Price:</label>
        <input type="number" step="0.25" id="price" name="price" placeholder="Enter price" required><br><br>

        <label for="material">Material:</label>
        <input type="text" id="material" name="material" placeholder="Enter material" required><br><br>

        <label for="stock">Stock:</label>
        <input type="number" id="stock" name="stock" placeholder="Enter stock quantity" required><br><br>

        <label for="dimension">Dimension:</label>
        <input type="text" id="dimension" name="dimension" placeholder="Enter dimensions" required><br><br>

        <label for="IVA">IVA:</label>
        <input type="number" step="0.1" id="IVA" name="IVA" placeholder="Enter IVA" required><br><br>

        <label for="sex">Sex:</label>
        <select id="sex" name="sex" required>
            <option value="MAN">MAN</option>
            <option value="WOMEN">WOMEN</option>
            <option value="UNISEX">UNISEX</option>
        </select><br/><br>

        <label for="visible">Visible:</label>
        <input type="checkbox" id="visible" name="visible"><br><br>

        <input type="submit" value="Add Watch">
    </form>
    <%@include file="../footer.html"%>

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
</body>
</html>
