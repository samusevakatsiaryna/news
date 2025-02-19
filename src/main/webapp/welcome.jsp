<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to Your Banking</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #ffffff;
            font-family: 'Arial', sans-serif;
            color: #333;
            padding-top: 50px;
            margin: 0;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            text-align: center;
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            border: 1px solid #e0e0e0; /* Легкая рамка */
        }

        .header {
            margin-bottom: 40px;
        }

        h1 {
            font-size: 36px;
            font-weight: 600;
            color: #ffcc00; /* Яркий акцент Яндекса */
        }

        p.lead {
            font-size: 18px;
            color: #666;
        }

        .btn-login {
            background-color: #ffcc00;
            border-color: #ffcc00;
            color: #000;
            font-size: 18px;
            font-weight: 500;
            padding: 10px 20px;
            margin: 10px;
            border-radius: 4px;
        }

        .btn-login:hover {
            background-color: #ffbb00;
            border-color: #ffbb00;
        }

        .btn-register {
            background-color: transparent;
            border: 2px solid #ffcc00;
            color: #ffcc00;
            font-size: 18px;
            font-weight: 500;
            padding: 10px 20px;
            margin: 10px;
            border-radius: 4px;
        }

        .btn-register:hover {
            background-color: #ffcc00;
            color: #000;
        }

        .footer {
            text-align: center;
            margin-top: 40px;
            font-size: 14px;
            color: #999;
            border-top: 1px solid #e5e5e5;
            padding-top: 20px;
        }

        .footer a {
            color: #999;
            text-decoration: none;
        }

        .footer a:hover {
            color: #ffcc00;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="header">
        <h1>Welcome to Your Banking</h1>
        <p class="lead">Check your balance, manage your trades, and stay on top of your financial future.</p>
    </div>

    <div class="text-center">
        <form action="Controller" method="POST">
            <input type="hidden" name="command" value="go_to_login">
            <button type="submit" class="btn btn-lg btn-login">Login</button>
        </form>
    </div>

    <div class="text-center">
        <form action="Controller" method="POST">
            <input type="hidden" name="command" value="do_registration">
            <button type="submit" class="btn btn-lg btn-register">Register</button>
        </form>
    </div>

    <div class="footer">
        <p>&copy; 2024 Your Banking App. All rights reserved. <a href="#">Terms of Service</a> | <a href="#">Privacy Policy</a></p>
    </div>
</div>

</body>
</html>
