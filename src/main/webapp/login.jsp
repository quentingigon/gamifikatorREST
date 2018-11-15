<!DOCTYPE html>
<html lang="en" >

<head>
    <meta charset="UTF-8">
    <title>Elastic Login Form</title>



    <link rel="stylesheet" href="elastic/css/style.css">


</head>

<body>

<!--Google Font - Work Sans-->
<link href='https://fonts.googleapis.com/css?family=Work+Sans:400,300,700' rel='stylesheet' type='text/css'>

<div class="container">
    <div class="profile">
        <button class="profile__avatar" id="toggleProfile">
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
                    <c:if test="${not empty login_error}">
                        <p >${login_error}</p>
                    </c:if>
                </div>
            </div>
            <a href="register" id="regist">Not yet a member ?</a>

        </form>

    </div>

</div>



<script  src="elastic/js/index.js"></script>




</body>

</html>