<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Welcome</title>
</head>
<body>
	
	<div>
		<span style=${dis1}>
			<label for="account">Account of the Customer:</label>
	    	<input id="account" type="text" maxlength="250" name="account">
	    </span>
	    <span>
			<button id ="modify" type="button">
				<span class="glyphicon glyphicon-pencil"></span> Modify
			</button>
		</span>
	</div>
	<div>
	<button style=${dis2} type="button"
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
	</section>
</body>
	<script>
		var acc = "${dis1}";
		$("#modify").click(function(){
			if(acc!=="display:None") {
				if($("#account").val()===""){
					alert("Account Number cannot be empty")
				}
				else{ 
					window.location = '/securebank/modify?id='+$("#account").val();
				}
			}
			else{ 
				window.location = '/securebank/modify';
			}
		});
	</script>
</html>