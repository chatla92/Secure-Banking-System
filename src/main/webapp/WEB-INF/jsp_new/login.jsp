<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <script src='https://www.google.com/recaptcha/api.js'></script>
<!--    <link rel="stylesheet"
		href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
    <script src='https://www.google.com/recaptcha/api.js?onload=reCaptchaCallback&render=explicit'></script>
          
	<title>Login</title>
        <script>
            $(document).ready(function() {
                $('select').material_select();
            });
        </script>
</head>
<body>
    <div class="section">
    <h5 class="teal-text center">Login: Enter your details</h5>    
    <div class="row container">
        <form class="col s12" action="/securebank/login" method="post" id="formtemp" onsubmit="check_if_capcha_is_filled()">
        <div class="section">
            <p class="center-align red-text">${flash}</p>
          <div class="row">
            <div class="input-field col s12">
                <input type="text" maxlength="250" name="name" class="validate" required>
            <label for="name">Name:</label>
            </div>
          </div>
          <div class="row">
          <div class="input-field col s12">
              <input type="password" maxlength="64" name="password" class="validate" required>
            <label for="password">Password:</label>
          </div>
          </div>
          <div class="g-recaptcha" data-callback="capcha_filled" data-expired-callback="capcha_expired" data-sitekey="6LczIDUUAAAAAMqd-KvvPUVKWZDi19GVWyMjbEBc"></div>
<!--        <div class="g-recaptcha" data-sitekey="6LczIDUUAAAAAMqd-KvvPUVKWZDi19GVWyMjbEBc"></div>-->
          <div class="section">
            <select name="usertype" form="formtemp">
                      <option value="e">Customer</option>
                      <option value="i">Employee</option>
              </select>
<!--            <ul id="usertype" name="usertype" form="formtemp" class="dropdown-content">
                      <option value="e">Customer</option>
                      <option value="i">Employee</option>
              </ul>               -->
      <!--      <button type="submit" class="btn btn-default" id="submit" type="submit">
              <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>Log in</button>-->
              <div class="row">
              <button class="btn waves-effect waves-light left green accent-4" onclick="window.location='/securebank/forgotpassword';">Forgot Password?</button>
            <button type="submit" class="btn waves-effect waves-light right green accent-4" id="submit" type="submit">
                Log in<i class="material-icons right">send</i></button>
              </div>
          </div>
        </div>
        </form>
    </div>
    </div>
</body>

<script>
    var allowSubmit=false;
    function capcha_filled(e){
        if(e)
            allowSubmit=true;
        allowSubmit=false;
    }
    function capcha_expired(){
        allowSubmit=false;
    }
    function check_if_capcha_is_filled(){
        if(allowSubmit===true) return true;
        //alert('Fill in the capcha!');
    }
</script>
</html>