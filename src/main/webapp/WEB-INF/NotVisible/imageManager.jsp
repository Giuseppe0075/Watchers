<%--
  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 16/06/2024
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="storage.Beans.ImageBean" %>
<%@ page import="storage.Models.ImageModel" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

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
    <h2>Manage Product Images</h2>

    <!-- Upload Form -->
    <div class="form-group">
        <h3>Upload Image</h3>
        <form id="uploadForm">
            <input type="hidden" id="watchIdUpload" name="watchId" value="<%= watchId %>">
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
                <img src="${pageContext.request.contextPath}/getImage?id=<%=image.getId()%>&watch=<%=watchId%>" alt="${image.name}">
                <span class="delete-btn" data-id="${image.id}" data-watch="${watchId}">
                    <i class="fas fa-trash"></i>
                </span>
            </div>
        <%}%>
    </div>
</div>

<script>
    $(document).ready(function () {
        // Function to load images
        function loadImages() {
            $.ajax({
                url: '/getImages?watchId=' + $('#watchIdUpload').val(),
                method: 'GET',
                success: function (data) {
                    $('#imageList').empty();
                    $.each(data, function (index, image) {
                        $('#imageList').append(
                            '<div class="image-item">' +
                            '<img src="/images/' + image.id + '" alt="' + image.name + '">' +
                            '<span class="delete-btn" data-id="' + image.id + '" data-watch="' + image.watch + '"><i class="fas fa-trash"></i></span>' +
                            '</div>'
                        );
                    });
                }
            });
        }

        // Initial load
        loadImages();

        // Upload Form Submission
        $('#uploadForm').submit(function (e) {
            e.preventDefault();
            var formData = new FormData(this);
            $.ajax({
                url: '/uploadImage',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function () {
                    alert('Image uploaded successfully');
                    loadImages();
                },
                error: function (error) {
                    alert('Image upload failed'+error.responseText);
                }
            });
        });

        // Delete Button Click
        $('#imageList').on('click', '.delete-btn', function () {
            var imageId = $(this).data('id');
            var watchId = $(this).data('watch');
            if (confirm('Are you sure you want to delete this image?')) {
                $.ajax({
                    url: '/deleteImage',
                    type: 'POST',
                    data: { image: imageId, watch: watchId },
                    success: function () {
                        alert('Image deleted successfully');
                        loadImages();
                    },
                    error: function () {
                        alert('Image deletion failed');
                    }
                });
            }
        });
    });
</script>

