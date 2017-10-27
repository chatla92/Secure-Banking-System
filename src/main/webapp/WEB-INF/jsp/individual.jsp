<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Welcome</title>
</head>

<body>
	<div>
		<span>
			<button id="modify" type="button">
				<span class="glyphicon glyphicon-pencil"></span> Modify
			</button>
		</span>
	</div>

	<button style="position: fixed; right: 10px; top: 5px" type="button"
		onclick="location.href = '/securebank/logout';">
		<span class="glyphicon glyphicon-log-out"> </span> Log out
	</button>
	<p>${home}</p>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>${greeting}</h1>
				<p>${tagline}</p>
			</div>

			<form action="/securebank/authorize/request" method="get">
				<button type="submit" id="submit">Authorize requests</button>
			</form>

			<form action="/securebank/transaction" method="post">
				<select name="accNo">
					<c:forEach items="${AccountList}" var="account">
						<c:forEach items="${account}" var="entry">
							<option value="${entry.key},${entry.value}">${entry.key}(${entry.value})
							</option>
						</c:forEach>
					</c:forEach>
				</select>
				<button type="submit" id="submit">Get transactions</button>
			</form>

			<form action="/securebank/transfer" method="post">
				<select name="fromAcc">
					<c:forEach items="${AccountList}" var="account">
						<c:forEach items="${account}" var="entry">
							<option value="${entry.key},${entry.value}">${entry.key}(${entry.value})
							</option>
						</c:forEach>
					</c:forEach>
				</select> <label for="email">Email of the other party:</label> <input
					type="text" maxlength="100" name="email"> <label
					for="amount">Amount:</label> <input type="number" name="amount">

				<button type="submit" id="submit" name="action" value="transfer">Transfer</button>
				<button type="submit" id="submit" name="action" value="request">Request</button>
			</form>
		</div>
	</section>
</body>
<script>
		$("#modify").click(function() {
			window.location = '/securebank/modify';
		});
	</script>

</html>
