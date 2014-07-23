package mypkg.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypkg.control.Command;
import mypkg.control.CommandResult;
import mypkg.service.WebtoonService;
import mypkg.vo.WebtoonVO;

public class WebtoonCommand implements Command {
	CommandResult commandResult = null;
	WebtoonService webtoonService = new WebtoonService();

	public CommandResult execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("웹툰커맨드 시작 ");

		String todo = request.getParameter("todo");
		
		// 2014.07.11 soo 웹툰 상세보기 todo - 2014.07.19 수정
		if (todo.equals("showWebtoonDetails")) {
			this.doGetWebtoonDetails(request);

			commandResult = new CommandResult("/WEB-INF/jsp/recommend/showWebtoonDetails.jsp");
		} else {
			
		}
		return commandResult;
	}

	//2014.07.11 soo 웹툰 상세보기 서비스 불러오기 - 2014.07.19 수정
	public void doGetWebtoonDetails(HttpServletRequest request) {
		int webtoon_id = Integer.parseInt(request.getParameter("webtoon_id"));
		System.out.println(webtoon_id);
		WebtoonVO webtoonDetail = webtoonService.doGetWebtoonDetail(webtoon_id);
		String authorsName = webtoonService.doGetAuthors(webtoon_id);
		
		request.setAttribute("webtoonDetail", webtoonDetail);
		request.setAttribute("authorsName", authorsName);
	}
	
}
