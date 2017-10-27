<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<html>

	<head>
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		<title>Create User Account</title>
	</head>

	<body>
		<form action="/securebank/otp" method="post">

			<label for="Name">OTP:</label>
			<input type="number" maxlength="50" name="otp">

			<button type="submit" id="submit" type="submit">Save</button>
		</form>
	</body>

	</html>
