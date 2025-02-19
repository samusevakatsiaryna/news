package web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import web.controller.commands.Command;
import web.controller.commands.CommandProvider;
import web.util.ConnectionPool;
import web.util.ConnectionPoolException;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private final CommandProvider commandProvider = new CommandProvider();

	public Controller() {
		super();
	}

	@Override
	public void init() throws ServletException {
		try {
			super.init();
			ConnectionPool.getInstance().initConnectionPool();
		} catch(ConnectionPoolException e) {
			throw new ServletException("Failed to init Connection pool", e);
		}
    }

	@Override
	public void destroy() {
		super.destroy();
		ConnectionPool.getInstance().dispose();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doRequest(request, response);
	}

	private void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userCommand = request.getParameter("command");
		Command command = commandProvider.getCommand(userCommand);
		command.execute(request, response);
	}

}
