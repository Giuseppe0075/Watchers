<%@ page import="user.SignupDataForm" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="utils.FieldDescriptor" %><%--
  Created by IntelliJ IDEA.
  User: giuse
  Date: 29/04/2024
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Watch</title>
</head>
<body>
    <div class="container">
        <%@include file="../navbar.jsp"%>
        <h2>Watch registration</h2>
        <form action="${pageContext.request.contextPath}/add-watch" method="post">
            <%
                for(Field field: SignupDataForm.class.getDeclaredFields()){
                    FieldDescriptor descriptor = field.getAnnotation(FieldDescriptor.class);
                    String placeHolder = descriptor != null ? descriptor.description() : "";
            %>

            <label for=name=><%=field.getName()%></label>
            <input type="text" id=<%=field.getName()%> name=<%=field.getName()%> placeholder=<%=placeHolder%>><br>

            <%}%>

            <input type="submit" value="Add Watch">
        </form>
        <%@include file="../footer.html"%>
    </div>
</body>
</html>
