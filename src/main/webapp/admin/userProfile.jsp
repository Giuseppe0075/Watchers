<%@ page import="Model.Beans.UserBean" %>
<%@ page import="Model.Models.UserModel" %>
<%@ page import="Model.Beans.PurchaseBean" %>
<%@ page import="Model.Models.PurchaseModel" %>
<%@ page import="Model.Models.WatchModel" %>
<%@ page import="java.util.*" %>
<%@ page import="Model.Beans.WatchBean" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>User Profile</title>
    <%
        Long sessionUser = (Long) session.getAttribute("user");
        WatchModel watchModel = new WatchModel();
        UserBean userModify;
        String userIdObject = request.getParameter("id");

        if (userIdObject != null) {
            UserModel userModel = new UserModel();
            Long userId = Long.parseLong(userIdObject);
            try {
                userModify = userModel.doRetrieveByKey(List.of(userId));
            } catch (Exception e) {
                throw new RuntimeException("Error while getting user data", e);
            }
        } else {
            throw new RuntimeException("UserID not found");
        }
    %>
    <style>

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
        }

        .form-group .input-container {
            position: relative;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            border: 2px solid #cccccc;
            border-radius: 12px;
            outline: none;
            box-sizing: border-box;
        }

        .form-group input:focus {
            border-color: #007BFF;
        }

        .form-group .error-icon, .form-group .success-icon {
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            display: none;
        }

        .form-group .error {
            color: red;
            padding-top: 8px;
        }

        .form-group input.border-red {
            border-color: red;
        }

        .form-group input.border-green {
            border-color: green;
        }

        .form-group .error-icon.visible, .form-group .success-icon.visible {
            display: inline;
        }

        .error-message {
            color: red;
            padding-top: 8px;
        }

        /* Stile per il container principale */
        .user-container {
            padding: 10px;
            display: flex;
            flex-wrap: wrap;
            margin: auto;
            gap: 20px;
            justify-content: space-between;
            width: 80%;
        }

        .form-container,
        .order-list-container {
            flex: 1 1 45%;
            box-sizing: border-box;
        }

        /* Ensure form and order list stack vertically on small screens */
        @media (max-width: 768px) {
            .form-container,
            .order-list-container {
                flex: 1 1 100%;
            }
        }

        .form-container, .order-list-container {
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-group input, .form-group select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        button[type="submit"] {
            background-color: #5d9ea8;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }
        button[type="submit"]:hover {
            background-color: #498e99;
        }

        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0;
            border: 1px solid #ddd;
            border-radius: 10px;
            overflow: hidden;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<%@include file="../navbar.jsp"%>
<script src="../utils/formValidator.js"></script>


<div class="user-container">
    <div class="form-container">
        <% if (userModify != null) { %>
        <form id="userForm" action="${pageContext.request.contextPath}/admin/updateUser" method="post">
            <input type="hidden" name="id" value="<%= userModify.getId() %>">
            <div class="form-group">
                <label for="email">Email</label>
                <div class="input-container">
                    <input type="email" id="email" name="email" required pattern="^\w((\.)?\w+)*@\w+\.{1}[a-z]+(\.{1}\w+)*$" value="<%=userModify.getEmail()%>"/>
                    <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                    <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                </div>
                <div class="error"></div>
            </div>

            <div class="form-group">
                <label for="name">Name</label>
                <div class="input-container">
                    <input type="text" id="name" name="name" required maxlength="255"
                           value="<%= userModify.getName()%>" />
                    <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                    <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                </div>
                <div class="error"></div>
            </div>

            <div class="form-group">
                <label for="surname">Surname</label>
                <div class="input-container">
                    <input type="text" id="surname" name="surname" required
                           value="<%=userModify.getSurname()%>" />
                    <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                    <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                </div>
                <div class="error"></div>
            </div>
            <div class="form-group">
                <label for="cap">CAP</label>
                <div class="input-container">
                    <input type="text" id="cap" name="cap" required minlength="5" maxlength="5"
                           value="<%=userModify.getCAP()%>" />
                    <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                    <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                </div>
                <div class="error"></div>
            </div>
            <% if (!userModify.getId().equals(sessionUser)) { %>
            <div class="form-group">
                <label for="admin">Admin</label>
                <div class="input-container">
                    <select id="admin" name="admin">
                        <option value="true" <%= userModify.getAdmin() ? "selected" : "" %>>Yes</option>
                        <option value="false" <%= !userModify.getAdmin() ? "selected" : "" %>>No</option>
                    </select>
                    <span class="error-icon"><i class="fa-solid fa-circle-exclamation"></i></span>
                    <span class="success-icon"><i class="fa-solid fa-circle-check"></i></span>
                </div>
                <div class="error"></div>
            </div>
            <% } %>
            <button type="submit" class="btn-submit">Update</button>
        </form>
        <% } %>
    </div>

    <%
        PurchaseModel purchaseModel = new PurchaseModel();
        Collection<PurchaseBean> purchaseBeans;
        UserModel userModel = new UserModel();
        UserBean userBean;

        try {
            purchaseBeans = purchaseModel.doRetrieveByCond("WHERE user = ? ORDER BY id DESC", List.of(userIdObject));
            userBean = userModel.doRetrieveByKey(List.of(userIdObject));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long hid = purchaseBeans.iterator().next().getId();

        //Division of the purchases by order
        java.util.HashMap<Long, List<PurchaseBean>> orderMap = new HashMap<>();
        for(PurchaseBean purchaseBean : purchaseBeans) {
            if(orderMap.containsKey(purchaseBean.getId())) {
                orderMap.get(purchaseBean.getId()).add(purchaseBean);
            } else {
                List<PurchaseBean> list = new ArrayList<>();
                list.add(purchaseBean);
                orderMap.put(purchaseBean.getId(), list);
            }
        }
    %>

    <div class="order-list-container">
        <h1>Order History</h1>
        <% for(long i = hid; i >= 1; i--) {
            if(!orderMap.containsKey(i)) {
                continue;
            }
            List<PurchaseBean> purchaseBeansList = orderMap.get(i);
        %>
        <div class="order" style="overflow-x: scroll">
            <h2>Order <%= i %> </h2> <h5><%= purchaseBeansList.getFirst().getDate()%></h5>
            <table>
                <tr>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>IVA</th>
                    <th>Total</th>
                </tr>
                <% for(PurchaseBean purchaseBean : purchaseBeansList) {
                    WatchBean watchBean;
                    try {
                        watchBean = watchModel.doRetrieveByKey(List.of(purchaseBean.getWatch()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                %>
                <tr>
                    <td><%= watchBean.getName() %></td>
                    <td><%= purchaseBean.getQuantity() %></td>
                    <td><%= purchaseBean.getPrice() %>€</td>
                    <td><%= purchaseBean.getIVA() %>%</td>
                    <td><%= purchaseBean.getQuantity() * purchaseBean.getPrice() %>€</td>
                </tr>

                <div id="hidden-data-for-<%=i%>" hidden>
                    <div class="date"><%=purchaseBean.getDate()%></div>
                    <div class="user-name"><%=userBean.getName()%></div>
                    <div class="user-surname"><%=userBean.getSurname()%></div>
                    <div class="user-address"><%=userBean.getRoad()%>,<%=userBean.getCivic_number()%></div>
                </div>
                <% } %>
            </table>
        </div>
        <% } %>
    </div>
</div>

<%@include file="../footer.html"%>
</body>
</html>