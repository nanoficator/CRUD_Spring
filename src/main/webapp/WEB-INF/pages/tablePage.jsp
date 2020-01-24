<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mikhail Kuzivanov
  Date: 21.01.2020
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Table</title>
</head>
<body>

<table border="0">
    <tr>
        <td>
            <table border="1">

                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Second Name</th>
                    <th>Username</th>
                    <th>Age</th>
                    <th>Gender</th>
                    <th>Options</th>
                </tr>
                <c:forEach var="user" items="${allUsers}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.secondName}</td>
                    <td>${user.userName}</td>
                    <td>${user.age}</td>
                    <td>${user.gender}</td>
                    <td><a href=/admin/delete?id=${user.id}>Delete</a> / <a href=/admin/edit?id=${user.id}>Edit</a> / <a href=/user/info?id=${user.id}>Info</a></td>
                </tr>
                </c:forEach>
            </table>
        </td>
    </tr>

    <tr>
        <td>
            <table align="center" border="0">
                <th><a href="/admin/delete/all">CLEAR DATA BASE</a></th>
                <th><a href="/admin/add">ADD NEW USER</a></th>
            </table>
        </td>
    </tr>

</table>

</body>
</html>
