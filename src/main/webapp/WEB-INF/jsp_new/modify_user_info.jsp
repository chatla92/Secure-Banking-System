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
	<title>Welcome</title>
</head>
<body>
    <table class="striped">
        <form action="/securebank/modify" method="post">
        <tr>
            <th>Username</th>
            <th>E-Mail</th>
            <th>Address</th>
            <th>Zipcode</th>
            <th>Gender</th>
            <th>Contact Number</th>
        </tr>
        <c:forEach items="${modifylist}" var="ModifyList">
            <tr>
                <td>${ModifyList.get("user_name")}</td>
                <td>${ModifyList.get("email")}</td>
                <td>${ModifyList.get("address")}</td>
                <td>${ModifyList.get("zipcode")}</td>
                <td>${ModifyList.get("gender")}</td>
                <td>${ModifyList.get("contact_no")}</td>
            </tr>
        </c:forEach>
           <c:forEach items="${modifylist}" var="ModifyList">
               <input type="radio" name="trans" id= "${ModifyList.get("user_name")}" value="${ModifyList.get("user_name")}">
            <label for="${ModifyList.get("user_name")}">
                <tr>
                <td>${ModifyList.get("user_name")}</td>
                <td>${ModifyList.get("email")}</td>
                <td>${ModifyList.get("address")}</td>
                <td>${ModifyList.get("zipcode")}</td>
                <td>${ModifyList.get("gender")}</td>
                <td>${ModifyList.get("contact_no")}</td>
                </tr>
            </label>
        </c:forEach>
        <div class="row">
            <div class="col s10">   
            <button type="submit" class="btn waves-effect waves-light right green accent-4" name="action" value="approve"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>Approve</button>
            </div>
            <div class="col s2">
                <button type="submit" class="btn waves-effect waves-light right green accent-4" name="action" value="decline"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>Decline</button>
            </div>
        </div>
     </form>
    </table>
</body>
</html>