
import java.io.IOException;

import DAO.LoginDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDAO loginDAO;

    public void init() {
        loginDAO = new LoginDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String accountType = loginDAO.validateUser(username, password);

        HttpSession session = request.getSession();
        if (accountType != null) {
            session.setAttribute("username", username);
            session.setAttribute("accountType", accountType);

            switch (accountType) {
                case "admin":
                    response.sendRedirect("employeeDashboard.jsp");
                    break;
                case "manager":
                    response.sendRedirect("managerDashboard.jsp");
                    break;
                default:
                    response.sendRedirect("login.jsp?error=Invalid account type");
                    break;
            }
        } else {
            response.sendRedirect("login.jsp?error=Invalid username or password");
        }
    }
}
