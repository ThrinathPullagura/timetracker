import java.io.IOException;
import java.util.List;

import DAO.TaskDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Task;

@WebServlet("/ManagerDashboardServlet")
public class managerDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TaskDAO taskDAO;

    public managerDashboardServlet() {
        this.taskDAO = new TaskDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); // Set the response content type
        try {
            List<Task> pendingTasks = taskDAO.getPendingTasks();
            request.setAttribute("pendingTasks", pendingTasks);
            request.getRequestDispatcher("managerDashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Consider using a logging framework
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while retrieving tasks.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); // Set the response content type
        try {
            String taskIdParam = request.getParameter("taskId");
            String action = request.getParameter("action");

            if (taskIdParam == null || taskIdParam.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Task ID is required.");
                return;
            }

            int taskId = Integer.parseInt(taskIdParam);

            if ("approve".equals(action)) {
                taskDAO.updateTaskStatus(taskId, "Approved");
            } else if ("reject".equals(action)) {
                taskDAO.updateTaskStatus(taskId, "Rejected");
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter.");
                return;
            }

            // Redirect to the GET method to refresh the dashboard
            response.sendRedirect(request.getContextPath() + "/ManagerDashboardServlet");
        } catch (NumberFormatException e) {
            e.printStackTrace(); // Consider using a logging framework
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid task ID format.");
        } catch (Exception e) {
            e.printStackTrace(); // Consider using a logging framework
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating task status.");
        }
    }
}
