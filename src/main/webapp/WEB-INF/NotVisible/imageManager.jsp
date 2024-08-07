<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.Beans.ImageBean" %>
<%@ page import="Model.Models.ImageModel" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<%--
<link rel="stylesheet" href="../style/styleImageManager.css"> <!-- Nuovo file CSS per lo stile delle immagini -->
--%>

<%
    ImageModel imageModel = new ImageModel();
    Long watchId = Long.valueOf(request.getParameter("id"));
    Collection<ImageBean> images = null;
    try {
        images = imageModel.doRetrieveByCond("WHERE watch = ?", List.of(watchId));
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<div class="container">

    <!-- Upload Form -->
    <div class="form-group">
        <h2>Manage Product Images</h2>
        <h3>Upload Image</h3>
        <form action="${pageContext.request.contextPath}/admin/uploadImage" method="post" enctype="multipart/form-data">
            <input type="hidden" name="watch" value="<%= watchId %>">
            <label for="imageUpload">Select Image:</label>
            <input type="file" id="imageUpload" name="image" accept="image/*" required>
            <input type="submit" value="Upload Image">
        </form>
    </div>

    <!-- Image List -->
    <div id="imageList">
        <h3>Current Images</h3>
        <% for (ImageBean image : images) { %>
        <div class="image-item">
            <div class="image-container">
                <img src="${pageContext.request.contextPath}/getImage?id=<%=image.getId()%>&watch=<%=watchId%>" alt="${image.name}" class="product-image">
                <form action="${pageContext.request.contextPath}/admin/deleteImage" method="post" class="delete-form">
                    <input type="hidden" name="image" value="<%=image.getId()%>">
                    <input type="hidden" name="watch" value="<%=watchId%>">
                    <button type="submit" class="delete-btn">
                        <i class="fas fa-trash"></i>
                    </button>
                </form>
            </div>
        </div>
        <%}%>
    </div>
</div>

