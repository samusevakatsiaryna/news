package web.controller.commands.actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.controller.commands.Command;
import web.service.NewsService;
import web.service.ServiceException;
import web.service.ServiceProvider;

import java.io.IOException;

public class DeleteNews implements Command {

    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String newsIdStr = request.getParameter("newsId");

            if (newsIdStr == null || newsIdStr.isEmpty()) {
                request.setAttribute("error", "Error - News id is not found");
                request.getRequestDispatcher("WEB-INF/jsp/home.jsp").forward(request, response);
                return;
            }

            int newsId = Integer.parseInt(newsIdStr);

            newsService.deleteNewsById(newsId);

            response.sendRedirect("Controller?command=go_to_home");
        }  catch (ServiceException e) {
            //logging
            request.setAttribute("errorMessage", "Произошла ошибка в сервисном слое.");
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
        }
        catch (Exception e) {
            // logging
            request.setAttribute("errorMessage", "Произошла общая ошибка.");
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
