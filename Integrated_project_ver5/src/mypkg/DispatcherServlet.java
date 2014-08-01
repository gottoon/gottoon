package mypkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypkg.control.Command;
import mypkg.control.CommandResult;
import mypkg.control.RequestHelper;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
	}

	protected void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
		System.out.println("dispatch 시작 ");
		System.out.println("dispatch = " + page);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	protected void sendContent(HttpServletRequest request, HttpServletResponse response, CommandResult commandResult)
			throws ServletException, IOException {
		System.out.println("sendContent 시작 ");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("1 :"+commandResult.getContent());
		PrintWriter out = response.getWriter();
		System.out.println("2 :"+commandResult.getContentType());

		if (commandResult.getContentType().contains("text/plain")) {
			System.out.println("if 1");
			out.println(commandResult.getContent());
		} else if (commandResult.getContentType().contains("json")) {
			response.setContentType("application/json");
			System.out.println("if 2");
			out.println(commandResult.getContent());
		}
		out.flush();
		out.close();
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("프로세스 시작 ");
		CommandResult commandResult;

		try {

			RequestHelper helper = new RequestHelper(request);

			Command command = helper.getCommand();

			commandResult = command.execute(request, response);

			if (commandResult.hasView()) {
				dispatch(request, response, commandResult.getViewURL());
			} else {
				sendContent(request, response, commandResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
}
