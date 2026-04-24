package com.employeeapp.dao;

import com.employeeapp.model.Employee;
import com.employeeapp.util.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
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

    public Employee getEmployeeById(long id) {
        Employee employee = null;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM employee WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setName(resultSet.getString("name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setDepartment(resultSet.getString("department"));
            }
            logger.info("Employee fetched Successfully with id = " + id);
        } catch (SQLException e) {
            logger.error("Error while getting employee data for id = " + id, e);
        }
        return employee;
    }

    public void deleteEmployee(long id) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "DELETE FROM employee WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ps.executeUpdate();
            logger.info("Employee Deleted Successfully with id = " + id);
        } catch (SQLException e) {
            logger.error("Error while Deleting the employee with id = " + id, e);
        }
    }

    public void updateEmployee(Employee employee) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "UPDATE employee SET name=?, email=?, department=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getDepartment());
            ps.setLong(4, employee.getId());
            ps.executeUpdate();
            logger.info("Employee updated successfully");
        } catch (SQLException e) {
            logger.error("Error while updating employee", e);
        }
    }
}
