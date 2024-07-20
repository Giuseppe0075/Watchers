<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="Model.Models.WatchModel" %>
<%@ page import="java.util.List"%>
<%@ page import="Model.Beans.WatchBean" %><%--
  Created by IntelliJ IDEA.
  User: Pasquale Livrieri
  Date: 30/05/2024
  Time: 00:12
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Product Manager</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<style>
    table {
        width: 100%;
        border-collapse: separate;
        border-spacing: 0;
        border: 1px solid #ddd;
        border-radius: 10px;
        overflow: hidden;
    }



    th, td {
        padding: 8px;
        text-align: left;
    }

    th {
        background-color: #f2f2f2;
    }

</style>
<link rel="stylesheet" href="../style/styleProductList.css">
    <%
        WatchModel watchModel = new WatchModel();
        List<WatchBean> watches;
        try {
            watches = (List<WatchBean>) watchModel.doRetrieveByCond("Order by visible DESC", List.of());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    %>
    <%@include file="../navbar.jsp"%>
<div class="product-container">
    <div style="overflow-x: auto;" >
        <h2 style="text-align: center">Product Manager</h2>
    <table>
        <tr>
            <th>Name</th>
            <th>Brand</th>
            <th>Description</th>
            <th>Price</th>
            <th>IVA</th>
            <th>Visible</th>
            <th>Remove</th>
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
                <td><button class="delete-btn" data-id="<%= watch.getId() %>">X</button></td>
            </tr>
        <% } %>
        </tbody>
    </table>
    </div>
</div>


    <script>
        $(document).ready(function() {
            $(".delete-btn").click(function() {
                let watchId = $(this).data("id"); // Ottieni l'ID dall'attributo data-id
                let button = $(this); // Riferimento al bottone cliccato
                    $.ajax({
                        url: '${pageContext.request.contextPath}/admin/deleteWatch', // URL del Servlet che gestisce l'eliminazione
                        type: 'POST',
                        data: {id: watchId}, // Dati inviati al server
                        success: function() {
                            button.closest('tr').find('td').eq(5).text('false');
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
            const columns = Array.from({ length: 7 }, (_) =>
                document.querySelectorAll(`tbody td:nth-child(${1})`)
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
