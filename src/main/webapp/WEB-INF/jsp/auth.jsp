<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Your Banking App</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #ffffff;
            font-family: 'Arial', sans-serif;
        }

        .container {
            max-width: 330px;
            padding: 15px;
            margin: auto;
            text-align: center;
            background-color: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        .form-signin {
            margin-bottom: 15px;
        }

        .form-signin .form-control {
            position: relative;
            height: auto;
            padding: 10px;
            font-size: 16px;
            margin-bottom: 10px;
            border-radius: 4px;
            border-color: #ffcc00;
        }

        .form-signin .form-control:focus {
            z-index: 2;
            border-color: #ffbb00;
            box-shadow: none;
        }

        .text-banking {
            color: #ffcc00; /* Yandex-style yellow color */
        }

        .checkbox {
            margin-bottom: 10px;
        }

        .btn-login {
            background-color: #ffcc00;
            border-color: #ffcc00;
            color: #000;
            font-size: 18px;
            padding: 10px 20px;
            width: 100%;
            border-radius: 4px;
        }

        .btn-login:hover {
            background-color: #ffbb00;
            border-color: #ffbb00;
        }

        .footer {
            font-size: 14px;
            color: #999;
            margin-top: 20px;
        }

        .footer a {
            color: #999;
            text-decoration: none;
        }

        .footer a:hover {
            color: #ffcc00;
        }

        .news-section {
            margin-top: 30px;
            text-align: left;
        }

        .news-card {
            background-color: #f5f5f5;
            margin-bottom: 15px;
            padding: 10px;
            border-radius: 4px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .news-title {
            font-size: 18px;
            font-weight: bold;
            color: #333;
        }

        .news-body {
            font-size: 14px;
            color: #555;
            margin-top: 5px;
        }

    </style>
</head>
<body>

<div class="container">
    <form class="form-signin" action="Controller?command=do_auth" method="post">
        <input type="hidden" name="command" value="do_auth"/>
        <h2 class="form-signin-heading text-banking">Please sign in</h2>
        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" name="email" required autofocus>
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" required>
        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-login" type="submit">Sign In</button>
        <div class="text-center mt-2">
            <a href="Controller?command=registration">Create a new account</a>
        </div>
        <p class="footer mt-5 mb-3">&copy; 2024 Your Banking App. All rights reserved.</p>
    </form>

    <!-- Секция новостей -->
    <div class="news-section">
        <h3 class="text-banking">Latest News</h3>
        <c:forEach var="news" items="${newsArticles}">
            <div class="news-card">
                <div class="news-title">${news.title}</div>
                <div class="news-body">${news.content}</div>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
