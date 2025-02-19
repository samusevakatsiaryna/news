<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home - Trading App</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

    <script>
        function toggleEditForm(newsId) {
            let form = document.getElementById("editForm-" + newsId);
            form.style.display = (form.style.display === "none" || form.style.display === "") ? "block" : "none";
        }
    </script>

    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 30px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
        }

        .news-card {
            background-color: #fff;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 15px;
            position: relative;
        }

        .news-title {
            font-size: 1.2rem;
            font-weight: bold;
            color: #333;
            margin-bottom: 8px;
        }

        .news-body {
            font-size: 0.95rem;
            color: #555;
        }

        .section-title {
            font-size: 1.5rem;
            font-weight: bold;
            color: #ffcc00;
            margin-bottom: 20px;
            text-align: center;
        }

        .action-icons {
            position: absolute;
            right: 10px;
            top: 10px;
            display: flex;
            gap: 10px;
        }

        .icon-btn {
            background-color: #ffcc00;
            color: #000;
            border: none;
            border-radius: 50%;
            width: 32px;
            height: 32px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 16px;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .icon-btn:hover {
            background-color: #e6b800;
            transform: scale(1.1);
        }

        .edit-form {
            display: none;
            margin-top: 15px;
            padding: 15px;
            border: 1px solid #ddd;
            background: #f9f9f9;
            border-radius: 5px;
        }

        .btn-save {
            background-color: #ffcc00;
            color: black;
            font-weight: bold;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
        }

        .btn-save:hover {
            background-color: #e6b800;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="my-4">Welcome to Your Trading App, <%= session.getAttribute("email") %></h1>

        <!-- 🔥 Форма добавления новостей (видна только админу) -->
        <c:if test="${sessionScope.authUser.role eq 'admin'}">
            <div class="news-section">
                <h3 class="section-title">Add a New Trading News</h3>
                <form action="Controller?command=add_news" method="post">
                    <div class="form-group">
                        <label for="title">Title</label>
                        <input type="text" class="form-control" id="title" name="title" required>
                    </div>

                    <div class="form-group">
                        <label for="content">Content</label>
                        <textarea class="form-control" id="content" name="content" rows="4" required></textarea>
                    </div>

                    <div class="form-group">
                        <label for="typeName">Category</label>
                        <select class="form-control" id="typeName" name="typeName" required>
                            <option value="Crypto">Crypto</option>
                            <option value="ETFs">ETF</option>
                            <option value="Stocks">Stocks</option>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">Add News</button>
                </form>
            </div>
        </c:if>

        <!-- Список новостей -->
        <div class="news-section">
            <h3 class="section-title">Latest Trading News</h3>
            <c:choose>
                <c:when test="${not empty newsArticles}">
                    <c:forEach var="news" items="${newsArticles}">
                        <div class="news-card">
                            <div class="news-title">${news.title}</div>
                            <div class="news-body">${news.content}</div>

                            <c:if test="${sessionScope.authUser.role eq 'admin'}">
                                <div class="action-icons">
                                    <!-- 🖊 Кнопка редактирования -->
                                    <button type="button" class="icon-btn" onclick="toggleEditForm(${news.id})">
                                        <i class="fas fa-pen"></i>
                                    </button>

                                    <!-- 🗑 Кнопка удаления -->
                                    <form action="Controller" method="post" style="display:inline;">
                                        <input type="hidden" name="command" value="delete_news"/>
                                        <input type="hidden" name="newsId" value="${news.id}"/>
                                        <button type="submit" class="icon-btn">
                                            <i class="fas fa-trash-alt"></i>
                                        </button>
                                    </form>
                                </div>

                                <!-- Форма редактирования (изначально скрыта) -->
                                <div class="edit-form" id="editForm-${news.id}">
                                    <form action="Controller?command=update_news" method="post">
                                        <input type="hidden" name="id" value="${news.id}">

                                        <div class="form-group">
                                            <label for="title-${news.id}">Title:</label>
                                            <input type="text" class="form-control" id="title-${news.id}" name="title" value="${news.title}" required>
                                        </div>

                                        <div class="form-group">
                                            <label for="content-${news.id}">Content:</label>
                                            <textarea class="form-control" id="content-${news.id}" name="content" rows="3" required>${news.content}</textarea>
                                        </div>

                                        <button type="submit" class="btn-save">Save Changes</button>
                                    </form>
                                </div>
                            </c:if>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p class="text-center text-muted">No news available at the moment.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>
