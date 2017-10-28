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
		<span> <label for="account">Account of the Customer:</label> <input
			id="account" type="text" maxlength="250" name="account">
		</span> <span>
			<button id="modify" type="button">
				<span class="glyphicon glyphicon-pencil"></span> Modify
			</button>
		</span>
	</div>
	<div>
	<form style="display:${tier}" action="/securebank/authorize/critical" method="get">
		<button type="submit" id="submit">Authorize transactions</button>
	</form>
	<form action="/securebank/authorize/modify" method="get">
		<button type="submit" id="submit">Authorize modifications</button>
	</form>
	</div>
	<div>
		<button style="position: fixed; right: 10px; top: 5px" type="button"
			onclick="location.href = '/securebank/create';">
			<span class="glyphicon glyphicon-plus"></span> Create New User
		</button>
	</div>
	<button style="position: fixed; right: 10px; top: 5px" type="button"
		onclick="location.href = '/securebank/logout';">
		<span class="glyphicon glyphicon-log-out"></span> Log out
	</button>
	<p>${home}</p>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>${greeting}</h1>
				<p>${tagline}</p>
			</div>
		</div>
		<form action="/securebank/transfer" method="post">
			<label for="fromAcc">From:</label> <input type="text" maxlength="100"
				name="fromAcc"> <label for="toAcc">To:</label> <input
				type="text" maxlength="100" name="toAcc"> <label
				for="amount">Amount:</label> <input type="number" name="amount">

			<button type="submit" id="submit">Make the Transaction</button>
		</form>
	</section>
</body>
<script>
        $("#modify").click(function(){
          if($("#account").val()===""){
              alert("Account Number cannot be empty")
          }
          else{ 
              window.location = '/securebank/modify?id='+$("#account").val();
          }
       });
    </script>
</html>