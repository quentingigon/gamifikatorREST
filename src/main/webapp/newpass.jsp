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
        <form action="newpass" method="post" class="profile__form">
            <div class="profile__fields">
                <div class="field">
                    <input type="email" id="email" name="email" class="input" required pattern=.*\S.* />
                    <label for="email" class="label">Email</label>
                </div>
                <div class="field">
                    <input type="password" id="new_password" name="new_password" class="input" required pattern=.*\S.* />
                    <label for="new_password" class="label">New Password</label>
                </div>
                <div class="profile__footer">
                    <input type="submit" id="bSubmit" value="Change password" class="btn">
                </div>
                <div>
                    <if test="${not empty newpass_error}">
                        <p >${newpass_error}</p>
                    </if>
                </div>
            </div>

        </form>

    </div>

</div>

<script  src="elastic/js/index.js"></script>

</body>

</html>