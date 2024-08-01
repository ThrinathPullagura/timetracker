package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/timetracker";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Kavya1510";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public ResultSet getEmployeePerformances() {
        ResultSet rs = null;
        String sql = "SELECT * from employee_name, tasks_completed, attendance_days FROM employee_performance";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }
}
