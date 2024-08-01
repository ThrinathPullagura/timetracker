import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.LoginDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.TimeLog;

@WebServlet("/EmployeeDashboardServlet")
public class EmployeeDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDAO loginDAO;

    public void init() {
        loginDAO = new LoginDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Fetch time logs and prepare data for the JSP
        List<TimeLog> timeLogs = new ArrayList<>();
        ResultSet rs = loginDAO.getTimeLogs(username);
        try {
            while (rs != null && rs.next()) {
                java.sql.Timestamp timeIn = rs.getTimestamp("time_in");
                java.sql.Timestamp timeOut = rs.getTimestamp("time_out");
                timeLogs.add(new TimeLog(timeIn, timeOut));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("username", username);
        request.setAttribute("timeLogs", timeLogs);
        request.getRequestDispatcher("employeeDashboard.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        if ("logTimeIn".equals(action)) {
            loginDAO.logTimeIn(username);
        } else if ("logTimeOut".equals(action)) {
            loginDAO.logTimeOut(username);
        }

        response.sendRedirect("EmployeeDashboardServlet");
    }
}
