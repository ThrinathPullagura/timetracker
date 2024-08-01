package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/timetracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Kavya1510";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC driver not found.", e);
        }
    }

    public String validateUser(String username, String password) {
        String role = null;
        String query = "SELECT role FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    role = rs.getString("role");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logging framework for production code
        }

        return role;
    }

    public void logTimeIn(String username) {
        String query = "INSERT INTO timelogs (username, time_in) VALUES (?, NOW())";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logging framework for production code
        }
    }

    public void logTimeOut(String username) {
        String query = "UPDATE timelogs SET time_out = NOW() WHERE username = ? AND time_out IS NULL";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logging framework for production code
        }
    }

    public ResultSet getTimeLogs(String username) {
        String query = "SELECT * FROM timelogs WHERE username = ? ORDER BY time_in DESC";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logging framework for production code
        }
        return null;
    }
}
