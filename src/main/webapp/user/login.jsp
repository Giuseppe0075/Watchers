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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
          integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
          crossorigin="anonymous"
          referrerpolicy="no-referrer" />

    <title>Login</title>

    <style>
        .background {
            z-index: -1;
            background-size: cover;
            background-color: #dddddd;
        }

        .side-column {
            background-size: cover;
            background-position: 50%;
            background-color: #eeeeee;
        }

    </style>
    <link rel="stylesheet" href="../style/styleHomepage.css">
</head>
<body>
        <%@include file="../navbar.jsp"%>
        <div class="h-full w-full fixed top-0 background"></div>
        <div class="w-full px-8 mt-32 flex shadow-2xl">
            <div class="w-1/2 p-12 side-column">
                <h2 class="text-black text-8xl mt-16 font-bold">Watchers</h2>

                <p class="text-black-400 my-8"> Join us in the world of luxury. We have a variety of watches from every luxury brand and from every part of the world.</p>
                <p class ="text-black my-8 font-bold"> Login with social media</p>
                <div class="flex gap-8">
                    <div class="flex items-center gap-4 text-sm bg-blue-900 text-white px-8 py-2 rounded-full">
                        <i class="fa-brands fa-facebook-f"></i>
                        <span>Facebook</span>
                    </div>
                    <div class="flex items-center gap-4 text-sm bg-sky-400 text-white px-8 py-2 rounded-full">
                        <i class="fa-brands fa-twitter"></i>
                        <span>Twitter</span>
                    </div>
                </div>
            </div>
            <div class="w-1/2 p-16 bg-white">
                <h2 class="text-4xl font-bold">Login</h2>
                <p class="my-8">Don't have an account? <a class="text-blue-700" href="${pageContext.request.contextPath}/user/signup.jsp">Sign-up here </a> </p>
                <form id="loginForm" method="POST" action="${pageContext.request.contextPath}/login">
                    <%--Email--%>
                    <div class="formGroup flex flex-col my-4">
                        <label for="email">Email</label>
                        <div class="flex items-center">
                            <input type="email" id="email" name="email" class="w-full border-b-2 border-grey-200 outline-none py-2"
                                   required
                            />
                            <span class="error-icon hidden -ml-6 text-red-700">
                        <i class="fa-solid fa-circle-exclamation"></i>
                        </span>
                            <span class="success-icon hidden -ml-6 text-green-700">
                            <i class="fa-solid fa-circle-check"></i>
                        </span>
                        </div>
                        <div class="error text-red-700 py-2"></div>
                    </div>

                    <%--Password--%>
                    <div class="formGroup flex flex-col my-4">
                        <label for="password">Password</label>
                        <div class="flex items-center">
                            <input type="password" id="password" name="password" class="w-full border-b-2 border-grey-200 outline-none py-2"
                                   required
                            />
                            <span class="error-icon hidden -ml-6 text-red-700">
                            <i class="fa-solid fa-circle-exclamation"></i>
                        </span>
                            <span class="success-icon hidden -ml-6 text-green-700">
                            <i class="fa-solid fa-circle-check"></i>
                        </span>
                        </div>
                        <div class="error text-red-700 py-2"></div>
                    </div>
                    <% if (request.getAttribute("loginError") != null) { %>
                        <div class="errorMessage text-red-700 py-2">Email/Password are wrong</div>
                    <% } %>

                    <button type="submit" class="px-8 py-2 mt-4 bg-blue-400 w-full rounded-full text-white shadow-large">Login</button>
                </form>
            </div>
        </div>
        <script src="../utils/formValidator.js"></script>
</body>
</html>
