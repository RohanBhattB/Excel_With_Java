package org.example;

import java.sql.*;

public class test {
    public static void main(String[] args) {
        // JDBC Connection Variables
        String jdbcURL = "jdbc:oracle:thin:@localhost:1521:XE"; // Change as per your DB
        String dbUser = "SYSTEM";
        String dbPassword = "rohan";

        String createTableSQL = "CREATE TABLE STUDENT ("
                + "NAME VARCHAR2(100) NOT NULL, "
                + "ROLLNO VARCHAR2(20) PRIMARY KEY, "
                + "REGISTERED VARCHAR2(3))";

        // Establish Connection and Execute Query
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(createTableSQL);
            System.out.println("Table STUDENT created successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
