<%@ page import="java.util.List" %>
<%@ page import="model.TimeLog" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Dashboard</title>
    <style>
        /* Reset some default styles */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        /* Basic styling */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0f2f5;
            color: #333;
        }

        /* Header styling */
        header {
            background-color: #007bff;
            color: #fff;
            padding: 15px 0;
            border-bottom: 2px solid #0056b3;
        }

        .header-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 0 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .header-container h1 {
            font-size: 24px;
        }

        nav ul {
            list-style: none;
            display: flex;
        }

        nav ul li {
            margin: 0 15px;
        }

        nav ul li a {
            color: #fff;
            text-decoration: none;
            font-size: 16px;
            font-weight: 500;
        }

        nav ul li a:hover {
            text-decoration: underline;
        }

        /* Main container styling */
        main {
            padding: 20px;
        }

        .container {
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            padding: 20px;
            max-width: 1200px;
            margin: 20px auto;
        }

        h2 {
            margin-bottom: 20px;
        }

        .action-buttons {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        .action-buttons form {
            margin: 0;
        }

        .btn {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            text-align: center;
            transition: background-color 0.3s ease;
        }

        .btn-primary {
            background-color: #007bff;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .btn-secondary {
            background-color: #6c757d;
        }

        .btn-secondary:hover {
            background-color: #5a6268;
        }

        /* Table styling */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #f8f9fa;
        }

        td {
            background-color: #ffffff;
        }

        /* Footer styling */
        footer {
            background-color: #343a40;
            color: #fff;
            text-align: center;
            padding: 15px 0;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <!-- Header Section -->
    <header>
        <div class="header-container">
            <h1>Employee Dashboard</h1>
            <nav>
                <ul>
                    <li><a href="employeeProfile.jsp">Profile</a></li>
                    <li><a href="login.jsp">Logout</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <!-- Main Content Section -->
    <main>
        <div class="container">
            <h2>Welcome, <%= session.getAttribute("username") %></h2>

            <!-- Forms for logging time in and out -->
            <div class="action-buttons">
                <form action="EmployeeDashboardServlet" method="post">
                    <input type="hidden" name="action" value="logTimeIn">
                    <button type="submit" class="btn btn-primary">Log Time In</button>
                </form>
                <form action="EmployeeDashboardServlet" method="post">
                    <input type="hidden" name="action" value="logTimeOut">
                    <button type="submit" class="btn btn-secondary">Log Time Out</button>
                </form>
                <form action="addTask.jsp" method="get">
                    <button type="submit" class="btn btn-primary">Add Task</button>
                </form>
            </div>

            <!-- Display time logs -->
            <section class="time-logs">
                <h3>Your Time Logs</h3>
                <table>
                    <thead>
                        <tr>
                            <th>Time In</th>
                            <th>Time Out</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<TimeLog> timeLogs = (List<TimeLog>) request.getAttribute("TimeLogs");

                            if (timeLogs != null && !timeLogs.isEmpty()) {
                                for (TimeLog log : timeLogs) {
                        %>
                        <tr>
                            <td><%= log.getTimeIn() %></td>
                            <td><%= log.getTimeOut() != null ? log.getTimeOut() : "Not Yet Logged Out" %></td>
                        </tr>
                        <%
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="2">No time logs available.</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </section>
        </div>
    </main>

    <!-- Footer Section -->
    <footer>
        <p>&copy; 2024 Thrinath Tech. All rights reserved.</p>
    </footer>
</body>
</html>
