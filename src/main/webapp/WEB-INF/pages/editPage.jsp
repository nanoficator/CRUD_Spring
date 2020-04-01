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
    <title>Edit user</title>
</head>
<body>

<p><a href=/admin/table>Back to table</a></p>


<form action="/admin/edit/" method="post">
    <p>ID: <input type="text" name="id" value="${user.id}" readonly></p>
    <p>Username: <input type="text" name="username" value="${user.username}" required></p>
    <p>Password: <input type="password" name="password" value="${user.password}"></p>
    <p>Confirm password: <input type="password" name="confirmPassword" value="${user.confirmPassword}"></p>
    <p>Role:
        <c:forEach var="role" items="${allRoles}">
            <c:if test="${user.roles.contains(role) == true}">
                <input type="checkbox" name="${role.name}" value="${role.name}" id="${role.id}" checked>
            </c:if>
            <c:if test="${user.roles.contains(role) == false}">
                <input type="checkbox" name="${role.name}" value="${role.name}" id="${role.id}">
            </c:if>
            <label for="${role.id}">${role.name}</label>
        </c:forEach>
    <p><input type="submit" name="SUBMIT" value="CHANGE USER"></p>
</form>

</body>
</html>
