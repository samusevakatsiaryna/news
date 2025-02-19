package web.controller.commands.actions;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.controller.commands.Command;
import web.bean.News;
import web.service.NewsService;
import web.service.ServiceException;
import web.service.ServiceProvider;

import java.io.IOException;
import java.util.List;

public class GoToLogin implements Command {

    private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
    private final NewsService newsService = serviceProvider.getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("invitationMessage", "Hello, user!");

            List<News> newsArticles = newsService.getAllNews();
            request.setAttribute("newsArticles", newsArticles);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/auth.jsp");
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            request.setAttribute("errorMessage", "Произошла ошибка в сервисном слое.");
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
        }
        catch (Exception e) {
            request.setAttribute("errorMessage", "Произошла общая ошибка.");
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }

}
