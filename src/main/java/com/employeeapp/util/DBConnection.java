package com.employeeapp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final Logger logger = LoggerFactory.getLogger(DBConnection.class);

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employee_db",
                    "root",
                    "Deep@123"
            );
            logger.info("Database connected successfully");
        } catch (Exception e) {
            logger.error("DB Connection Failed", e);
        }
        return connection;
    }
}
