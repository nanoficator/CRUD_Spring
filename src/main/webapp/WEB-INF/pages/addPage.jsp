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
    <title>Add user</title>
</head>
<body>

<a href=/admin/table>Back to table</a>

<form action="/admin/add/" method="post">
    <p>First Name: <input  type="text" name="firstName" value="${user.firstName}" required title="Input first name"></p>
    <p>Second Name: <input type="text" name="secondName" value="${user.secondName}" required></p>
    <p>Username: <input type="text" name="userName" value="${user.userName}" required></p>
    <p>Password: <input type="password" name="password" value="${user.password}" required></p>
    <p>Confirm password: <input type="password" name="confirmPassword" value="${user.password}" required></p>
    <p>Age: <input  type="number" name="age" value="${user.age}" required></p>
    <p>Gender:
        <input type="radio" value="male" name="gender" required>Male
        <input type="radio" value="female" name="gender" required>Female
    </p>
    <p>Create new user: <input type="submit" name="SUBMIT"></p>
</form>

</body>
</html>
