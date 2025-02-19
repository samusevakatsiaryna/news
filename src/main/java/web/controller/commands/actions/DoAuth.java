package web.controller.commands.actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.controller.commands.Command;
import web.model.User;
import web.service.AuthService;
import web.service.ServiceException;
import web.service.ServiceProvider;

import java.io.IOException;

public class DoAuth implements Command {

    private final AuthService authService = ServiceProvider.getInstance().getAuthService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User user = authService.authenticate(email, password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("authUser", user);

                response.sendRedirect("Controller?command=go_to_home");

            } else {
                request.setAttribute("error", "Invalid login credentials. Try again or create a new account.");
                request.getRequestDispatcher("WEB-INF/jsp/registration.jsp");

            }
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
