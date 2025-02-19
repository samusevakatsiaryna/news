<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error - Trading App</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            padding: 50px;
        }
        .error-container {
            max-width: 500px;
            margin: auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #ffcc00;
        }
        p {
            color: #333;
        }
        .btn-home {
            background-color: #ffcc00;
            color: #000;
            padding: 10px 20px;
            border-radius: 5px;
            font-weight: bold;
            text-decoration: none;
            display: inline-block;
            margin-top: 20px;
        }
        .btn-home:hover {
            background-color: #e6b800;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Oops! Something went wrong 🚨</h1>
        <p>${errorMessage}</p>
        <a href="Controller?command=go_to_home" class="btn-home">Go Back to Home</a>
    </div>
</body>
</html>
