<!DOCTYPE html>
<html lang="en" >

<head>
    <meta charset="UTF-8">
    <title>Elastic Login Form</title>



    <link rel="stylesheet" href="elastic/css/style.css">
    <link rel="shortcut icon" href="https://api.ning.com/files/*54juLYOTrMdu4eBaIkD5urGoLY-RZV7WCPaEMInPMnHNVM-k0nOmyGbBgR0pPONCf-vv7xrdDzY2efL-2SoM6jbvSFWFSn4/Gamification_head.png" type="image/x-icon">

</head>

<body>

<!--Google Font - Work Sans-->
<link href='https://fonts.googleapis.com/css?family=Work+Sans:400,300,700' rel='stylesheet' type='text/css'>
<!--font awesome -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">


<div class="menuTop"><a href="logout"> <i class="fas fa-sign-out-alt"></i> Log out</a>  <a href="admin"> <i class="fas fa-cogs"></i> Admin</a> <div class="holder">    <a href="home" class="logo"> <img src="logos/logoWG.png" alt="Avatar" /></a>
    &nbsp;</div>  </div>
<div class="containerHome">

    <c:forEach app="${list}" var="app">
        ${app}<br>
    </c:forEach>

    <h2>Gamifikator - profile et applications</h2>
    <div id="containerH" class>
    <div id="buttonAddApp"><button id="toggleAddApp" class="btn float-right">Add application</button></div>

    <div id="addApp" class="app innerShadow ">

        <form action="upload" enctype="multipart/form-data" method="post" class="app__form ">
            <div class="app__fields">
                <div class="field">
                    <input type="text" id="appname" name="appname" class="input" required pattern=.*\S.* />
                    <label for="appname" class="label">Application name</label>
                </div>
                <div class="field">
               <!--     <textarea name="appDesc " id="appDesc" cols="50" rows="3"></textarea> -->
                    <input type="text" id="appDesc" name="appDesc" class="input" required pattern=.*\S.* />

                    <label for="appDesc" class="label">Short Description</label>
                </div>
                <div class="field">
                   <!-- <label for="appFile" class="label">Select WAR</label> -->
                    <input type="file" name="UploadAddServlet" id="appFile"  />

                </div>
                <div class="app__footer">
                    <input type="submit" id="addSubmit" value="Add" class="btn">
                </div>
                <div>
                    <c:if test="${not empty login_error}">
                        <p class="errorMessage" >${login_error}</p>
                    </c:if>
                </div>
            </div>

        </form>


    </div>

    <div id="showApps">

        <c:forEach items="${list}" var="item">
            ${item}<br>
        </c:forEach>
        <div class="container">
            <div class="profile">
                <button class="profile__avatar profile--open" id="toggleProfile">
                    <img class="bottom" src="logos/logoWB.png" />
                </button>
                <form action="login" method="post" class="profile__form">
                    <div class="profile__fields">
                        <div class="field">
                            <input type="text" id="email" name="email" class="input" required pattern=.*\S.* />
                            <label for="email" class="label">Email</label>
                        </div>
                        <div class="field">
                            <input type="password" id="password" name="password" class="input" required pattern=.*\S.* />
                            <label for="password" class="label">Password</label>
                        </div>
                        <div class="profile__footer">
                            <input type="submit" id="bSubmit" value="Login" class="btn">
                        </div>
                        <div>
                            <c:if test="${not empty home_error}">
                                <script text="javascript">
                                    [].map.call(document.querySelectorAll('.profile'), function(el) {
                                        el.classList.toggle('profile--open');
                                    });
                                </script>
                                <p class="errorMessage" >${home_error}</p>
                            </c:if>
                        </div>
                    </div>
                    <a href="register" id="regist">Not yet a member ?</a>

                </form>

            </div>

        </div>


        <br><br><br><br><br>
        <p>coucou</p>

    </div>
</div>
</div>


<script  src="elastic/js/home.js"></script>




</body>

</html>
