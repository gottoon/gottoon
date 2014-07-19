package mypkg.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypkg.control.Command;
import mypkg.control.CommandResult;
// 다 bj 꺼 
public class MainCommand implements Command {
	public CommandResult execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		CommandResult commandResult = null;

		commandResult = new CommandResult("/WEB-INF/jsp/main/main.jsp");

		return commandResult;

	}
}
