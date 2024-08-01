package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Task;

public class TaskDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/timetracker";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Kavya1510";

    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found", e);
        }
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public List<Task> getPendingTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT id, title, description, start_time, end_time, approval_status FROM tasks WHERE approval_status = 'Pending'";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String startTime = rs.getString("start_time");
                String endTime = rs.getString("end_time");
                String approvalStatus = rs.getString("approval_status");
                Task task = new Task(id, title, description, startTime, endTime, approvalStatus);
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Use a logging framework in production
        }
        return tasks;
    }

    public void updateTaskStatus(int taskId, String status) {
        String sql = "UPDATE tasks SET approval_status = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, taskId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Use a logging framework in production
        }
    }

	

	public void addTask(Task task) {
		// TODO Auto-generated method stub
		
	}
}
