<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.Collection" %>
<%@ page import="storage.Models.WatchModel" %>
<%@ page import="utils.Security" %>
<%@ page import="java.util.List" %>
<%@ page import="storage.Beans.WatchBean" %>
<%@ page import="storage.Models.ImageModel" %>
<%@ page import="storage.Beans.ImageBean" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Set" %>
<%@ page import="storage.Models.BrandModel" %>
<%@ page import="storage.Beans.BrandBean" %>
<html>
<head>
    <title>Catalogue</title>
    <link rel="stylesheet" href="../style/styleCatalogue.css" type="text/css">
    <link rel="stylesheet" href="../style/styleHomepage.css">

</head>
<body>
<%!
    Collection<WatchBean> watchList;
    WatchModel watchModel = new WatchModel();
    ImageModel imageModel= new ImageModel();
    BrandModel brandModel = new BrandModel();
    Set<String> materials = new HashSet<>();
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
                <input type="checkbox" id="female" name="gender" value="WOMAN">Woman<br>
                <input type="checkbox" id="unisex" name="gender" value="UNISEX">Unisex<br>
            </label>
        </div>

        <!-- Filter by brand -->
        <div class="filterGroup">
            <h3>Brand:</h3>
            <label>
                <% for(BrandBean brand : brands) { %>
                    <input type="checkbox" value="<%= brand.getBusiness_name() %>"><%= brand.getBusiness_name() %><br>
                <% } %>
            </label>
        </div>

        <!-- Filter by size -->
        <div class="filterGroup">
            <h3>Size (mm):</h3>
            <label>
                <input type="number" id="sizeMin" name="sizeMin" min="0" style="width: 50px; text-align: center;">
                <input type="number" id="sizeMax" name="sizeMax" min="0" style="width: 50px; text-align: center;">
            </label>
        </div>

        <!-- Filter by material -->
        <div class="filterGroup">
            <h3>Material:</h3>
            <label>
                <% for(String material : materials) { %>
                    <input type="checkbox" value="<%= material %>"><%= material %><br>
                <% } %>
            </label>
        </div>

        <!-- Filter by price -->
        <div class="filterGroup">
            <h3>Price:</h3>
            <label>
                <input type="number" id="priceMin" name="priceMin" min="0" style="width: 50px; text-align: center;">
                <input type="number" id="priceMax" name="priceMax" min="0" style="width: 50px; text-align: center;"><br>
            </label>
        </div>
    </form>
</div>

<%@include file="../footer.html"%> <!-- Footer -->
<script src="catalogue.js"></script>
</body>
</html>
