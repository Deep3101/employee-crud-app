package com.employeeapp.dao;

import com.employeeapp.model.Employee;
import com.employeeapp.util.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeDao.class);

    public void addEmployee(Employee employee) {
        try {
            Connection connection = DBConnection.getConnection();
            String query = "INSERT INTO employee(name, email, department) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getDepartment());
            preparedStatement.executeUpdate();
            logger.info("Employee added successfully");
        } catch (Exception e) {
            logger.error("Error while adding the employee", e);
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT * FROM employee";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setName(resultSet.getString("name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setDepartment(resultSet.getString("department"));
                list.add(employee);
                logger.info("Employees fetched successfully");
            }
        } catch (Exception e) {
            logger.error("Error while getting the employees", e);
        }
        return list;
    }
}
