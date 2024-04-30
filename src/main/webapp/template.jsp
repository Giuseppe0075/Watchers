<%--
  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 23/04/2024
  Time: 09:55
  To change this template use File | Settings | File Templates.
--%>
<!-- template.jsp -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Il tuo titolo del sito</title>
    <!-- Includi qui i tuoi stili CSS -->
</head>
<body>

<jsp:include page="navbar.jsp">Header</jsp:include>

<main>
    <!-- Questo sarà il contenuto dinamico delle tue pagine -->
    <%-- Qui inseriremo il contenuto specifico di ogni pagina --%>
    <jsp:include page="${content}" />
</main>

<jsp:include page="footer.html">Footer</jsp:include>
</body>
</html>

