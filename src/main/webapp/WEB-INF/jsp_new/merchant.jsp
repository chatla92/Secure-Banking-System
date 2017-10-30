<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<!--<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
            <div class="row">
                <div class="left"><h1>${greeting}</h1></div>
            </div>
            <p>${tagline}</p>
        </div>
        <div class="divider"></div>

        <div class="section container">
            <div class="row">
                <button id ="modify" class="btn waves-effect waves-light left green accent-4" type="button">
                <i class="material-icons right">edit</i> Modify profile
                </button>
            </div>
        </div>
        <div class="divider"></div>
        <div class="section container">
            <div class="row">
                <form action="/securebank/authorize/request" method="get">
                    <button type="submit" class="btn waves-effect waves-light left green accent-4" id="submit">Authorize requests</button>
                </form>  
            </div>
        </div>
        
        <div class="divider"></div>                       
        <h5 class="center-align">Get transaction history:</h5>
        <div class="section container">
            <div class="row">
                <form action="/securebank/transaction" method="post">
                        <select name="accNo">
                                <c:forEach items="${AccountList}" var="account">
                                        <c:forEach items="${account}" var="entry">
                                                <option value="${entry.key},${entry.value}">${entry.key}(${entry.value}) </option>
                                        </c:forEach>
                                </c:forEach>
                        </select>
                        <div>
                        <button type="submit" class="btn waves-effect waves-light right green accent-4" id="submit">
                            Get past transactions<i class="material-icons right">call_received</i></button>
                        </div>
                </form>
            </div>
        </div>
        <div class="divider"></div>
        <h5 class="center-align">Request money from customers:</h5>
        <div class="section container">
                <form action="/securebank/transfer" method="post">
                        <select name="fromAcc">
                                <c:forEach items="${AccountList}" var="account">
                                        <c:forEach items="${account}" var="entry">
                                                <option value="${entry.key},${entry.value}">${entry.key}(${entry.value})
                                                </option>
                                        </c:forEach>
                                </c:forEach>
                        </select>
                        <div class="row">
                            <div class="input-field col s12">
                            <label for="cc">Credit Card of the customer:</label>
                            <input type="text" maxlength="100" name="cc"> 
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <label for="cvv">CVV:</label>
                                <input type="number" name="cvv"> 
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">                               
                                <label for="amount">Amount:</label>
                                <input type="number" name="amount">
                            </div>
                        </div>
                        <div class="row">
                            <button type="submit" class="btn waves-effect waves-light right green accent-4" id="submit" name="action" value="merchant">
                                Request<i class="material-icons right">call_received</i></button>
                        </div>
                </form>
            </div>
	</section>
        </section>
        </div>
</body>
<script>
		$("#modify").click(function() {
			window.location = '/securebank/modify';
		});
	</script>

</html>
