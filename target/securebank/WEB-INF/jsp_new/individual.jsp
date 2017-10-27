<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
<title>Welcome</title>
</head>

<body>
	<div class="container section">
		<!--			<span>
				<button id ="modify" class="btn waves-effect waves-light green accent-4" type="button">
				<i class="material-icons right">edit</i> Modify
                                </button>
			</span>-->
	</div>
	<div class="container section">
		<!--                    <div class="row">
			<button class="btn waves-effect waves-light right green accent-4" type="button" onclick="location.href = '/securebank/logout';">
				<span class="glyphicon glyphicon-log-out">
				</span> Log out
			</button>
                    </div>-->
		<p>${home}</p>
		<div>
			<section>
				<div class="jumbotron">
					<div class="container">
						<div class="row">
							<div class="left">
								<h1>${greeting}</h1>
							</div>
							<div class="right">
								<button
									class="btn waves-effect waves-light right green accent-4"
									type="button" onclick="location.href = '/securebank/logout';">
									<span class="glyphicon glyphicon-log-out"> </span> Log out
								</button>
							</div>
						</div>
						<p>${tagline}</p>
					</div>

					<div class="divider"></div>
					<div class="section container">
						<div>
							<button id="modify"
								class="btn waves-effect waves-light left green accent-4"
								type="button">
								<i class="material-icons right">edit</i> Modify
							</button>
						</div>

						<form action="/securebank/transaction" method="post">
							<div>
								<select name="accNo">
									<c:forEach items="${AccountList}" var="account">
										<c:forEach items="${account}" var="entry">
											<option value="${entry.key},${entry.value}">${entry.key}(${entry.value}) </option>
										</c:forEach>
									</c:forEach>
								</select>
							</div>
							<div>
								<button type="submit"
									class="btn waves-effect waves-light right green accent-4"
									id="submit">
									Get past transactions<i class="material-icons right">call_received</i>
								</button>
							</div>
						</form>
					</div>
					<div class="divider"></div>
					<form action="/securebank/transfer" method="post">
						<select name="fromAcc">
							<c:forEach items="${AccountList}" var="account">
								<c:forEach items="${account}" var="entry">
									<option value="${entry.key},${entry.value}">${entry.key}(${entry.value}) </option>
								</c:forEach>
							</c:forEach>
						</select>
						<h5 class="center-align">Request/Transfer Money</h5>
						<div class="section container">
							<div class="row">
								<div class="input-field col s12">
									<label for="email">Email of the other party:</label> <input
										type="text" maxlength="100" name="email">
								</div>
							</div>
							<div class="row">
								<div class="input-field col s12">
									<label for="amount">Amount:</label> <input type="number"
										name="amount">
								</div>
							</div>
							<div>
								<button type="submit"
									class="btn waves-effect waves-light right green accent-4"
									id="submit" name="action" value="transfer">
									Transfer<i class="material-icons right">call_made</i>
								</button>
							</div>
							<div>
								<button type="submit"
									class="btn waves-effect waves-light left green accent-4"
									id="submit" name="action" value="request">
									Request<i class="material-icons right">call_received</i>
								</button>
							</div>
						</div>
					</form>
				</div>
			</section>
		</div>
	</div>
</body>
<script>
	$("#modify").click(function() {
		window.location = '/securebank/modify';
	});
</script>

</html>
