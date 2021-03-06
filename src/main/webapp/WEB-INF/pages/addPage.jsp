<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <p>Username: <input type="text" name="username" value="${user.username}" required></p>
    <p>Password: <input type="password" name="password" value="${user.password}" required></p>
    <p>Confirm password: <input type="password" name="confirmPassword" value="${user.confirmPassword}" required></p>
    <p>Role:
        <c:forEach var="role" items="${allRoles}">
            <input type="checkbox" name="${role.name}" value="${role.name}" id="${role.id}">
            <label for="${role.id}">${role.name}</label>
        </c:forEach>
    </p>
    <p><input type="submit" name="SUBMIT" value="ADD USER"></p>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>

</body>
</html>