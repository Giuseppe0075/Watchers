<%@ page import="java.time.LocalDate" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="user.SignupDataForm" %>
<%@ page import="utils.FieldDescriptor" %>
<%@ page import="utils.Security" %><%--
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
        <%!String csrfToken = Security.getCSRFToken();%>
        <%session.setAttribute("CSRF-Token", csrfToken);%>
        <h2> Sign-Up</h2><br><br>
        <form action="${pageContext.request.contextPath}/signup" method="post">

            <input type="hidden" id="CSRF-Token" name="CSRF-Token" value=<%=csrfToken%>>
            <%
                for(Field field: SignupDataForm.class.getFields()){
                    FieldDescriptor descriptor = field.getAnnotation(FieldDescriptor.class);
                    String placeHolder = descriptor != null ? descriptor.description() : "";
                    String type = descriptor != null ? descriptor.type() : "text";
                %>


            <label> <%=field.getName()%>
                <input type=<%=type%> id=<%=field.getName()%> name=<%=field.getName()%> placeholder=<%=placeHolder%>>
            </label><br>

            <%}%>

            <input type="submit" value="Sign-up">
        </form>

    </div>
</body>
</html>
