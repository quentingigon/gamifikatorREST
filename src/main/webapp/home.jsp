<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

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


<div class="menuTop"><a href="logout"> <i class="fas fa-sign-out-alt"></i> Log out</a>
    <a href="admin"> <i class="fas fa-cogs"></i> Admin</a>
    <a href="#" onclick="switchTheme()"> <i class="fas fa-magic"></i> Theme </a>  <div class="holder">    <a href="home" class="logo"> <img src="logos/logoWG.png" alt="Avatar" /></a>
        &nbsp;  <button class="btn changeName" id="toggleProfile" >
            Change name
        </button>
    </div>
</div>

<div class="containerHome containerAdmin">

    <h2><span class="big">Gamifikator</span> <br> profile and applications for ${user.username}</h2>
    <div id="containerH" class>


        <div id="buttonAddApp"><button id="toggleAddApp" class="btn float-right">Add application</button></div>

        <div>
            <if test="${not empty app_error}">
                <p class="errorMessage" >${app_error}</p>
            </if>
        </div>

        <div id="addApp" class="app innerShadow ">

            <form action="app" method="post" class="app__form ">
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
                      <div class="app__footer">
                        <input type="submit" id="addSubmit" value="Add" class="btn">
                    </div>
                    <div>

                    </div>
                </div>

            </form>


        </div>
        <div id="containerApp">


            <c:forEach items="${apps}" var="app" >

                <div id="${app.name}" class="appForDev" onclick="">
                    <form action="home" method="post" class=" ">
                        <p  class="dev_field dev_title"> ${app.name} </p>
                        <input type="text"  name="appname" value="${app.name}" style="display:none;">

                        <textarea name="appDesc" class="dev_field dev_text" cols="50" rows="3" placeholder="${app.description}" ></textarea>

                        <p class="apikey">${app.apiKey}</p><br>

                        <p>${app.createDate}</p><br>
                        <input type="submit"  value="Update" class="btn update_btn">

                    </form>
                    <form action="home" method="post">
                        <input type="text"  name="deleteApp" value="${app.name}" style="display:none;">

                        <button type="submit"  value="Delete" class="btn delete_btn">
                    <i class="far fa-trash-alt"></i></button>
                    </form>
                </div>

            </c:forEach>
        </div>
    </div>
</div>


<form action="home" method="post">
    <input type="text"  name="increment" value="minus" style="display:none;">

    <button type="submit"  value="" class="btn left_btn">
        <i class="fas fa-chevron-circle-left"></i>
    </button>
</form>

<form action="home" method="post">
    <input type="text"  name="increment" value="plus" style="display:none;">

    <button type="submit"  value="" class="btn right_btn">
        <i class="fas fa-chevron-circle-right"></i>
    </button>
</form>

<div class="container">
    <div class="profile">

        <form action="home" method="post" class="profile__form no_padding">
            <p id="textH">So. Wanna change name ? </p>

            <div class="profile__fields">

                <div class="field">
                    <input type="text" id="newname" name="newName" class="input" required pattern=.*\S.* />
                    <label for="newName" class="label">New name:</label>
                </div>
            </div>
        </form>

    </div>

</div>



<script  src="elastic/js/home.js"></script>




</body>

</html>
