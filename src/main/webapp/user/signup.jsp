<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign-Up</title>
    <script src="signupFormChecker.js"></script>
</head>
<body>
    <div class="container">
        <!-- Navbar -->
        <%@include file="../navbar.jsp"%>
        <h2> Sign-Up</h2><br><br>
        <form id="form" name="form">

            <label for="name">
                Name
                <input type="text" id="name" name="name" placeholder="insert your name..." required>
                <span id="nameError" class="error"></span>
                <br>
            </label>

            <label for="surname">
                Surname
                <input type="text" id="surname" name="surname" placeholder="insert your surname..." required>
                <span id="surnameError" class="error"></span>
                <br>
            </label>

            <label for="birthday">
                Birthday
                <input type="date" id="birthday" name="birthday" required>
                <span id="birthdayError" class="error"></span>
                <br>
            </label>

            <label for="road">
                Road
                <input type="text" id="road" name="road" placeholder="insert your road..." required>
                <span id="roadError" class="error"></span>
                <br>
            </label>

            <label for="civic_number">
                Civic number
                <input type="text" id="civic_number" name="civic_number" placeholder="insert your civic number..." required>
                <span id="civic_numberError" class="error"></span>
                <br>
            </label>

            <label for="city">
                City
                <input type="text" id="city" name="city" placeholder="insert your city..." required>
                <span id="cityError" class="error"></span>
                <br>
            </label>

            <label for="CAP">
                CAP
                <input type="text" id="CAP" name="CAP" placeholder="insert your CAP..." required>
                <span id="CAPError" class="error"></span>
                <br>
            </label>

            <label for="email">
                Email
                <input type="email" id="email" name="email" placeholder="insert your email..." required>
                <span id="emailError" class="error"></span>
                <br>
            </label>

            <label for="repeatedEmail">
                Repeat Email
                <input type="email" id="repeatedEmail" name="repeatedEmail" placeholder="repeat your email..." required>
                <span id="repeatedEmailError" class="error"></span>
                <br>
            </label>

            <label for="password">
                Password
                <input type="password" id="password" name="password" placeholder="insert your password..." required>
                <span id="passwordError" class="error"></span>
                <br>
            </label>

            <label for="repeatedPassword">
                Repeat Password
                <input type="password" id="repeatedPassword" name="repeatedPassword" placeholder="repeat your password..." required>
                <span id="repeatedPasswordError" class="error"></span>
                <br>
            </label>
            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>
