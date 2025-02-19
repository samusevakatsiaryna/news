package web.controller.commands.actions;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.controller.commands.Command;

import java.io.IOException;

public class DoRegistration implements Command {
    // TODO finish registration
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
    } catch (Exception e) {
        request.setAttribute("errorMessage", "Произошла общая ошибка.");
        request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
    }

    }
}
