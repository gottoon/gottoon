package mypkg.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mypkg.control.Command;
import mypkg.control.CommandResult;
import mypkg.service.UserService;
import mypkg.vo.UserVO;

public class UserCommand implements Command {
	CommandResult commandResult = null;
	UserService userService = new UserService();

	public CommandResult execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("유저 커맨드 시작 ");
		System.out.println(request.getParameter("CurruntUser_facebookID"));

		String todo = request.getParameter("todo");
		System.out.println("todo = " + todo);

		if (todo != null && todo.equals("logout")) {
			this.logout(request);
			commandResult = new CommandResult("/WEB-INF/jsp/main/main.jsp");
		} else {
			this.doCheckUser(request, response);

		}

		return commandResult;

	}

	public void doAddUser(HttpServletRequest request,
			HttpServletResponse response) {

		if (userService.doAddUser(request)) {
			System.out.println("회원가입 완료");
			commandResult = new CommandResult("/WEB-INF/jsp/main/main.jsp");
		} else {
			commandResult = new CommandResult("/WEB-INF/jsp/error.jsp");
		}
	}

	public void doCheckUser(HttpServletRequest request,
			HttpServletResponse response) {

		long CurruntUser_facebookID = Long.parseLong(request
				.getParameter("CurruntUser_facebookID"));

		boolean isUser = userService.doCheckUser(request);
		String userGrade = userService.doGetUserGrade(CurruntUser_facebookID);

		if (isUser) {
			System.out.println("이미 회원입니다.");

			commandResult = new CommandResult("/WEB-INF/jsp/main/main.jsp");
		} else {
			this.doAddUser(request, response);
		}

		System.out.println("CurrentUser = " + CurruntUser_facebookID
				+ ", userGrade = " + userGrade);
		HttpSession session = request.getSession();
		session.setAttribute("CurrentUser", CurruntUser_facebookID);
		session.setAttribute("userGrade", userGrade);
	}

	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("CurrentUser");
		session.removeAttribute("userGrade");

	}

}
