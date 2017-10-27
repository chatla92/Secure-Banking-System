<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<html>

	<head>
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		<title>Create User Account</title>
	</head>

	<body>
	<p>If you haven't received email then you might not have an account with us</p>
		<form action="/securebank/forgototp" method="post">

			<label for="otp">OTP:</label>
			<input type="text" maxlength="50" name="otp">

			<button type="submit" id="submit" type="submit">Save</button>
		</form>
	</body>

	</html>
