<%--
  Created by IntelliJ IDEA.
  User: Mikhail Kuzivanov
  Date: 22.01.2020
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Auth user</title>
</head>
<body>

<form action="/login" method="post">
    <p>Username: <input type="text" name="userName" required></p>
    <p>Password: <input type="password" name="password" required></p>
    <p><input type="submit" value="SIGN IN"></p>
</form>

</body>
</html>
