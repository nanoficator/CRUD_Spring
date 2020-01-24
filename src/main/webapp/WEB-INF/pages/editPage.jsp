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
    <p>First Name: <input type="text" value="${user.firstName}" name="firstName" required></p>
    <p>Second Name: <input type="text" value="${user.secondName}" name="secondName" required></p>
    <p>Username: <input type="text" value="${user.userName}" name="userName" required></p>
    <p>Password: <input type="password" value="${user.password}" name="password" required></p>
    <p>Confirm password: <input type="password" value="${user.password}" name="confirmPassword" required></p>
    <p>Age: <input type="number" value="${user.age}" name="age" required></p>
    <p>Gender:
        <input type="radio" value="${user.gender}" checked name="gender">${user.gender}
        <input type="radio" value="${agender}" name="gender">${agender}

    </p>
    <p>Change user: <input type="submit" name="SUBMIT"></p>
</form>

</body>
</html>
