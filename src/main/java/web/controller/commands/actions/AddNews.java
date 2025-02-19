package web.controller.commands.actions;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.bean.News;
import web.controller.commands.Command;
import web.service.NewsService;
import web.service.NewsTypesService;
import web.service.ServiceException;
import web.service.ServiceProvider;

import java.io.IOException;

public class AddNews implements Command {

    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
    private final NewsTypesService newsTypesService = ServiceProvider.getInstance().getNewsTypeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String typeName = request.getParameter("typeName");

            if (title == null || content == null || typeName == null) {
                request.setAttribute("error", "All fields are required.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/home.jsp");
                dispatcher.forward(request, response);
                return;
            }

            int newsTypeId = newsTypesService.getIdByName(typeName);
            News news = new News(title, content, newsTypeId);
            newsService.addNews(news);
            response.sendRedirect("Controller?command=go_to_home");

        }  catch (ServiceException e) {
            request.setAttribute("errorMessage", "Service layer error");
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
        }
        catch (Exception e) {
            request.setAttribute("errorMessage", "General Error");
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
