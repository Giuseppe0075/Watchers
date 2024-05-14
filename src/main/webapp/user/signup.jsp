<%--
  Created by IntelliJ IDEA.
  User: giuse
  Date: 14/05/2024
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign-Up</title>
</head>
<body>
    <div class="container">

        <h2> Sign-Up</h2>
        <form action="${pageContext.request.contextPath}/signup" method="post">
            <!-- Name -->
            <label for="name">Name
                <input type="text" id="name" name="name" placeholder="Insert your name...">
            </label>

            <!-- Surname -->
            <label for="surname">Surname
                <input type="text" id="surname" name="surname" placeholder="Insert your surname...">
            </label>

            <!-- Birthday -->
            <label for="birthday">Birthday
                <input type="date" id="birthday" name="birthday">
            </label>

            <!-- Address -->
            <label for="city">City
                <input type="text" id="city" name="city" placeholder="Insert your city...">
            </label>
            <label for="road">Road
                <input type="text" id="road" name="road" placeholder="Insert your address...">
            </label>
            <label for="civicNumber">Civic number
                <input type="text" id="civicNumber" name="civicNumber" placeholder="Insert your civic number...">
            </label>
            <label for="CAP">CAP
                <input type="text" id="CAP" name="CAP" placeholder="Insert your CAP...">
            </label>

            <!-- Email -->
            <label for="email">Email
                <input type="email" id="email" name="email" placeholder="Insert your email...">
            </label>
            <label for="repeatEmail">Repeat email>
                <input type="email" id="repeatEmail" name="repeatEmail" placeholder="Repeat your email...">
            </label>

            <!-- Password -->
            <label for="password">Password
                <input type="password" id="password" name="password" placeholder="Insert your password...">
            </label>
            <label for="repeatPassword">Repeat password
                <input type="password" id="repeatPassword" name="repeatPassword" placeholder="Repeat your password...">
            </label>

            <input type="submit" value="Sign-up">
        </form>

    </div>
</body>
</html>
