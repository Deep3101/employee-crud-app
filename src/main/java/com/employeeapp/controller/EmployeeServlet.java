package com.employeeapp.controller;

import com.employeeapp.dao.EmployeeDao;
import com.employeeapp.model.Employee;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class EmployeeServlet extends HttpServlet {

    EmployeeDao employeeDao = new EmployeeDao();

    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
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

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        List<Employee> list = employeeDao.getAllEmployees();
        httpServletRequest.setAttribute("empList", list);
        RequestDispatcher rd = httpServletRequest.getRequestDispatcher("view.jsp");
        rd.forward(httpServletRequest, httpServletResponse);
    }
}
