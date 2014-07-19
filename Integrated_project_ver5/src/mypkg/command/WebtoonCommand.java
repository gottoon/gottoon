package mypkg.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypkg.control.Command;
import mypkg.control.CommandResult;
import mypkg.service.WebtoonService;

public class WebtoonCommand implements Command {
	CommandResult commandResult = null;
	WebtoonService webtoonService = new WebtoonService();

	public CommandResult execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("웹툰커맨드 시작 ");

		String todo = request.getParameter("todo");
		
		if (todo.equals("showWebtoonDetails")) { // 2014.07.11 soo 웹툰 상세보기 todo
			this.doShowWebtoonDetails(request, response);

			commandResult = new CommandResult("/WEB-INF/jsp/recommend/showWebtoonDetails.jsp");
		} else {
			
		}
		return commandResult;
	}

	//2014.07.11 soo 웹툰 상세보기 서비스 불러오기
	public void doShowWebtoonDetails(HttpServletRequest request, HttpServletResponse response) {
		webtoonService.doShowWebtoonDetails(request);
	}
	
}
