<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mikhail Kuzivanov
  Date: 22.01.2020
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User info</title>
</head>
<body>

<p>ID: ${user.id}</p>
<p>Username: ${user.username}</p>
<p>Roles:
    <ul>
        <c:forEach var="role" items="${user.roles}">
            <li>${role.name}</li>
        </c:forEach>
    </ul>
</p>

<p>
<c:forEach var="role" items="${user.roles}">
    <c:if test="${role.equals(allRoles.get(0))}">
        <a href=/admin/table>Table</a>
    </c:if>
    <c:if test="${role.equals(allRoles.get(1))}">
        <a href=/logout>LOGOUT</a>
    </c:if>
</c:forEach>
</p>

</body>
</html>
