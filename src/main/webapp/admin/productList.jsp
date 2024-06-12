<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="storage.Models.WatchModel" %>
<%@ page import="storage.Models.Model" %>
<%@ page import="java.util.List" %>
<%@ page import="storage.Beans.WatchBean" %><%--
  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 30/05/2024
  Time: 00:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        WatchModel watchModel = new WatchModel();
        List<WatchBean> watchs;
        try {
            watchs = (List<WatchBean>) watchModel.doRetrieveByCond("WHERE visible= ?",List.of(true));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    %>
    <%@include file="../navbar.jsp"%>

    <table>
        <thead>
            <td>Nome</td>
            <td>Brand</td>
            <td>Descrizione</td>
            <td>Prezzo</td>
            <td>IVA</td>
            <td>Visibile</td>
            <td>Azione</td>
        </thead>
        <%for (WatchBean watch : watchs){%>
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
                var watchId = $(this).data("id"); // Ottieni l'ID dall'attributo data-id
                var button = $(this); // Riferimento al bottone cliccato
                    $.ajax({
                        url: '${pageContext.request.contextPath}/admin/deleteWatch', // URL del Servlet che gestisce l'eliminazione
                        type: 'POST',
                        data: {id: watchId}, // Dati inviati al server
                        success: function(response) {
                            button.closest('tr').remove(); // Rimuove la riga della tabella del bottone cliccato
                        },
                        error: function() {
                            // Gestisci errori di richiesta qui
                            alert('Errore nell\'eliminazione dell\'orologio.');
                        }
                    });
               // }
            });
        });
    </script>

</body>
</html>
