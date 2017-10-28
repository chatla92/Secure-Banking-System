<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<link rel="stylesheet"
		href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
	<title>Create User Account</title>
</head>
<body>
	<p>${flash}</p>
	<form action="/securebank/create" method="post" id="createuser">
			
		<label for="Name">Name:</label>
		<input type="text" maxlength="50" name="name">
			
		<label for="ssn">SSN:</label>
		<input type="text" maxlength="50" name="ssn">
		
		<label for="username">Username:</label>
		<input type="text" maxlength="50" name="username">

		<label for="password">Password:</label>
		<input type="text" maxlength="50" name="password">

		<label for="address">Address:</label>
		<input type="text" maxlength="100" name="address">

		<label for="zipcode">ZipCode:</label>
		<input type="number" maxlength="5" name="zipcode">

		<label for="email">Email:</label>
		<input type="text" maxlength="20" name="email">

		<label for="gender">Gender:</label>
		<input type="text" maxlength="1" name="gender">

		<label for="phone">Phone:</label>
		<input type="number" maxlength="10" name="phone">
							
		<label for="role">Role:</label>
		 <select name="role" form="createuser">
  			<option value="Tier1">Tier1</option>
  			<option value="Tier2">Tier2</option>
  			<option value="IA">Admin</option>
	  	</select>
		
		<button type="submit" id="submit" type="submit">Save</button>
		<button type="reset" value="Reset">Reset</button>
		<button onclick="location.href = '/securebank/home';" type="button">Cancel</button>
	</form>
</body>
</html>