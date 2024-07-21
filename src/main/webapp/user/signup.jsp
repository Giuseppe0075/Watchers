<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
          integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
          crossorigin="anonymous"
          referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="../style/styleLoginSignup.css">

    <title>Sign-Up</title>
</head>
<body>

<%
    //check if user is already logged in
    if (session.getAttribute("user") != null) {
        response.sendRedirect(request.getContextPath() + "/homepage.jsp");
    }
%>

<!-- Navbar -->
<%@include file="../navbar.jsp"%>

<div class="flex-container">
    <div class="flex-content">
        <!-- Contenuto della pagina -->

        <div class="form-container">
            <div class="form-column">
                <h2 class="form-title">Watchers</h2>
            </div>
            <div class="form-column">
                <h2 class="form-title">Sign-up</h2>
                <p>Already have an account? <a href="${pageContext.request.contextPath}/user/login.jsp">Login here</a> instead</p>
                <form id="registrationForm" method="POST" action="${pageContext.request.contextPath}/signup">

                    <%-- Name --%>
                    <div class="form-group">
                        <label for="name">Name</label>
                        <div class="input-container">
                            <input type="text" id="name" name="name" required maxlength="255"
                                   value="<%= request.getAttribute("name") != null ? request.getAttribute("name") : "" %>" />
                            <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                            <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                        </div>
                        <div class="error"></div>
                    </div>

                    <%-- Surname --%>
                    <div class="form-group">
                        <label for="surname">Surname</label>
                        <div class="input-container">
                            <input type="text" id="surname" name="surname" required maxlength="255"
                                   value="<%= request.getAttribute("surname") != null ? request.getAttribute("surname") : "" %>" />
                            <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                            <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                        </div>
                        <div class="error"></div>
                    </div>

                    <%-- Birthday --%>
                    <div class="form-group">
                        <label for="birthday">Birthday</label>
                        <div class="input-container">
                            <input type="date" id="birthday" name="birthday" required
                                   value="<%= request.getAttribute("birthday") != null ? request.getAttribute("birthday") : "" %>" />
                            <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                            <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                        </div>
                        <div class="error"></div>
                    </div>

                    <%-- Road --%>
                    <div class="form-group">
                        <label for="road">Road</label>
                        <div class="input-container">
                            <input type="text" id="road" name="road" required maxlength="255"
                                   value="<%= request.getAttribute("road") != null ? request.getAttribute("road") : "" %>" />
                            <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                            <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                        </div>
                        <div class="error"></div>
                    </div>

                    <%-- Civic Number --%>
                    <div class="form-group">
                        <label for="civic_number">Civic Number</label>
                        <div class="input-container">
                            <input type="text" id="civic_number" name="civic_number" required maxlength="255"
                                   value="<%= request.getAttribute("civic_number") != null ? request.getAttribute("civic_number") : "" %>" />
                            <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                            <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                        </div>
                        <div class="error"></div>
                    </div>

                    <%-- City --%>
                    <div class="form-group">
                        <label for="city">City</label>
                        <div class="input-container">
                            <input type="text" id="city" name="city" required maxlength="255"
                                   value="<%= request.getAttribute("city") != null ? request.getAttribute("city") : "" %>" />
                            <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                            <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                        </div>
                        <div class="error"></div>
                    </div>

                    <%-- CAP --%>
                    <div class="form-group">
                        <label for="cap">CAP</label>
                        <div class="input-container">
                            <input type="text" id="cap" name="cap" required minlength="5" maxlength="5"
                                   value="<%= request.getAttribute("cap") != null ? request.getAttribute("cap") : "" %>" />
                            <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                            <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                        </div>
                        <div class="error"></div>
                    </div>

                    <%-- Email --%>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <div class="input-container">
                            <input type="email" id="email" name="email" required
                                   pattern="^\w((\.)?\w+)*@\w+\.{1}[a-z]+(\.{1}\w+)*$" />
                            <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                            <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                        </div>
                        <div class="error"></div>
                    </div>

                    <%-- Password --%>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <div class="input-container">
                            <input type="password" id="password" name="password" required minlength="8"
                                   pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&.,%])[A-Za-z\d@$!%*#?&.,%]{8,}$" />
                            <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                            <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                        </div>
                        <div class="error"></div>
                    </div>

                    <%-- Confirm Password --%>
                    <div class="form-group">
                        <label for="confirmPassword">Confirm Password</label>
                        <div class="input-container">
                            <input type="password" id="confirmPassword" name="confirmPassword" required match="password" />
                            <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                            <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                        </div>
                        <div class="error"></div>
                    </div>
                    <% if(request.getAttribute("registrationError") != null) { %>
                    <p class="error-message"><%= request.getAttribute("registrationError") %>. Maybe <a href="${pageContext.request.contextPath}/user/login.jsp">Login</a>?</p>
                    <% } %>

                    <button type="submit" class="btn-submit">Sign-up</button>
                </form>
            </div>
        </div>

    </div>


<script src="../utils/formValidator.js"></script>
<%@include file="../footer.html"%> <!-- Footer -->
</body>
</html>
