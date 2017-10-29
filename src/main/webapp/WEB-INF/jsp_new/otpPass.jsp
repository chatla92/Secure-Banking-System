<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<html>

	<head>
<!--		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">-->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
    <script src='https://www.google.com/recaptcha/api.js?onload=reCaptchaCallback&render=explicit'></script>
		<title>Create User Account</title>
	</head>

	<body>
        <div class="section container">
		<div class="card-panel grey lighten-3">            
                <h5 class="center-align"> You have 10 minutes to type in your OTP. If you are past 10 minutes or if validation fails, you will have to go back. 
                    If you haven't received email then you might not have an account with us</h5>
		<form action="/securebank/forgototp" method="post">
                    <div class="row">
                        <div class="input-field col s12">
			<label for="otp">OTP:</label>
			<input type="text" maxlength="50" name="otp">
                        </div>
                    </div>
                    <div class="row">
			<button type="submit" class="btn waves-effect waves-light right green accent-4" id="submit" type="submit">Save</button>
                    </div>
		</form>
	</body>

	</html>
