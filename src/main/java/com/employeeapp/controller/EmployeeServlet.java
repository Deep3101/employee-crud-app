package com.employeeapp.controller;

import com.employeeapp.dao.EmployeeDao;
import com.employeeapp.model.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class EmployeeServlet extends HttpServlet {

    EmployeeDao employeeDao = new EmployeeDao();

    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String action = httpServletRequest.getParameter("action");

        if ("update".equals(action)) {
            long id = Integer.parseInt(httpServletRequest.getParameter("id"));
            Employee employee = new Employee();
            employee.setId(id);
            employee.setName(httpServletRequest.getParameter("name"));
            employee.setEmail(httpServletRequest.getParameter("email"));
            employee.setDepartment(httpServletRequest.getParameter("department"));
            employeeDao.updateEmployee(employee);
        } else {
            String name = httpServletRequest.getParameter("name");
            String email = httpServletRequest.getParameter("email");
            String department = httpServletRequest.getParameter("department");

            Employee employee = new Employee();
            employee.setName(name);
            employee.setEmail(email);
            employee.setDepartment(department);

            employeeDao.addEmployee(employee);
            httpServletResponse.sendRedirect("EmployeeServlet");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("edit".equals(action)) {

            long id = Long.parseLong(request.getParameter("id"));
            Employee employee = employeeDao.getEmployeeById(id);

            request.setAttribute("emp", employee);
            request.getRequestDispatcher("edit.jsp").forward(request, response);

        } else if ("delete".equals(action)) {

            long id = Long.parseLong(request.getParameter("id"));
            employeeDao.deleteEmployee(id);

            response.sendRedirect("EmployeeServlet");

        } else {

            List<Employee> list = employeeDao.getAllEmployees();
            request.setAttribute("empList", list);
            request.getRequestDispatcher("view.jsp").forward(request, response);
        }
    }
}

