<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Watchers</title>
    <!-- <link rel="icon" type="image/x-icon" href="/images/favicon.ico"> -->
    <link rel="stylesheet" href="homepage/style.css">

</head>
<body>

<nav><!--NavBar -->
    <div> <!--Logo-->
        <h1 id="title"><a href="">Watchers</a></h1>
    </div>
    <div> <!--Menu-->
        <ul class="menu">
            <li><a href="">Menu1</a></li>
            <li><a href="">Menu2</a></li>
            <li><a href="">Menu3</a></li>
            <li><a href="">Menu4</a></li>
        </ul>
    </div>
    <div class="Account"> <!--Account ICon-->
        <a href="login-servlet"><img id="AccountLogo" src="homepage/omino.png"></a>
    </div>

</nav>


<div id="frontPage">
    <video id="mainVideo" autoplay muted loop>
        <source src="homepage/home.webm" type="video/webm">
        Your browser does not support the video tag.
    </video>

    <div class="itemPage"> <!-- Item main page -->
        <ul>
            <li><a href="watchpage/watch.html"><img src="homepage/watch1.avif"></a></li>
            <li><a href="watchpage/watch.html"><img src="homepage/watch2.avif"></a></li>
            <li><a href="watchpage/watch.html"><img src="homepage/watch3.avif"></a></li>
        </ul>
    </div>
</div>
</body>
</html>
