<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.employeeapp.model.Employee" %>

<%
    Employee emp = (Employee) request.getAttribute("emp");
%>

<html>
<head>
    <title>Edit Employee</title>
</head>
<body>

<h2>Edit Employee</h2>

<form action="EmployeeServlet" method="post">

    <!-- Hidden fields -->
    <input type="hidden" name="id" value="<%= emp.getId() %>">
    <input type="hidden" name="action" value="update">

    Name: <input type="text" name="name" value="<%= emp.getName() %>" required><br>
    Email: <input type="email" name="email" value="<%= emp.getEmail() %>" required><br>
    Department: <input type="text" name="department" value="<%= emp.getDepartment() %>" required><br>

    <input type="submit" value="Update Employee">
</form>

<br>
<a href="EmployeeServlet">Back to List</a>

</body>
</html>