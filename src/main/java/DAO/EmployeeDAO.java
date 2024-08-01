package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {
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

    public ResultSet getTimeLogs(String username) {
        ResultSet rs = null;
        String sql = "SELECT time_in, time_out FROM time_logs WHERE username = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public void logTimeIn(String username) {
        String sql = "INSERT INTO time_logs (username, time_in) VALUES (?, NOW())";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void logTimeOut(String username) {
        String sql = "UPDATE time_logs SET time_out = NOW() WHERE username = ? AND time_out IS NULL";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
