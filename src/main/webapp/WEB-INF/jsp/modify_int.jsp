<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<link rel="stylesheet"
		href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
	<title>Modify</title>
</head>
<body>
	<form action="/securebank/modify" method="post">
		<label for="username">Username:</label>
		<input type="text" maxlength="50" name="username" readonly="readonly" value="${username}">

		<label for="address">Address:</label>
		<input type="text" maxlength="100" name="address" value="${address}">

		<label for="zipcode">Description:</label>
		<input type="number" maxlength="5" name="zipcode" value="${zipcode}">

		<label for="email">Email:</label>
		<input type="text" maxlength="20" name="email" value="${email}">

		<label for="gender">Gender:</label>
		<input type="text" maxlength="1" name="gender" value="${gender}">

		<label for="phone">Phone:</label>
		<input type="number" maxlength="10" name="phone" value="${phone}">
		
		<label for="threshold">Threshold:</label>
		<input type="number" maxlength="10" name="threshold" value="${threshold}">
		
		<button type="submit" id="submit" type="submit">Save</button>
		
		<button id="reset" type="reset" value="Reset">Reset</button>
		
		<button onclick="location.href = '/securebank/home';" type="button">Cancel</button>
	</form>
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