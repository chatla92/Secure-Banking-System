<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<html>

	<head>
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		<title>Create User Account</title>
	</head>

	<body>
	<p>${flash}</p>
		<form action="/securebank/forgotpassword" method="post">

			<label for="email">Email:</label>
			<input type="text" maxlength="50" name="email">
			
			<label for="new_pass">New Password:</label>
			<input type="text" maxlength="50" name="new_pass">

			<button type="submit" id="submit" type="submit">Save</button>
		</form>
	</body>

	</html>
