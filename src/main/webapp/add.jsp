<%--
  Created by IntelliJ IDEA.
  User: DEEP PANCHAL
  Date: 13-04-2026
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="EmployeeServlet" method="post">
    Name: <input type="text" name="name"><br>
    Email: <input type="text" name="email"><br>
    Department: <input type="text" name="department"><br>
    <input type="submit" value="Add Employee">
</form>

<a href="EmployeeServlet">View Employees</a>
</body>
</html>
