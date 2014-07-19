package mypkg.control;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {
	HttpServletRequest request;
	CommandFactory commandFactory;

	public RequestHelper(HttpServletRequest request) {
		this.request = request;
		commandFactory = new CommandFactory();
	}

	public Command getCommand() {
		Command target = null;

		target = CommandFactory.createCommand(request.getPathInfo());
		System.out.println("겟 커맨드 시작 " + request.getPathInfo());
		return target;
	}

}
