<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<!--	<link rel="stylesheet"
		href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">-->
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
	<title>Create User Account</title>
</head>
<body>
	<p>${flash}</p>
        <div class="container section">
            <nav>
                <div class="nav-wrapper">
                  <a class="brand-logo"> Secure Banking Application</a>
                  <ul id="nav-mobile" class="right hide-on-med-and-down">
                      <li><a href="/securebank/home">Home<i class="material-icons left">home</i></a></li>
                      <li><a href="/securebank/logout">Logout<i class="material-icons left">exit_to_app</i></a></li>
                  </ul>
                </div>
            </nav>
        <div class="card-panel grey lighten-3">
	<form action="/securebank/create" method="post" id="createuser">
		<div class="row">
                <div class="input-field col s12">	
                    <label for="Name">Name:</label>
                    <input type="text" maxlength="50" name="name">
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                    <label for="ssn">SSN:</label>
                    <input type="text" maxlength="50" name="ssn">
                    </div>
                </div>    
                <div class="row">
                    <div class="input-field col s12">
                    <label for="username">Username:</label>
                    <input type="text" maxlength="50" name="username">
                    </div>
                </div>    
                <div class="row">
                    <div class="input-field col s12">
                    <label for="password">Password:</label>
                    <input type="text" maxlength="50" name="password">
                    </div>
                </div>    
                <div class="row">
                    <div class="input-field col s12">
                    <label for="address">Address:</label>
                    <input type="text" maxlength="100" name="address">
                    </div>
                </div>    
                <div class="row">
                    <div class="input-field col s12">
                    <label for="zipcode">Zipcode:</label>
                    <input type="number" maxlength="5" name="zipcode">
                    </div>
                </div>    
                <div class="row">
                    <div class="input-field col s12">
                    <label for="email">Email:</label>
                    <input type="text" maxlength="20" name="email">
                    </div>
                </div>    
                <div class="row">
                    <div class="input-field col s12">
                    <label for="gender">Gender:</label>
                    <input type="text" maxlength="1" name="gender">
                    </div>
                </div>    
                <div class="row">
                    <div class="input-field col s12">
                    <label for="phone">Phone:</label>
                    <input type="number" maxlength="10" name="phone">
                    </div>
                </div> 
                <div class="row">
                    <div class="input-field col s12">
                    <label for="threshold">Threshold:</label>
                    <input type="number" maxlength="10" name="threshold">
                    </div>
                </div>
                <div>
                <div class="row">			
                    <label for="role">Role:</label>
                        <select name="role" form="createuser">
                            <option value="MR">Merchant</option>
                            <option value="Ind">Individual</option>
                        </select>
                </div>
                </div>
            <div class="row">
                <div class="col s8">
                    <button type="submit" id="submit" class="btn waves-effect waves-light right green accent-4" type="submit">Save</button>
                </div>
                <div class="col s2">
                    <button type="reset" class="btn waves-effect waves-light right green accent-4" value="Reset">Reset</button>
                </div>
                <div class="col s2">
                    <button onclick="location.href = '/securebank/home';" class="btn waves-effect waves-light right green accent-4" type="button">Cancel</button>
                </div>
            </div>
	</form>
        </div>
        </div>
</body>
</html>