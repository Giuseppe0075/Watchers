<%@ page import="java.time.LocalDate" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="user.SignupDataForm" %>
<%@ page import="utils.FieldDescriptor" %><%--
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
        <!-- Navbar -->
        <%@include file="../navbar.jsp"%>
        <h2> Sign-Up</h2><br><br>
        <form action="${pageContext.request.contextPath}/signup" method="post">
            <%
                for(Field field: SignupDataForm.class.getDeclaredFields()){
                    FieldDescriptor descriptor = field.getAnnotation(FieldDescriptor.class);
                    String placeHolder = descriptor != null ? descriptor.description() : "";
                %>

                    <label for=name=><%=field.getName()%></label>
                    <input type="text" id=<%=field.getName()%> name=<%=field.getName()%> placeholder=<%=placeHolder%>><br>

                <%}%>

            <input type="submit" value="Sign-up">
        </form>

    </div>
</body>
</html>
