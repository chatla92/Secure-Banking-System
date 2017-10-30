<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
    <script src='https://www.google.com/recaptcha/api.js?onload=reCaptchaCallback&render=explicit'></script>
    <title>Transactions</title>
</head>
<body>
<div class="container">
    <nav>
                <div class="nav-wrapper">
                  <a class="brand-logo"> Secure Banking Application</a>
                  <ul id="nav-mobile" class="right hide-on-med-and-down">
                      <li><a href="/securebank/home">Home<i class="material-icons left">home</i></a></li>
                      <li><a href="/securebank/logout">Logout<i class="material-icons left">exit_to_app</i></a></li>
                  </ul>
                </div>
    </nav>
    <h4 class="center-align">Complete Transaction Log</h4>
    <div class="section">
    <table class="striped">
        <tr>
            <th>Date</th>
            <th>Sender</th>
            <th>Receiver</th>
            <th>Type</th>
            <th>Amount</th>
        </tr>
        <c:forEach items="${transactionlist}" var="TransactionList">
            <tr>
                <td>${TransactionList.get("date")}</td>
                <td>${TransactionList.get("sender")}</td>
                <td>${TransactionList.get("reciver")}</td>
                <td>${TransactionList.get("type")}</td>
                <td>${TransactionList.get("amt")}</td>
            </tr>
        </c:forEach>
    </table>
    </div>

    <div class="row">
    <button class="btn waves-effect waves-light right green accent-4" type="button"
    onclick="location.href = '/securebank/home';">
        Back<i class="material-icons right">arrow_back</i> 
    </button>
                <div>
            <button class="btn waves-effect waves-light left green accent-4" type="button"
                onclick="window.print();">
                    Print <i class="material-icons right">local_printshop</i> 
            </button>
        </div>
    </div>
</div>
</body>
</html>