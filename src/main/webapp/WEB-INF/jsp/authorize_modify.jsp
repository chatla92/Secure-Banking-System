<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script src='https://www.google.com/recaptcha/api.js'></script>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Welcome</title>
</head>
<body>
	<form action="/securebank/authorize/modify" method="post">
		<table>
			<tr>
				<th>Username</th>
				<th>E-Mail</th>
				<th>Address</th>
				<th>Zipcode</th>
				<th>Gender</th>
				<th>Contact Number</th>
			</tr>
			<c:forEach items="${modifylist}" var="ModifyList">
				<tr>
					<td><input type="radio" name="modify"
						value="${ModifyList.get('user_name')}" /></td>
					<td>${ModifyList.get("user_name")}</td>
					<td>${ModifyList.get("email")}</td>
					<td>${ModifyList.get("address")}</td>
					<td>${ModifyList.get("zipcode")}</td>
					<td>${ModifyList.get("gender")}</td>
					<td>${ModifyList.get("contact_no")}</td>
				</tr>

			</c:forEach>
		</table>
		<button type="submit" class="btn btn-default" name="action"
			value="approve">Approve</button>
		<button type="submit" class="btn btn-default" name="action"
			value="decline">Decline</button>
	</form>
</body>
</html>