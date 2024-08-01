import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.TaskDAO;
import model.Task;

public class AddTaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddTaskServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle GET requests, if needed
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String taskTitle = request.getParameter("taskTitle");
        String taskDescription = request.getParameter("taskDescription");
        String taskStartTime = request.getParameter("taskStartTime");
        String taskEndTime = request.getParameter("taskEndTime");

        // Check for null or empty parameters and handle validation
        if (taskTitle == null || taskTitle.isEmpty() ||
            taskDescription == null || taskDescription.isEmpty() ||
            taskStartTime == null || taskStartTime.isEmpty() ||
            taskEndTime == null || taskEndTime.isEmpty()) {
            
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required.");
            return;
        }

        // Create a new Task object with "Pending" as default for approval status
        Task task = new Task(0, taskTitle, taskDescription, taskStartTime, taskEndTime, "Pending");

        // Create a TaskDAO instance
        TaskDAO taskDAO = new TaskDAO();
        
        try {
            // Add task to the database
            taskDAO.addTask(task);
            
            // Redirect to success page
            response.sendRedirect("Success.html");
        } catch (Exception e) {
            e.printStackTrace(); // Consider using a logging framework
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while adding the task.");
        }
    }
}
