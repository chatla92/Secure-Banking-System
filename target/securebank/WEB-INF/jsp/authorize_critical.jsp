<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script src='https://www.google.com/recaptcha/api.js'></script>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Welcome</title>
</head>
<body>
	<form action="/securebank/authorize/critical" method="post">
		<table>
			<tr>
				<th></th>
           		<th>Date</th>
            	<th>Receiver</th>
            	<th>Type</th>
            	<th>Amount</th>
			</tr>

			<c:forEach items="${criticalList}" var="trans">
			<tr>
				<td><input type="radio" name="id" value="${trans.get('trans_id')}"/></td>
                <td>${trans.get("date")}</td>
                <td>${trans.get("reciver")}</td>
                <td>${trans.get("type")}</td>
                <td>${trans.get("amt")}</td>
			</tr>
			</c:forEach>
		</table>
		<button type="submit" name="action" value="approve">Approve</button>
		<button type="submit" name="action" value="decline">Decline</button>
	</form>
</body>
</html>