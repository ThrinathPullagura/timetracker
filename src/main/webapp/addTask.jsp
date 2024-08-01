<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Task</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0f2f5;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 600px;
        }
        h2 {
            margin-bottom: 20px;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        input[type="text"], input[type="datetime-local"], textarea, select {
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Add New Task</h2>
        <form action="AddTaskServlet" method="post">
            <input type="text" name="taskTitle" placeholder="Employee Name" required>
            <textarea name="taskDescription" placeholder="Task Description" rows="4" required></textarea>
            <input type="datetime-local" name="taskStartTime" placeholder="Task Starting Time" required>
            <input type="datetime-local" name="taskEndTime" placeholder="Task Ending Time" required>
            <input type="submit" value="Add Task">
        </form>
    </div>
</body>
</html>