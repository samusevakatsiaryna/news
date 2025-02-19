package web.controller.commands.actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.controller.commands.Command;
import web.service.NewsService;
import web.service.ServiceException;
import web.service.ServiceProvider;

import java.io.IOException;

public class UpdateNews implements Command {

    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int newsId = Integer.parseInt(request.getParameter("newsId"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            if (title == null || title.isEmpty()) {
                request.setAttribute("error", "Ошибка: ID новости не передан.");
                request.getRequestDispatcher("WEB-INF/jsp/home.jsp").forward(request, response);
                return;
            }

            newsService.updateNews(newsId, title, content);
            response.sendRedirect("Controller?command=go_to_home");
        }  catch (ServiceException e) {
            request.setAttribute("errorMessage", "Произошла ошибка в сервисном слое.");
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
        }
        catch (Exception e) {
            request.setAttribute("errorMessage", "Произошла общая ошибка.");
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
