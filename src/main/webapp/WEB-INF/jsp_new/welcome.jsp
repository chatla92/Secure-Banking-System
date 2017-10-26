<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<!--<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
    <script src='https://www.google.com/recaptcha/api.js?onload=reCaptchaCallback&render=explicit'></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<title>Welcome</title>
</head>
<body>
	<div class="container section jumbotron">
	<div class="row">
                <div class="input-field col s12">
		<span style=${dis1}>
			<label for="account">Account of the Customer:</label>
                        <input id="account" type="text" maxlength="250" name="account">
	    </span>
                </div>
	    <span>
			<button id ="modify" type="button">
				<i class="material-icons right">edit</i> Modify
			</button>
		</span>
	</div>
	<div>
<!--	<button style='${dis2}' type="button"
		onclick="location.href = '/securebank/create';">
		<span class="glyphicon glyphicon-plus"></span> Create New User
	</button>-->
        <span style=${dis2}>
	<button type="button"
		onclick="location.href = '/securebank/create';">
		<span class="glyphicon glyphicon-plus"></span> Create New User
	</button>   
        </span>
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
        </div>
</body>
	<script>
		var acc = "${dis1}";
		$("#modify").click(function(){
			if(acc!=="display:None") {
				if($("#account").val()===""){
					alert("Account Number cannot be empty");
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