<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Transactions</title>
        <script src='https://www.google.com/recaptcha/api.js'></script>
<!--    <link rel="stylesheet"
		href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
    <script src='https://www.google.com/recaptcha/api.js?onload=reCaptchaCallback&render=explicit'></script>
</head>
<body>
    <table class="striped">
        <tr>
            <th>Date</th>
            <th>Receiver</th>
            <th>Type</th>
            <th>Amount</th>
        </tr>
        <c:forEach items="${Pendingtransactionlist}" var="TransactionList">
            <tr>
                <td>${TransactionList.get("date")}</td>
                <td>${TransactionList.get("reciver")}</td>
                <td>${TransactionList.get("type")}</td>
                <td>${TransactionList.get("amt")}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>