package mypkg.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import mypkg.control.Command;
import mypkg.control.CommandResult;
import mypkg.service.ChartService;
//다 bj 꺼 
public class ChartCommand implements Command {
	CommandResult commandResult = null;
	ChartService chartService = new ChartService();

	public CommandResult execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			JSONException {
		System.out.println("Chart command 시작 ");
		String todo = request.getParameter("todo");
		System.out.println("todo = " + todo);

		if (todo.equals("keywordChart")) {

			commandResult = new CommandResult("/chart/chart.html");
		} else if (todo.equals("authorChart")) {
			commandResult = new CommandResult("/chart/authorChart.html");
		} else if (todo.equals("genreChart")) {
			commandResult = new CommandResult("/chart/genreChart.html");
		} else if (todo.equals("publisherChart")) {
			commandResult = new CommandResult("/chart/publisherChart.html");
		} else if (todo.equals("userWebtoonChart")) {
			commandResult = new CommandResult("/chart/userWebtoonChart.html");
		}

		return commandResult;

	}

	public void doGetAllUsersList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			JSONException {
		JSONObject responseJson = chartService.doGetAllUsersList(request,
				response);

		commandResult = new CommandResult("json", responseJson);
	}

}
