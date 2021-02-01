<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <style>
        body {
            margin: 0;
            background-size: cover;
            background: black url("images/backgr.png") no-repeat center center;
            background-position-y: 150px;
        }
    </style>

    <title>HOME</title>
</head>
<body>

<nav class="navbar navbar-dark bg-dark">
    <img height="50px" src="images/logo-java-jdbc.png" alt="logo">
    <div class="nav">
        <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/home">Home </a>
        <a class="nav-item nav-link" href="login">login</a>
        <a class="nav-item nav-link" href="register">Sign UP</a>
        <a class="nav-item nav-link disabled" href="">Disabled</a>
    </div>
</nav>

    <h1>HOME Page</h1>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script></body>
</html>
