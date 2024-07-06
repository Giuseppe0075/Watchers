<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="storage.Beans.ImageBean" %>
<%@ page import="storage.Models.ImageModel" %>
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

<style>
    .container {
        display: flex;
        flex-wrap: wrap;
        padding: 10px;
        gap: 20px;
        justify-content: space-between;
    }

    .image-manager-container {
        flex: 1 1 45%;
        box-sizing: border-box;
        background-color: #f9f9f9;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    .form-group {
        margin-bottom: 20px;
    }

    .form-group label {
        display: block;
        margin-bottom: 10px;
        font-weight: bold;
    }

    .form-group input[type="file"] {
        display: block;
        margin-bottom: 10px;
    }

    .form-group input[type="submit"] {
        background-color: #5d9ea8;
        color: white;
        border: none;
        padding: 10px 20px;
        cursor: pointer;
        border-radius: 5px;
    }

    .form-group input[type="submit"]:hover {
        background-color: #498e99;
    }

    #imageList {
        margin-top: 20px;
    }

    .image-item {
        display: inline-block;
        margin: 10px;
        position: relative;
    }

    .image-container {
        position: relative;
        width: 150px;
        height: 150px;
    }

    .product-image {
        width: 100%;
        height: 100%;
        object-fit: cover;
        border-radius: 5px;
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
    }

    .delete-form {
        position: absolute;
        top: 5px;
        right: 5px;
        display: none;
    }

    .image-item:hover .delete-form {
        display: block;
    }

    .delete-btn {
        background-color: rgba(255, 0, 0, 0.7);
        color: white;
        border: none;
        border-radius: 50%;
        padding: 5px 10px;
        cursor: pointer;
    }

    /* Ensure form and image manager stack vertically on small screens */
    @media (max-width: 768px) {
        .form-container,
        .image-manager-container {
            flex: 1 1 100%;
        }
    }

</style>
