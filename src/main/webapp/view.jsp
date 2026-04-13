<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.employeeapp.model.Employee" %>

<html>
<head>
    <title>Employee List</title>
</head>
<body>

<h2>Employee List</h2>

<form action="add.jsp" method="get">
    <button type="submit">Add Employee</button>
</form>
<br><br>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Department</th>
        <th>Actions</th>
    </tr>

    <%
        List<Employee> list = (List<Employee>) request.getAttribute("empList");

        if (list != null) {
            for (Employee e : list) {
    %>
    <tr>
        <td><%= e.getId() %></td>
        <td><%= e.getName() %></td>
        <td><%= e.getEmail() %></td>
        <td><%= e.getDepartment() %></td>
        <td>
            <a href="EmployeeServlet?action=edit&id=<%= e.getId() %>">Edit</a> |
            <a href="EmployeeServlet?action=delete&id=<%= e.getId() %>"
               onclick="return confirm('Are you sure?')">Delete</a>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>