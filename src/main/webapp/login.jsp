<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #6a1b9a, #8e24aa);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            background: #ffffff;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
            padding: 30px;
            width: 350px;
            max-width: 90%;
        }

        h2 {
            text-align: center;
            color: #6a1b9a;
            margin-bottom: 20px;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-bottom: 6px;
            color: #6a1b9a;
            font-weight: bold;
        }

        input[type="text"],
        input[type="password"] {
            padding: 12px;
            margin-bottom: 15px;
            border: 1px solid #6a1b9a;
            border-radius: 6px;
            font-size: 16px;
        }

        input[type="submit"] {
            background-color: #8e24aa;
            color: #ffffff;
            border: none;
            padding: 15px;
            border-radius: 6px;
            cursor: pointer;
            font-size: 18px;
            font-weight: bold;
        }

        input[type="submit"]:hover {
            background-color: #6a1b9a;
        }

        .error-message {
            color: #dc3545;
            margin-top: 15px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Login</h2>
        <form action="LoginServlet" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <input type="submit" value="Login">
        </form>
        <%
            String error = request.getParameter("error");
            if (error != null) {
        %>
        <p class="error-message"><%= error %></p>
        <%
            }
        %>
    </div>
</body>
</html>
