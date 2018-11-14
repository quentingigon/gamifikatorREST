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
            <img src="https://api.ning.com/files/*54juLYOTrMdu4eBaIkD5urGoLY-RZV7WCPaEMInPMnHNVM-k0nOmyGbBgR0pPONCf-vv7xrdDzY2efL-2SoM6jbvSFWFSn4/Gamification_head.png" alt="Avatar" />
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


                    <span id="error"></span>
                </div>
            </div>
        </form>
    </div>
</div>



<script  src="elastic/js/index.js"></script>




</body>

</html>