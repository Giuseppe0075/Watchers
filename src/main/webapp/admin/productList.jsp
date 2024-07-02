<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="storage.Models.WatchModel" %>
<%@ page import="java.util.List" %>
<%@ page import="storage.Beans.WatchBean" %><%--
  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 30/05/2024
  Time: 00:12
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<link rel="stylesheet" href="../style/styleFooter.css">
    <%
        WatchModel watchModel = new WatchModel();
        List<WatchBean> watches;
        try {
            watches = (List<WatchBean>) watchModel.doRetrieveByCond("WHERE visible= ?",List.of(true));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    %>
    <%@include file="../navbar.jsp"%>

    <table>
        <tr>
            <th>Nome</th>
            <th>Brand</th>
            <th>Descrizione</th>
            <th>Prezzo</th>
            <th>IVA</th>
            <th>Visibile</th>
            <th>Azione</th>
        </tr>
        <% for (WatchBean watch : watches){ %>
            <tr>
                <td><a href="${pageContext.request.contextPath}/admin/modifyWatch.jsp?id=<%=watch.getId()%>"><%=watch.getName()%></a></td>
                <td><%=watch.getBrand()%></td>
                <td><%=watch.getDescription()%></td>
                <td><%=watch.getPrice()%></td>
                <td><%=watch.getIVA()%></td>
                <td><%=watch.getVisible()%></td>
                <td><button class="elimina" data-id="<%= watch.getId() %>">X</button></td>
            </tr>
        <% } %>
    </table>

    <%@include file="../footer.html"%>

    <script>
        $(document).ready(function() {
            $(".elimina").click(function() {
                let watchId = $(this).data("id"); // Ottieni l'ID dall'attributo data-id
                let button = $(this); // Riferimento al bottone cliccato
                    $.ajax({
                        url: '${pageContext.request.contextPath}/admin/deleteWatch', // URL del Servlet che gestisce l'eliminazione
                        type: 'POST',
                        data: {id: watchId}, // Dati inviati al server
                        success: function(response) {
                            button.closest('tr').remove(); // Rimuove la riga della tabella del bottone cliccato
                        },
                        error: function() {
                            // Gestisci errori di richiesta qui
                            alert('An error occurred while removing the watch.');
                        }
                    });
               // }
            });
        });
    </script>

</body>
</html>
