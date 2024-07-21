<%@ page import="Model.Models.BrandModel" %>
<%@ page import="Model.Beans.BrandBean" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8"%>
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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../style/styleAddWatch.css">
</head>
<body>

    <%@include file="../navbar.jsp"%>

    <div class="flex-container">
        <div class="flex-content">
            <!-- Contenuto della pagina -->

            <%
                BrandModel brandModel = new BrandModel();
                Collection<BrandBean> brands;
                try {
                    brands = brandModel.doRetrieveAll();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            %>

            <div class="form-container">
                <h2>Watch Registration</h2>
                <form id="new-watch-form" action="${pageContext.request.contextPath}/admin/addWatch" method="get" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <div class="input-container">
                            <input type="text" id="name" name="name" required>
                            <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                            <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                        </div>
                        <div class="error"></div>
                    </div>

                    <div class="form-group">
                        <label for="brand">Brand:</label>
                        <div class="input-container">
                            <select id="brand" name="brand" required onchange="toggleBrandInput(this)">
                                <option value="" disabled selected>Select a brand</option>
                                <% for (BrandBean brand : brands) { %>
                                <option value="<%= brand.getName() %>"><%= brand.getName() %></option>
                                <% } %>
                                <option value="new">Other (specify below)</option>
                            </select>
                            <label for="newBrandInput" style="display:none;">New Brand:</label>
                            <input type="text" id="newBrandInput" name="newBrand" style="display:none;">
                            <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                            <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                        </div>
                        <div class="error"></div>
                    </div>

                    <div class="form-group">
                        <label for="description">Description:</label>
                        <div class="input-container">
                            <input type="text" id="description" name="description" required />
                            <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                            <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                        </div>
                        <div class="error"></div>
                    </div>

                    <div class="form-group">
                        <label for="price">Price:</label>
                        <div class="input-container">
                            <input type="number" step="0.25" id="price" name="price"  required>
                            <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                            <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                        </div>
                        <div class="error"></div>
                    </div>

                    <div class="form-group">
                        <label for="material">Material:</label>
                        <div class="input-container">
                            <input type="text" id="material" name="material" required>
                            <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                            <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                        </div>
                        <div class="error"></div>
                    </div>

                    <div class="form-group">
                        <label for="stock">Stock:</label>
                        <div class="input-container">
                            <input type="number" id="stock" name="stock" required>
                            <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                            <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                        </div>
                        <div class="error"></div>
                    </div>

                    <div class="form-group">
                        <label for="dimension">Dimension:</label>
                        <div class="input-container">
                            <input type="text" id="dimension" name="dimension" required>
                            <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                            <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                        </div>
                        <div class="error"></div>
                    </div>

                    <div class="form-group">
                        <label for="IVA">IVA:</label>
                        <div class="input-container">
                            <input type="number" step="0.1" id="IVA" name="IVA" required>
                            <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                            <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                        </div>
                        <div class="error"></div>
                    </div>

                    <div class="form-group">
                        <label for="sex">Sex:</label>
                        <div class="input-container">
                            <select id="sex" name="sex" required>
                                <option value="MAN">MAN</option>
                                <option value="WOMEN">WOMEN</option>
                                <option value="UNISEX">UNISEX</option>
                            </select>
                            <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                            <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                        </div>
                        <div class="error"></div>
                    </div>

                    <div class="form-group">
                        <label for="visible">Visible:</label>
                        <div class="input-container">
                            <input type="checkbox" id="visible" name="visible">
                            <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                            <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                        </div>
                        <div class="error"></div>
                    </div>
                    <button type="submit" class="btn-submit">Add Watch</button>
                </form>
            </div>

        </div>



    <%@include file="../footer.html"%>
    <script>
    function toggleBrandInput(select) {
        let brandInput = document.getElementById('newBrandInput');
        if (select.value === 'new') {
            brandInput.style.display = 'inline';
            brandInput.required = true;
        } else {
            brandInput.style.display = 'none';
            brandInput.required = false;
        }
    }
</script>
<script src="../utils/formValidator.js"></script>
</body>
</html>
