<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="en" >

<head>
    <meta charset="UTF-8">
    <title>Elastic Login Form</title>



    <link rel="stylesheet" href="elastic/css/style.css">
    <link rel="shortcut icon" href="logos/logoWB.png" type="image/x-icon">

</head>

<body>

<!--Google Font - Work Sans-->
<link href='https://fonts.googleapis.com/css?family=Work+Sans:400,300,700' rel='stylesheet' type='text/css'>
<!--font awesome -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">


<div class="menuTop"><a href="logout"> <i class="fas fa-sign-out-alt"></i> Log out</a>  <a href="admin"> <i class="fas fa-cogs"></i> Admin</a> <div class="holder">    <a href="home" class="logo"> <img src="logos/logo1.png" alt="Avatar" /></a>
    &nbsp;</div>  </div>
<div class="containerHome">
    <h2>Gamifikator - Admin ${users[0]} u </h2>

    <div id="containerDev">
    <c:forEach items="${users}" var="user" >
        <div id="${user.username}" class="devHolder app" onclick="toggleL()">
            <a href="admin?cmd=1&email=${user.email}" id="suspend" ><i class="devButton far fa-stop-circle"></i></a>
            <a href="admin?cmd=2&email=${user.email}" id="rstPass" ><i class="devButton fas fa-sync-alt"></i></a>


            <p>Nom : ${user.username}</p><br>
            <p>Email : ${user.email}</p><br>


        </div>
    </c:forEach>
    </div>
</div>
<div id="showDev"><i id="close" class="fas fa-window-close" onclick="toggleL()"></i></div>





<script  src="elastic/js/admin.js"></script>




</body>

</html>
