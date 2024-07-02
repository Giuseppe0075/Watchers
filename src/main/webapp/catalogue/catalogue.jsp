<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.Collection" %>
<%@ page import="utils.Security" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Set" %>
<%@ page import="storage.Models.BrandModel" %>
<%@ page import="storage.Beans.BrandBean" %>
<html>
<head>
    <title>Catalogue</title>
    <link rel="stylesheet" href="../style/styleCatalogue.css" type="text/css">
    <link rel="stylesheet" href="../style/styleHomepage.css">
    <link rel="stylesheet" href="../style/styleFooter.css">
    <script src="stars.js"></script>
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
    boolean admin = session != null && session.getAttribute("admin") != null && session.getAttribute("admin").equals(true);
    String csrfToken= Security.getCSRFToken();
    request.getSession().setAttribute("csrfToken", csrfToken);
%>


<%@include file="../navbar.jsp"%> <!-- Navabar -->

<main>
    <section id="catalogue">

    </section>
</main>

<div id="filters">
    <form id="filterForm">
        <!-- Filter by gender -->
        <div class="filterGroup">
            <h3>Gender</h3>
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

        <!-- Filter by brand -->
        <div class="filterGroup">
            <h3>Brand:</h3>

            <% for(BrandBean brand : brands) { %>
            <label>
                <input type="checkbox" name="brand" value="<%= brand.getBusiness_name() %>"><%= brand.getBusiness_name() %><br>
            </label>
            <% } %>
        </div>

        <!-- Filter by size -->
        <div class="filterGroup">
            <h3>Size (mm):</h3>
            <label>
                <input type="number" id="sizeMin" name="sizeMin" placeholder="min" min="0" style="width: 50px; text-align: center;">
            </label>
            <label>
                <input type="number" id="sizeMax" name="sizeMax" placeholder="max" min="0" style="width: 50px; text-align: center;">
            </label>
        </div>

        <!-- Filter by material -->
        <div class="filterGroup">
            <h3>Material:</h3>
                <label>
                    <!--<input type="checkbox" name="material" value=""><br>-->
                </label>
        </div>

        <!-- Filter by price -->
        <div class="filterGroup">
            <h3>Price:</h3>
            <label>
                <input type="number" id="priceMin" name="priceMin" placeholder="min" min="0" style="width: 50px; text-align: center;">
            </label>
            <label>
                <input type="number" id="priceMax" name="priceMax" placeholder="max" min="0" style="width: 50px; text-align: center;"><br>
            </label>
        </div>

        <!-- Filter by stars -->
        <div class="filterGroup">
            <h3>Stars:<span id="starsMinValue"></span></h3>
            <label>
                <input type="range" id="starsMin" name="starsMin"  min=0 max=5 step=0.5 value=0 onchange="updateStars(this.value)">
            </label>
        </div>

    </form>
</div>

<%@include file="../footer.html"%> <!-- Footer -->
<script src="catalogue.js"></script>
</body>
</html>
