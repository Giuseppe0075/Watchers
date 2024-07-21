<%@ page import="Model.Beans.UserBean" %>
<%@ page import="Model.Models.UserModel" %>
<%@ page import="java.util.List" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
    /* Stili di base */

    #search-input{
        width: 100%;
        padding: 10px;
        margin: 0;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    .search-container {
        position: relative;
        width: 200px;
        margin: 0;
    }

    .search-results {
        position: absolute;
        width: 100%;
        border: 1px solid #ccc;
        max-height: 200px;
        overflow-y: auto;
        background-color: #fff;
        z-index: 1000;
    }
    .search-result {
        padding: 10px;
        cursor: pointer;
    }
    .search-result:hover {
        background-color: #f0f0f0;
    }
</style>

<script>
    toastr.options = {
        "closeButton": true,
        "debug": false,
        "newestOnTop": true,
        "progressBar": true,
        "positionClass": "toast-top-right",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "5000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    }
</script>
<% { %><!-- don't delete, it's used to avoid scope problems -->
<header>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/navbar.css">
    <%
        long user =  session.getAttribute("user") == null ? 0L : (long) session.getAttribute("user");
        UserModel userModel = new UserModel();
        UserBean userBean;
        try {
            userBean = userModel.doRetrieveByKey(List.of(user));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    %>

    <nav>
        <a href="${pageContext.request.contextPath}/homepage.jsp" id="logo">
            <img src="${pageContext.request.contextPath}/homepage/LOGO.png" alt="Logo">
        </a>
        <div class="search-container">
            <label for="search-input"></label>
            <input type="text" id="search-input" placeholder="Search...">
            <div id="search-results" class="search-results"></div>
        </div>
        <div class="navbar-items-container">
            <ul class="navbar-items">
                <li><a href="${pageContext.request.contextPath}/catalogue/catalogue.jsp">Catalogue</a></li>
                <li><a href="${pageContext.request.contextPath}/cart/cart.jsp">Cart</a></li>
                <li><a href="${pageContext.request.contextPath}/favourites/favourites.jsp">Favourites</a></li>

                <% if (user != 0L) {%>
                <li><a href="${pageContext.request.contextPath}/user/logout">Logout</a></li>
                <% }if (userBean != null && userBean.getAdmin()) { %>
                <li><a href="${pageContext.request.contextPath}/admin/userManager.jsp">UserManager</a> </li>
                <li><a href="${pageContext.request.contextPath}/admin/productList.jsp">ProductManager</a> </li>
                <li><a href="${pageContext.request.contextPath}/admin/orderList.jsp">OrderList</a> </li>
                <% } %>
            </ul>
        </div>
        <% if (userBean == null) { %>
            <a href="${pageContext.request.contextPath}/user/login.jsp" id="user">
        <% } else { %>
            <a href="${pageContext.request.contextPath}/user/personalArea.jsp" id="user">
        <% } %>
            <img class="omino" src="${pageContext.request.contextPath}/homepage/lalal.png" alt="Omino" id="user">
        </a>
        <span style="font-size:30px;cursor:pointer" onclick="openNav()" id="menu">&#9776;</span>
    </nav>
    <div id="mySidenav" class="sidenav">
        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
        <a href="${pageContext.request.contextPath}/catalogue/catalogue.jsp">Catalogue</a>
        <a href="${pageContext.request.contextPath}/cart/cart.jsp">Cart</a>
        <a href="${pageContext.request.contextPath}/favourites/favourites.jsp">Favourites</a>
        <% if (user != 0L) {%>
            <a href="${pageContext.request.contextPath}/user/logout">Logout</a>
        <% }if (userBean != null && userBean.getAdmin()) { %>
            <a href="${pageContext.request.contextPath}/admin/userManager.jsp">UserManager</a>
            <a href="${pageContext.request.contextPath}/admin/productList.jsp">ProductManager</a>
            <a href="${pageContext.request.contextPath}/admin/orderList.jsp">OrderList</a>
        <% } if (user == 0L) {%>
            <a href="${pageContext.request.contextPath}/user/login.jsp">Sign-in/Sign-up</a>
        <% } %>
    </div>
    <script>
        function openNav() {
            document.getElementById("mySidenav").style.width = "250px";
        }

        function closeNav() {
            document.getElementById("mySidenav").style.width = "0";
        }

        document.getElementById('search-input').addEventListener('input', function() {
            let query = this.value;
            if (query.length > 2) { // Esegui la ricerca solo se la query è più lunga di 2 caratteri
                fetchResults(query);
            } else {
                document.getElementById('search-results').innerHTML = '';
            }
        });

        function fetchResults(query) {
            console.log(query)
            fetch('<%= request.getContextPath() %>/search', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    'query': query
                })
            })
                .then(response => response.json())
                .then(results => displayResults(results))
                .catch(error => console.error('Error:', error));
        }

        function displayResults(results) {
            console.log(results);
            const resultsContainer = document.getElementById('search-results');
            resultsContainer.innerHTML = '';

            if (results.length === 0) {
                resultsContainer.innerHTML = '<div class="search-result">No results found</div>';
                return;
            }

            results.forEach(result => {
                let div = document.createElement('div');
                div.classList.add('search-result');
                // Aggiornato per visualizzare il nome dell'orologio
                div.textContent = result.name; // Usa la proprietà 'name' dell'oggetto risultato
                div.addEventListener('click', function() {
                    window.location.href = '<%= request.getContextPath() %>/watchpage/watch.jsp?id=' + result.id;
                });
                resultsContainer.appendChild(div);
            });
        }
    </script>
</header>
<% } %> <!-- don't delete, it's used to avoid scope problems -->