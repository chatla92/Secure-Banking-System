<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<!--	<link rel="stylesheet"
		href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">-->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
    <script src='https://www.google.com/recaptcha/api.js?onload=reCaptchaCallback&render=explicit'></script>
	<title>Modify</title>
</head>
<body>
    <div class="container section">
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
	<form action="/securebank/modify" method="post">
            <div class="row">
                <div class="input-field col s12">
		<input type="text" maxlength="50" name="username" value="${username}">
                <label for="username">Username</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
		<label for="address">Address:</label>
		<input type="text" maxlength="100" name="address" value="${address}">
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
		<label for="zipcode">Zipcode:</label>
		<input type="number" maxlength="5" name="zipcode" value="${zipcode}">
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
		<label for="email">Email:</label>
		<input type="text" maxlength="20" name="email" value="${email}">
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
		<label for="gender">Gender:</label>
		<input type="text" maxlength="1" name="gender" value="${gender}">
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
		<label for="phone">Phone:</label>
		<input type="number" maxlength="10" name="phone" value="${phone}">
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
		<label for="threshold">Threshold:</label>
		<input type="number" maxlength="10" name="threshold" value="${threshold}">
                </div>
            </div>
            <div class="row">
                <div class="col s8">
                    <button type="submit" class="btn waves-effect waves-light right green accent-4" id="submit" type="submit">Save</button>
                </div>
                <div class="col s2">
                    <button id="reset" class="btn waves-effect waves-light right green accent-4" type="reset" value="Reset">Reset</button>
                </div>
                <div class="col s2">
		<button onclick="location.href = '/securebank/home';" class="btn waves-effect waves-light right green accent-4" type="button">Cancel</button>
                </div>
            </div>
	</form>
        </div>
    </div>         
		<script>
		$("#reset").click(function(){
			var fields = window.queryselectorall(input);
			for(var field = 0; field<fields.length;field++){
				fields[field].value="";
			}
		});
	</script>
</body>
</html>