package web.controller.commands.actions;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.controller.commands.Command;
import web.service.ServiceException;

import java.io.IOException;

public class GoToRegistration implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registration.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error happened");
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }

}
