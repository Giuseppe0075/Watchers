<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: giuse
  Date: 28/02/2024
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
          integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
          crossorigin="anonymous"
          referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="../style/styleLoginSignup.css">

    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<%@include file="../navbar.jsp"%>
<video autoplay muted loop id="myVideo">
    <source src="../homepage/home.webm" type="video/mp4">
</video>
<div class="form-container">
    <div class="form-column side">
        <h2 class="form-title">Watchers</h2>
    </div>
    <div class="form-column">
        <h2 class="form-title">Login</h2>
        <p>Don't have an account? <a class="text-blue-700" href="${pageContext.request.contextPath}/user/signup.jsp">Sign-up here </a></p>
        <form id="loginForm" method="POST" action="${pageContext.request.contextPath}/login">
            <%-- Email --%>
            <div class="form-group">
                <label for="email">Email</label>
                <div class="input-container">
                    <input type="email" id="email" name="email" required pattern="^\w((\.)?\w+)*@\w+\.{1}[a-z]+(\.{1}\w+)*$"/>
                    <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                    <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                </div>
                <div class="error"></div>
            </div>
            <%-- Password --%>
            <div class="form-group">
                <label for="password">Password</label>
                <div class="input-container">
                    <input type="password" id="password" name="password" required />
                    <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                    <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                </div>
                <div class="error"></div>
            </div>
            <% if (request.getAttribute("loginError") != null) { %>
            <div class="error-message">Email/Password are wrong</div>
            <% } %>
            <button type="submit" class="btn-submit">Login</button>
        </form>
    </div>
</div>
<script src="../utils/formValidator.js"></script>
<%@include file="../footer.html"%> <!-- Footer -->
</body>
</html>
<style>
    #myVideo {
        position: absolute;
        top: 0;
        left: 0;
        width: 100vw;
        height: 100vh;
        object-fit: cover;
        z-index: -1;
    }
</style>
