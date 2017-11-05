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
        <nav>
                <div class="nav-wrapper">
                  <a class="brand-logo"> Secure Banking Application</a>
                  <ul id="nav-mobile" class="right hide-on-med-and-down">
                      <li><a href="/securebank/home">Home<i class="material-icons left">home</i></a></li>
                      <li><a href="/securebank/logout">Logout<i class="material-icons left">exit_to_app</i></a></li>
                  </ul>
                </div>
                </nav>
        <section class="card-panel grey lighten-3">
    <p>${home}</p>
    <section>
            <div class="container">
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
                <h5 class="center-align"> Modify an existing customer: </h5>
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
         <div style="display:${tier}" class="section container">
            <div class="row">
                <h5 class="center-align"> Delete existing customer: </h5>
                <div class="input-field col s12">
                    <span>
                        <label for="userid">user_id of the customer:</label>
                        <input id="userid" type="text" maxlength="250" name="userid">
                    </span>
                </div>
                <div>
                    <span>
                        <button id ="delete" class="btn waves-effect waves-light right green accent-4" type="button">
                            <i class="material-icons right">delete_forever</i> Delete
                        </button>
                    </span>
                </div>
            </div>
         </div>        
        <div class="divider"></div>
        <div class="section container">
<!--            <form action="/pending" method="post">
                                <div class="input-field">
                                    <select name="pending">
                                            <option value="trx">Pending Transaction authorization</option>
                                            <option value="mod">User details Modification</option>
                                    </select>
                                </div>
                                    <button type="submit" class="btn waves-effect waves-light right green accent-4" id="submit">Get Details</button>
                    </form>-->
            <div class="row">
                <form style="display:${tier}" action="/securebank/authorize/critical" method="get">
                        <button type="submit" class="btn waves-effect waves-light right green accent-4" id="submit">Authorize transactions</button>
                </form>
                <form action="/securebank/authorize/modify" method="get">
                        <button type="submit" class="btn waves-effect waves-light left green accent-4" id="submit">Authorize modifications</button>
                </form>
            </div>
        </div>
        <div class="divider"></div>
        <div class="section container">
            <h5 class="center-align"> Request/Transfer Money </h5> 
		<form action="/securebank/transfer" method="post">
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
        $("#delete").click(function(){
            if($("#userid").val()===""){
                alert("user_id cannot be empty");
            }
            else{
            	$.ajax({
                    type: "POST",
                    url: "/securebank/delete?id="+$("#userid").val(),
                  });
            }
         });
    </script>
</html>