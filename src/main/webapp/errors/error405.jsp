<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<html>
<head>
    <title>Error 405</title>
</head>
<body>
<%@ include file="../navbar.jsp"%>

<div class="flex-container">
    <div class="flex-content">
        <!-- Contenuto della pagina -->

        <h1> Error 405 :( </h1>
        <h1> Method Not Allowed</h1>
        <p>
            The method received in the request-line is known by the origin server but not supported by the target resource.
        </p>

    </div>

<%@ include file="../footer.html"%>
</body>
</html>
