<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<html>

	<head>
<!--		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">-->
                    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
                    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
                    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
                    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
                    <script src='https://www.google.com/recaptcha/api.js?onload=reCaptchaCallback&render=explicit'></script>
		<title>Forgot Password</title>
	</head>

	<body>
            <div class="section container">
                <nav>
                <div class="nav-wrapper">
                  <a class="brand-logo"> Secure Banking Application</a>
                  <ul id="nav-mobile" class="right hide-on-med-and-down">
                      <li><a href="/securebank/home">Home<i class="material-icons left">home</i></a></li>
                      <li><a href="/securebank/logout">Logout<i class="material-icons left">exit_to_app</i></a></li>
                  </ul>
                </div>
                </nav>
                <div class="card-panel grey lighten-3">
                    <p>${flash}</p>
                    <form action="/securebank/forgotpassword" method="post">
                        <div class="row">
                            <div class="input-field col s12">	
                            <label for="email">Email:</label>
                            <input type="text" maxlength="50" name="email">
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">	
                            <label for="new_pass">New Password:</label>
                            <input type="password" maxlength="50" name="new_pass">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s10">
                                <button type="submit" class="btn waves-effect waves-light right green accent-4" id="submit" type="submit">Save</button>
                            </div>
                            <div class="col s2">
                                <button onclick="location.href = '/securebank/login';" class="btn waves-effect waves-light right green accent-4" type="button">Cancel</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
	</body>

	</html>
