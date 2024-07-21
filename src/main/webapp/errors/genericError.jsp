<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>An error occurred</h1>
<p>Error details: <%= exception.getMessage() %></p>
</body>
</html>
