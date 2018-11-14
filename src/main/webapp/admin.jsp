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


<div class="menuTop"><a href="/logout"> <i class="fas fa-sign-out-alt"></i> Log out</a>  <a href="/admin"> <i class="fas fa-cogs"></i> Admin</a> <div class="holder">     <img src="logos/logo1.png" alt="Avatar" />
    &nbsp;</div>  </div>
<div class="containerHome">
    <%  /** if the parameters are already in place, grab them **/
        String test = request.getParameter("test");

    %>
    <h2>Gamifikator - Admin <% test.toString(); %> ${requestScope.test} u </h2>
    <p><input type="text" name="foo" value="${requestScope.test}" />
        coucou </p>
    <div class="devHolder"> <p> ${requestScope.test} </p></div>
</div>



<script  src="elastic/js/index.js"></script>




</body>

</html>
