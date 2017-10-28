<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <link rel="stylesheet"
		href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
	<title>Welcome</title>
</head>
<body>
<button onclick="window.location='/securebank/forgotpassword';">Forgot Password?</button>
	<form action="/securebank/login" method="post" id="formtemp">
    <div class="form-group">
	<p>${flash}</p>
      <label for="name">Name:</label>
      <input type="text" maxlength="250" name="name">

      <label for="password">Password:</label>
      <input type="text" maxlength="64" name="password">
      <select name="usertype" form="formtemp">
  		<option value="e">Customer</option>
  		<option value="i">Employee</option>
	  </select>
      <button type="submit" class="btn btn-default" id="submit" type="submit">
        <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>Log in</button>
    </div>
        <div class="g-recaptcha" data-sitekey="6LczIDUUAAAAAMqd-KvvPUVKWZDi19GVWyMjbEBc"></div>
    </form>
</body>
</html>