<%--
  Created by IntelliJ IDEA.
  User: Mikhail Kuzivanov
  Date: 21.01.2020
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>

<a href=/admin/table>Back to table</a>

<form action="/admin/edit?id=${id}" method="post">
    <p>Username: <input type="text" value="${user.userName}" name="userName" required></p>
    <p>Password: <input type="password" value="${user.password}" name="password" required></p>
    <p>Confirm password: <input type="password" value="${user.password}" name="confirmPassword" required></p>
    <p>Change user: <input type="submit" name="SUBMIT"></p>
</form>

</body>
</html>
