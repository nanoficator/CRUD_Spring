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

    <form action="/admin/edit/user?id=${id}" method="post">
        <p>First Name: <input type="text" value="${firstName}" name="firstName"></p>
        <p>Second Name: <input type="text" value="${secondName}" name="secondName"></p>
        <p>Username: <input type="text" value="${userName}" name="userName"></p>
        <p>Password: <input type="password" value="${password}" name="password"></p>
        <p>Confirm password: <input type="password" value="${password}" name="confirmPassword"></p>
        <p>Age: <input type="number" value="${age}" name="age"></p>
        <p>Gender:
            <input type="radio" value="${gender}" checked name="gender">${gender}
            <input type="radio" value="${agender}" name="gender">${agender}

        </p>
        <p>Role:
            <input type="radio" value="${role}" checked name="role">${role}
            <input type="radio" value="${arole}" name="role">${arole}

        </p>
        <p>Change user: <input type="submit" name="SUBMIT"></p>
    </form>

</body>
</html>
