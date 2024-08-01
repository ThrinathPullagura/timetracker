<%@ page import="java.util.List" %>
<%@ page import="model.Task" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manager Dashboard</title>
</head>
<body>
    <h1>Pending Tasks</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Description</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Task> pendingTasks = (List<Task>) request.getAttribute("pendingTasks");
                if (pendingTasks != null) {
                    for (Task task : pendingTasks) {
            %>
            <tr>
                <td><%= task.getId() %></td>
                <td><%= task.getTitle() %></td>
                <td><%= task.getDescription() %></td>
                <td><%= task.getStartTime() %></td>
                <td><%= task.getEndTime() %></td>
                <td><%= task.getApprovalStatus() %></td>
                <td>
                    <form action="ManagerDashboardServlet" method="post">
                        <input type="hidden" name="taskId" value="<%= task.getId() %>">
                        <input type="submit" name="action" value="approve">
                        <input type="submit" name="action" value="reject">
                    </form>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="7">No pending tasks available.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
