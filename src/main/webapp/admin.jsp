<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="en" >

<head>
    <meta charset="UTF-8">
    <title>Elastic Login Form</title>



    <link rel="stylesheet" href="elastic/css/style.css">
    <link rel="shortcut icon" href="logos/logoWR.png" type="image/x-icon">

</head>

<body>

<!--Google Font - Work Sans-->
<link href='https://fonts.googleapis.com/css?family=Work+Sans:400,300,700' rel='stylesheet' type='text/css'>
<!--font awesome -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">


<div class="menuTop"><a href="logout"> <i class="fas fa-sign-out-alt"></i> Log out</a>  <a href="admin"> <i class="fas fa-cogs"></i> Admin</a> <a href="#" onclick="switchTheme()"> <i class="fas fa-magic"></i> Theme </a><div class="holder">    <a href="home" class="logo"> <img id="logoIMG" src="logos/logoWR.png" alt="Avatar" /></a>
    &nbsp;</div>  </div>
<div class="containerHome containerAdmin">
    <h2><span class="big">Gamifikator</span> <br> ${user.email}</h2>

    <div>
        <if test="${not empty message}">
            <p class="errorMessage" >${message}</p>
        </if>
    </div>

    <div id="containerDev">
    <c:forEach items="${users}" var="user" >
        <div id="${user.username}" class="devHolder app" >


            <a href="admin?cmd=1&email=${user.email}" id="suspend" ><i class="devButton far fa-stop-circle"></i></a>
            <a href="admin?cmd=2&email=${user.email}" id="rstPass" ><i class="devButton fas fa-sync-alt"></i></a>



            <p>Nom : ${user.username}</p><br>
            <p>Email : ${user.email}</p><br>

            <a href="admin?&email=${user.email}" class="btn appA"  onclick="toggleL()">Applications</a>

        </div>
        </a>

    </c:forEach>
    </div>
</div>

<!-- SHOW APPLICATIONS LIST -->
<div id="showDev" class="${applist}"><i id="close" class="fas fa-window-close" onclick="toggleL()"></i>

    <c:forEach items="${apps}" var="app" >
        <div id="${app.name}" class="devHolder app" onclick="toggleL(${user.email})">
            <h4>${app.name}</h4>
            <p>Owner : ${app.owner}</p><br>
        </div>
    </c:forEach>


</div>





<script  src="elastic/js/admin.js"></script>




</body>

</html>
