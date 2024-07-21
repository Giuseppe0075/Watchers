<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<%@ include file="../navbar.jsp"%>

<div class="flex-container">
    <div class="flex-content">
        <!-- Contenuto della pagina -->

        <h1>An error occurred</h1>
        <p>Error details: <%= exception.getMessage() %></p>

    </div>

<%@ include file="../footer.html"%>
</body>
</html>
