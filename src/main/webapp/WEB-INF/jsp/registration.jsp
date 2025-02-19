<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration - Your Banking App</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #ffffff;
            font-family: 'Arial', sans-serif;
            padding-top: 40px;
            margin: 0;
        }

        .container {
            max-width: 400px;
            margin: 0 auto;
            text-align: center;
            padding: 20px;
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            background-color: #f9f9f9;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        .form-register {
            margin-bottom: 20px;
        }

        .form-register .form-control {
            border-radius: 4px;
            border-color: #ffcc00;
            margin-bottom: 15px;
            padding: 10px;
            font-size: 16px;
        }

        .form-register .form-control:focus {
            border-color: #ffbb00;
            box-shadow: none;
        }

        h2 {
            color: #ffcc00;
            font-size: 28px;
            margin-bottom: 20px;
            font-weight: 700;
        }

        .btn-register {
            background-color: #ffcc00;
            border-color: #ffcc00;
            color: #000;
            font-size: 18px;
            padding: 12px;
            width: 100%;
            border-radius: 4px;
        }

        .btn-register:hover {
            background-color: #ffbb00;
            border-color: #ffbb00;
        }

        .footer {
            font-size: 14px;
            color: #999;
            margin-top: 30px;
        }

        .footer a {
            color: #999;
            text-decoration: none;
        }

        .footer a:hover {
            color: #ffcc00;
        }

        .disclaimer {
            font-size: 12px;
            color: #666;
            margin-top: 10px;
        }
    </style>
</head>
<body>

<div class="container">

    <!-- Вывод сообщения об ошибке, если оно есть -->
    <%
        String errorMessage = (String) request.getAttribute("error");
        if (errorMessage != null) {
    %>
        <div class="alert alert-danger">
            <%= errorMessage %>
        </div>
    <%
        }
    %>

    <form class="form-register" action="Controller" method="post">
        <input type="hidden" name="command" value="do_registration"/>
        <h2>Registration</h2>
        <input type="text" id="inputName" class="form-control" placeholder="Username" name="username" required autofocus>
        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" name="email" required>
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" required>
        <input type="password" id="inputConfirmPassword" class="form-control" placeholder="Confirm password" name="confirmPassword" required>
        <button class="btn btn-lg btn-register" type="submit">Sign Up</button>
        <p class="disclaimer">By clicking "Sign Up", you agree to our <a href="#">terms of service</a> and <a href="#">privacy policy</a>.</p>
        <p class="footer">&copy; 2024 Your Banking App. All rights reserved.</p>
    </form>
</div>

</body>
</html>
