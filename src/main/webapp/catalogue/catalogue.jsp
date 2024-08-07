<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.Collection" %>
<%@ page import="Model.Models.BrandModel" %>
<%@ page import="Model.Beans.BrandBean" %>
<html>
<head>
    <title>Catalogue</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/styleCatalogue.css" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="stars.js"></script>
    <style>
        button{
            border: none;
        }
    </style>
</head>
<body>
<%!
    BrandModel brandModel = new BrandModel();
    Collection<BrandBean> brands;
%>
<%
    try{
        brands = brandModel.doRetrieveAll();
    }catch (Exception e){
        throw new RuntimeException(e);
    }
%>
<%@include file="../navbar.jsp"%> <!-- Navabar -->

<div class="flex-container">
    <div class="flex-content">
        <!-- Contenuto della pagina -->

        <div class="catalogue-container">


            <div id="filters" class="filters-bar hidden">
                <button id="closeFiltersBtn">X</button>
                <form id="filterForm">
                    <!-- Filter by gender -->
                    <div class="filterGroup">
                        <h3>Gender</h3>

                        <div class="filterOption">
                            <label>
                                <input type="checkbox" id="male" name="gender" value="MAN">Man<br>
                            </label>
                            <label>
                                <input type="checkbox" id="female" name="gender" value="WOMEN">Woman<br>
                            </label>
                            <label>
                                <input type="checkbox" id="unisex" name="gender" value="UNISEX">Unisex<br>
                            </label>
                        </div>

                    </div>

                    <!-- Filter by brand -->
                    <div class="filterGroup">
                        <h3>Brand:</h3>

                        <div class="filterOption">
                            <% for(BrandBean brand : brands) { %>
                            <label>
                                <input type="checkbox" name="brand" value="<%= brand.getBusiness_name() %>"><%= brand.getBusiness_name() %><br>
                            </label>
                            <% } %>
                        </div>

                    </div>

                    <!-- Filter by size -->
                    <div class="filterGroup">
                        <h3>Size (mm):</h3>

                        <div class="filterOption">
                            <label>
                                <input type="number" id="sizeMin" name="sizeMin" placeholder="min" min="0" style="width: 50px; text-align: center;">
                            </label>
                            <label>
                                <input type="number" id="sizeMax" name="sizeMax" placeholder="max" min="0" style="width: 50px; text-align: center;">
                            </label>
                        </div>

                    </div>

                    <!-- Filter by material -->
                    <div class="filterGroup">
                        <h3>Material:</h3>

                        <div class="filterOption" id="filter-materials">

                        </div>

                    </div>

                    <!-- Filter by price -->
                    <div class="filterGroup">
                        <h3>Price:</h3>

                        <div class="filterOption">
                            <label>
                                <input type="number" id="priceMin" name="priceMin" placeholder="min" min="0" style="width: 50px; text-align: center;">
                            </label>
                            <label>
                                <input type="number" id="priceMax" name="priceMax" placeholder="max" min="0" style="width: 50px; text-align: center;"><br>
                            </label>
                        </div>

                    </div>

                    <!-- Filter by stars -->
                    <div class="filterGroup">
                        <h3>Stars:</h3>

                        <div class="filterOption">
                            <span id="starsMinValue"></span>
                            <label>
                                <input type="range" id="starsMin" name="starsMin"  min=0 max=5 step=0.5 value=0 onchange="updateStars(this.value)">
                            </label>
                        </div>

                    </div>

                </form>
            </div>
            <button id="toggleFilters" class="toggle-filters">Filters</button>
            <div id="catalogue" class="watches-container">

            </div>
        </div>


    </div>

<script src="catalogue.js"></script>


<%@include file="../footer.html"%> <!-- Footer -->


</body>
</html>
