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
			<div class="divider"></div>
			<div class="section">
				<div class="row">
					<section class="col s4">
						<form action="/securebank/log" method="get">
							<button type="submit"
								class="btn waves-effect waves-light left green accent-4"
								id="submit">Get Transaction Log</button>
						</form>
					</section>
					<div class="center">
						<button type="button"
							class="btn waves-effect waves-light right green accent-4"
							onclick="location.href = '/securebank/create';">
							<span class="glyphicon glyphicon-plus"></span> Create New User
						</button>
					</div>
					<div class="col s4">
						<form action="/securebank/authorize/modify" method="get">
							<button type="submit"
								class="btn waves-effect waves-light right green accent-4"
								id="submit">Authorize modifications</button>
						</form>
					</div>
				</div>
			</div>
			<div class="divider"></div>
			<div class="section container">
				<h5 class="center-align">Modify an employee's profile:</h5>
				<div class="col s4">
					<form action="/securebank/employees" method="get">
						<button type="submit"
							class="btn waves-effect waves-light right green accent-4"
							id="submit">Check for Emp List</button>
					</form>
				</div>
				<div>
					<div class="row">
						<div class="input-field col s12">
							<label for="account">Employee Id:</label> <input id="account"
								type="text" maxlength="250" name="account">
						</div>
					</div>
					<div class="row">
						<div>
							<span>
								<button id="modify" type="button"
									class="btn waves-effect waves-light right green accent-4">
									<span class="glyphicon glyphicon-pencil"></span> Modify
								</button>
							</span>
						</div>
						<div>
							<span>
								<button id="delete"
									class="btn waves-effect waves-light right green accent-4"
									type="button">
									<i class="material-icons left">delete_forever</i> Delete
								</button>
							</span>
						</div>
					</div>
				</div>
				<div class="divider"></div>
			</div>

			<div class="divider"></div>
		</div>
		<form class="col s12" action="/securebank/pii" method="post"
			id="piiform">
			<div class="section">

				<div class="row">
					<div class="input-field col s12">
						<label for="id">ID:</label> <input type="text" maxlength="250"
							name="id" class="validate" required>

					</div>
				</div>


				<!--        <div class="g-recaptcha" data-sitekey="6LczIDUUAAAAAMqd-KvvPUVKWZDi19GVWyMjbEBc"></div>-->
				<div class="section">
					<button form="piiform" type="submit"
						class="btn waves-effect waves-light right green accent-4"
						id="submit" name="customer" value="true">
						Customer<i class="material-icons right">send</i>
					</button>
					<button form="piiform" type="submit"
						class="btn waves-effect waves-light left green accent-4"
						id="submit" name="customer" value="false">
						Employee<i class="material-icons right">send</i>
					</button>
				</div>
			</div>
		</form>
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