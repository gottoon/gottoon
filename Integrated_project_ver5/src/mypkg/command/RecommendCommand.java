package mypkg.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mypkg.control.Command;
import mypkg.control.CommandResult;
import mypkg.service.RecommendService;
import mypkg.vo.WebtoonVO;

// 다 내꺼 soo 웹툰 추천
public class RecommendCommand implements Command {
	CommandResult commandResult = null;

	public CommandResult execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<WebtoonVO> recommendWebtoons = this.doGetRecommendWebtoons(request);

		request.setAttribute("recommendWebtoons", recommendWebtoons);
		commandResult = new CommandResult("/WEB-INF/jsp/recommend/recommend_webtoon.jsp");
		
		return commandResult;
	}
	
	public List<WebtoonVO> doGetRecommendWebtoons(HttpServletRequest request) {
		HttpSession session = request.getSession();
		long currentUser_facebookID = (long)session.getAttribute("CurrentUser");
		String viewfreeValue = request.getParameter("filterviewfree");
		
		RecommendService recommendService = new RecommendService();
		return recommendService.getRecommendWebtoons(currentUser_facebookID, viewfreeValue);
	}
}
