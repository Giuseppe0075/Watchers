<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="storage.Models.WatchModel" %>
<%@ page import="java.util.List"%>
<%@ page import="storage.Beans.WatchBean" %><%--
  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 30/05/2024
  Time: 00:12
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Lista Prodotti</title>
</head>
<body>
<link rel="stylesheet" href="../style/styleFooter.css">
<link rel="stylesheet" href="../style/styleProductList.css">
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
    <div style="overflow-x: auto;">
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
        <tbody>
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
        </tbody>
    </table>
    </div>
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
        document.addEventListener("DOMContentLoaded", function () {
            const columns = Array.from({ length: 7 }, (_, i) =>
                document.querySelectorAll(`tbody td:nth-child(${i + 1})`)
            );

            columns.forEach((col, i) => {
                col.forEach((cell, j) => {
                    setTimeout(() => {
                        cell.style.opacity = "1";
                        cell.style.transform = "translateY(0)";
                    }, (i + j * 1.5) * 50);
                });
            });
        });
    </script>

<%@include file="../footer.html"%> <!-- Footer -->

</body>
</html>
