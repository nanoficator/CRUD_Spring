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
    <title>Delete user</title>
</head>
<body>

<p>Are you sure to delete ${userName}?</p>
<form action="/admin/delete?id=${id}" method="post">
    <button>YES</button>
</form>

<form action="/admin/table" method="get">
    <button>NO</button>
</form>

</body>
</html>
