<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<!--<link rel="stylesheet"
    href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
    <script src='https://www.google.com/recaptcha/api.js?onload=reCaptchaCallback&render=explicit'></script>
    <script>
            $(document).ready(function() {
                $('select').material_select();
            });
        </script>
<title>Welcome</title>
</head>
<body>
    <div class="section container">
        <section class="card-panel grey lighten-3">
    <p>${home}</p>
    <section>
            <div class="container">
                <div class="row">
                    <button class="btn waves-effect waves-light right green accent-4" type="button"
                        onclick="location.href = '/securebank/logout';">
                        <span class="glyphicon glyphicon-log-out"></span> Log out
                    </button>
                </div>
                <h1>${greeting}</h1>
                <p>${tagline}</p>
            </div>
        <div class="row">
<!--            <div class="col s8">
            <button class="btn waves-effect waves-light right green accent-4" type="button"
                onclick="location.href = '/securebank/create';">
                <i class="material-icons right">edit</i> Create New User
            </button>
            </div>
            <div class="col s2">
            <button class="btn waves-effect waves-light right green accent-4" type="button"
                onclick="location.href = '/securebank/logout';">
                <span class="glyphicon glyphicon-log-out"></span> Log out
            </button>
            </div>-->
        </div>   
        <div class="section container">    
        <div class="row">
                <button class="btn waves-effect waves-light right green accent-4" type="button"
                    onclick="location.href = '/securebank/create';">
                    <i class="material-icons right">edit</i> Create New User
                </button>
        </div>
        </div>
        <div class="divider"></div>
        <div class="section container">
            <div class="row">
                <h4 class="center-align"> Modify an existing customer: </h4>
                <div class="input-field col s12">
                    <span>
                        <label for="account">Account of the Customer:</label>
                        <input id="account" type="text" maxlength="250" name="account">
                    </span>
                </div>
                <div>
                    <span>
                        <button id ="modify" class="btn waves-effect waves-light right green accent-4" type="button">
                            <i class="material-icons right">edit</i> Modify
                        </button>
                    </span>
                </div>
            </div>
         </div>    
        <div class="divider"></div>
        <div class="section container">
            <form action="/pending" method="post">
                                <div class="input-field">
                                    <select name="pending">
                                            <option value="trx">Pending Transaction authorization</option>
                                            <option value="mod">User details Modification</option>
                                    </select>
                                </div>
                                    <button type="submit" class="btn waves-effect waves-light right green accent-4" id="submit">Get Details</button>
                    </form>
        </div>
        <div class="divider"></div>
        <div class="section container">
		<form action="/transfer" method="post">
		<div class="input-field col s12">
                    <label for="fromAcc">From Account Number:</label>
                    <input type="text" maxlength="100" name="fromAcc" required>
                </div>
                <div class="input-field col s12">
				<label for="toAcc">To Account Number:</label>
				<input type="text" maxlength="100" name="toAcc" required>
                </div>
                <div class="input-field col s12">
				<label for="amount">Amount:</label>
                                <input type="number" name="amount" required>
                </div>		
				<button type="submit" class="btn waves-effect waves-light right green accent-4" id="submit">Make the Transaction</button>
		</form>
        </div>
    </section>
    </section>

    </div>
</body>
    <script>
        $("#modify").click(function(){
          if($("#account").val()===""){
              alert("Account Number cannot be empty");
          }
          else{ 
              window.location = '/securebank/modify?id='+$("#account").val();
          }
       });
    </script>
</html>