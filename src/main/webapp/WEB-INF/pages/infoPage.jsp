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

<p><a href=/logout>LOGOUT</a></p>
<p><a href=/admin/table>Table</a> (Only for admins)</p>

<p>ID: ${user.id}</p>
<p>Username: ${user.username}</p>
<p>Roles:
    <ul>
        <c:forEach var="role" items="${user.roles}">
            <li>${role.name}</li>
        </c:forEach>
    </ul>
</p>

</body>
</html>
