<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
          integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
          crossorigin="anonymous"
          referrerpolicy="no-referrer" />

    <title>Sign-Up</title>

    <style>
        .background {
            z-index: -1;
            background-size: cover;
            background-color: #dddddd;
        }

        .side-column {
            background-size: cover;
            background-position: 50%;
            background-color: #eeeeee;
        }

        </style>
</head>
<body>
    <!-- Navbar -->
    <%@include file="../navbar.jsp"%>

    <div class="h-full w-full fixed top-0 background"></div>
    <div class="w-full px-8 mt-32 flex shadow-2xl">
        <div class="w-1/2 p-12 side-column">
            <h2 class="text-black text-8xl mt-16 font-bold">Watchers</h2>

            <p class="text-black-400 my-8"> Lorem ipsum dolor sit amet, consectetur adipisicing elit. Asperiores dolorem eligendi enim error eveniet excepturi facilis inventore labore modi nihil, optio provident quidem quod, ratione recusandae suscipit totam ullam ut.</p>
            <p class ="text-black my-8 font-bold"> Register with social media</p>
            <div class="flex gap-8">
                <div class="flex items-center gap-4 text-sm bg-blue-900 text-white px-8 py-2 rounded-full">
                    <i class="fa-brands fa-facebook-f"></i>
                    <span>Facebook</span>
                </div>
                <div class="flex items-center gap-4 text-sm bg-sky-400 text-white px-8 py-2 rounded-full">
                    <i class="fa-brands fa-twitter"></i>
                    <span>Twitter</span>
                </div>
            </div>
        </div>
        <div class="w-1/2 p-16 bg-white">
            <h2 class="text-4xl font-bold">Sign-up</h2>
            <p class="my-8">Already have an account? <a class="text-blue-700" href="${pageContext.request.contextPath}/user/login.jsp">Login here </a> instead  </p>
            <form id="registrationForm" method="POST" action="${pageContext.request.contextPath}/signup">

                <%--Name--%>
                <div class="formGroup flex flex-col my-4">
                    <label for="name">Name</label>
                    <div class="flex items-center">
                        <input type="text" id="name" name="name" class="w-full border-b-2 border-grey-200 outline-none py-2"
                               required
                               maxlength="255"
                        />
                        <span class="error-icon hidden -ml-6 text-red-700">
                            <i class="fa-solid fa-circle-exclamation"></i>
                        </span>
                        <span class="success-icon hidden -ml-6 text-green-700">
                            <i class="fa-solid fa-circle-check"></i>
                        </span>
                    </div>
                    <div class="error text-red-700 py-2"></div>
                </div>

                <%--Surname--%>
                <div class="formGroup flex flex-col my-4">
                    <label for="surname">Surname</label>
                    <div class="flex items-center">
                        <input type="text" id="surname" name="surname" class="w-full border-b-2 border-grey-200 outline-none py-2"
                               required
                               maxlength="255"
                        />
                        <span class="error-icon hidden -ml-6 text-red-700">
                        <i class="fa-solid fa-circle-exclamation"></i>
                        </span>
                        <span class="success-icon hidden -ml-6 text-green-700">
                            <i class="fa-solid fa-circle-check"></i>
                        </span>
                    </div>
                    <div class="error text-red-700 py-2"></div>
                </div>

                <%--Birthday--%>
                <div class="formGroup flex flex-col my-4">
                    <label for="birthday">Birthday</label>
                    <div class="flex items-center">
                        <input type="date" id="birthday" name="birthday" class="w-full border-b-2 border-grey-200 outline-none py-2"
                               required
                        />
                        <span class="error-icon hidden -ml-6 text-red-700">
                            <i class="fa-solid fa-circle-exclamation"></i>
                        </span>
                        <span class="success-icon hidden -ml-6 text-green-700">
                            <i class="fa-solid fa-circle-check"></i>
                        </span>
                    </div>
                    <div class="error text-red-700 py-2"></div>
                </div>

                <%--Road--%>
                <div class="formGroup flex flex-col my-4">
                    <label for="road">Road</label>
                    <div class="flex items-center">
                        <input type="text" id="road" name="road" class="w-full border-b-2 border-grey-200 outline-none py-2"
                               required
                               maxlength="255"
                        />
                        <span class="error-icon hidden -ml-6 text-red-700">
                            <i class="fa-solid fa-circle-exclamation"></i>
                        </span>
                        <span class="success-icon hidden -ml-6 text-green-700">
                            <i class="fa-solid fa-circle-check"></i>
                        </span>
                    </div>
                    <div class="error text-red-700 py-2"></div>
                </div>

                <%--civic_number--%>
                <div class="formGroup flex flex-col my-4">
                    <label for="civic_number">Civic Number</label>
                    <div class="flex items-center">
                        <input type="text" id="civic_number" name="civic_number" class="w-full border-b-2 border-grey-200 outline-none py-2"
                               required
                               maxlength="255"
                        />
                        <span class="error-icon hidden -ml-6 text-red-700">
                        <i class="fa-solid fa-circle-exclamation"></i>
                    </span>
                        <span class="success-icon hidden -ml-6 text-green-700">
                        <i class="fa-solid fa-circle-check"></i>
                    </span>
                    </div>
                    <div class="error text-red-700 py-2"></div>
                </div>

                <%--City--%>
                <div class="formGroup flex flex-col my-4">
                    <label for="city">City</label>
                    <div class="flex items-center">
                        <input type="text" id="city" name="city" class="w-full border-b-2 border-grey-200 outline-none py-2"
                               required
                               maxlength="255"
                        />
                        <span class="error-icon hidden -ml-6 text-red-700">
                        <i class="fa-solid fa-circle-exclamation"></i>
                    </span>
                        <span class="success-icon hidden -ml-6 text-green-700">
                        <i class="fa-solid fa-circle-check"></i>
                    </span>
                    </div>
                    <div class="error text-red-700 py-2"></div>
                </div>

                <%--CAP--%>
                <div class="formGroup flex flex-col my-4">
                    <label for="cap">CAP</label>
                    <div class="flex items-center">
                        <input type="text" id="cap" name="cap" class="w-full border-b-2 border-grey-200 outline-none py-2"
                               required
                               minlength="5"
                               maxlength="5"
                        />
                        <span class="error-icon hidden -ml-6 text-red-700">
                        <i class="fa-solid fa-circle-exclamation"></i>
                    </span>
                        <span class="success-icon hidden -ml-6 text-green-700">
                        <i class="fa-solid fa-circle-check"></i>
                    </span>
                    </div>
                    <div class="error text-red-700 py-2"></div>
                </div>

                <%--Email--%>
                <div class="formGroup flex flex-col my-4">
                    <label for="email">Email</label>
                    <div class="flex items-center">
                        <input type="email" id="email" name="email" class="w-full border-b-2 border-grey-200 outline-none py-2"
                               required
                               pattern=^\w((\.)?\w+)*@\w+\.{1}[a-z]+(\.{1}\w+)*$
                        />
                        <span class="error-icon hidden -ml-6 text-red-700">
                        <i class="fa-solid fa-circle-exclamation"></i>
                        </span>
                        <span class="success-icon hidden -ml-6 text-green-700">
                            <i class="fa-solid fa-circle-check"></i>
                        </span>
                    </div>
                    <div class="error text-red-700 py-2"></div>
                </div>

                <%--Password--%>
                <div class="formGroup flex flex-col my-4">
                    <label for="password">Password</label>
                    <div class="flex items-center">
                        <input type="password" id="password" name="password" class="w-full border-b-2 border-grey-200 outline-none py-2"
                               required
                               minlength="8"
                               pattern=^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&.])[A-Za-z\d@$!%*#?&.]{8,}$
                        />
                        <span class="error-icon hidden -ml-6 text-red-700">
                            <i class="fa-solid fa-circle-exclamation"></i>
                        </span>
                        <span class="success-icon hidden -ml-6 text-green-700">
                            <i class="fa-solid fa-circle-check"></i>
                        </span>
                    </div>
                    <div class="error text-red-700 py-2"></div>
                </div>

                <%--Confirm Password--%>
                <div class="formGroup flex flex-col my-4">
                    <label for="confirmPassword">Confirm Password</label>
                    <div class="flex items-center">
                        <input type="password" id="confirmPassword" name="confirmPassword" class="w-full border-b-2 border-grey-200 outline-none py-2"
                               required
                               match="password"
                        />
                        <span class="error-icon hidden -ml-6 text-red-700">
                        <i class="fa-solid fa-circle-exclamation"></i>
                    </span>
                        <span class="success-icon hidden -ml-6 text-green-700">
                        <i class="fa-solid fa-circle-check"></i>
                    </span>
                    </div>
                    <div class="error text-red-700 py-2"></div>
                </div>

                <button type="submit" class="px-8 py-2 mt-4 bg-blue-400 w-full rounded-full text-white shadow-large">Sign-up</button>
            </form>
        </div>
    </div>
    <script src="../utils/formValidator.js"></script>
</body>
</html>
