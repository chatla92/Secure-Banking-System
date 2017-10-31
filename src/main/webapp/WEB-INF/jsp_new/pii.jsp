<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<!--<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
<script
	src='https://www.google.com/recaptcha/api.js?onload=reCaptchaCallback&render=explicit'></script>
<script>
	$(document).ready(function() {
		$('select').material_select();
	});
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Welcome</title>
</head>
<body>
	<div class="section container">
		<nav>
			<div class="nav-wrapper">
				<a class="brand-logo"> Secure Banking Application</a>
				<ul id="nav-mobile" class="right hide-on-med-and-down">
					<li><a href="/securebank/home">Home<i
							class="material-icons left">home</i></a></li>
					<li><a href="/securebank/logout">Logout<i
							class="material-icons left">exit_to_app</i></a></li>
				</ul>
			</div>
		</nav>
		<div class="card-panel grey lighten-3">
			<p>${home}</p>
			<div class="container">
				<div class="row">
					<h1>${greeting}</h1>
					<p>${tagline}</p>
				</div>
			</div>
		</div>
		<div class="row">
        <div class="col s12 m6">
          <div class="card blue-grey darken-1">
            <div class="card-content white-text">
              <span class="card-title">Card Title</span>
              <p>${name}<br>${SSN}</p>
            </div>
          </div>
        </div>
      </div>
	</div>		
</body>
<script>
	$("#modify").click(function() {
		if ($("#account").val() === "") {
			alert("Account Number cannot be empty")
		} else {
			window.location = '/securebank/modify?id=' + $("#account").val();
		}
	});
	$("#delete").click(function() {
		if ($("#account").val() === "") {
			alert("user_id cannot be empty");
		} else {
			$.ajax({
				type : "POST",
				url : "/securebank/delete?id=" + $("#account").val(),
			});
		}
	});
</script>
</html>