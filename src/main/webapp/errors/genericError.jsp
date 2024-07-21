<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<%@ include file="../navbar.jsp"%>
<h1>An error occurred</h1>
<p>Error details: <%= exception.getMessage() %></p>
<%@ include file="../footer.html"%>
</body>
</html>
