package web.controller.commands.actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.bean.News;
import web.controller.commands.Command;
import web.service.NewsService;
import web.service.ServiceException;
import web.service.ServiceProvider;

import java.io.IOException;
import java.util.List;

public class GetNews implements Command {

    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<News> newsArticles = newsService.getAllNews();
            request.setAttribute("newsArticles", newsArticles);
        } catch (ServiceException e) {
            request.setAttribute("errorMessage", "Произошла ошибка в сервисном слое.");
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Произошла общая ошибка.");
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
