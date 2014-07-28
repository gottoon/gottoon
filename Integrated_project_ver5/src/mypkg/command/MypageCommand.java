package mypkg.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mypkg.control.Command;
import mypkg.control.CommandResult;
import mypkg.service.AuthorService;
import mypkg.service.MypageService;
import mypkg.service.UserService;
import mypkg.service.UserWebtoonMapsService;
import mypkg.service.WebtoonService;
import mypkg.vo.AuthorVO;
import mypkg.vo.MypageVO;
import mypkg.vo.UserVO;
import mypkg.vo.UserWebtoonMapsVO;
import mypkg.vo.WebtoonVO;

// 7.18 다 영규꺼
public class MypageCommand implements Command {
	CommandResult commandResult = null;
	UserService userService = new UserService();
	WebtoonService webtoonService = new WebtoonService();
	UserWebtoonMapsService userWebtoonMpasService = new UserWebtoonMapsService();

	public CommandResult execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("마이페이지 실행");
		String todo = request.getParameter("todo");
		
		System.out.println("todo :" + todo); 
		
		HttpSession session = request.getSession(true);
		
		long CurrentUser_facebookID = (long) session.getAttribute("CurrentUser");
		
		if (todo == null) {
			System.out.println("내가 본 웹툰 실행");
			this.doMypageReadWebtoon(request, CurrentUser_facebookID);
			commandResult = new CommandResult("/WEB-INF/jsp/mypage/mypageReadWebtoon.jsp");
		} else if (todo.equals("mypageReadWebtoon")) {
			System.out.println("내가 본 웹툰 실행");
			this.doMypageReadWebtoon(request, CurrentUser_facebookID);
			commandResult = new CommandResult("/WEB-INF/jsp/mypage/mypageReadWebtoon.jsp");
		} else if (todo.equals("readWebtoonCount")) {
			System.out.println("웹툰 카운트 실행");
			int count = this.doReadWebtoonCount(request, CurrentUser_facebookID);
			this.doSetUserGrade(CurrentUser_facebookID, count);
			commandResult = new CommandResult("text/plain", Integer.toString(count));
		} else if (todo.equals("mypageWishWebtoon")) {
			System.out.println("찜 웹툰 실행");
			this.doViewWishList(request, CurrentUser_facebookID);
			commandResult = new CommandResult("/WEB-INF/jsp/mypage/mypageWishWebtoon.jsp");
		} else if (todo.equals("mypageNewWebtoon")) {
			System.out.println("신작 웹툰 실행");
			this.doRecommendNew(request, CurrentUser_facebookID);
			commandResult = new CommandResult("/WEB-INF/jsp/mypage/mypageNewWebtoon.jsp");
		} 


		return commandResult;
	}

	// 내가 본 웹툰
	public void doMypageReadWebtoon(HttpServletRequest request,
			long CurrentUser_facebookID) throws ServletException, IOException {
		
		UserVO userVO = userService.getUserGrade(CurrentUser_facebookID);
		
		List<WebtoonVO> webtoonVO = webtoonService
				.getReadToon(CurrentUser_facebookID);
		
		request.setAttribute("grade", userVO.getUsers_grade().toString());
		request.setAttribute("readToon", webtoonVO);
	}
	
	// 내가 본 웹툰 카운트
	public int doReadWebtoonCount(HttpServletRequest request,
			long CurrentUser_facebookID) throws ServletException, IOException {

		return userWebtoonMpasService.doGetWebtoonCount(CurrentUser_facebookID);
	}
	
	public void doSetUserGrade(long CurrentUser_facebookID, int count) throws ServletException, IOException {

		userService.doSetUserGrade(CurrentUser_facebookID, count);
	}
	
	// 신작 웹툰
	public void doRecommendNew(HttpServletRequest request,
			long CurrentUser_facebookID) throws ServletException, IOException {

		List<WebtoonVO> webtoonVO = webtoonService.getNewToon();

		request.setAttribute("newWebtoon", webtoonVO);
	}
	
	// 찜한 웹툰
	public void doViewWishList(HttpServletRequest request,
			long CurrentUser_facebookID) throws ServletException, IOException {
		
		List<WebtoonVO> webtoonVO = webtoonService
				.getWishList(CurrentUser_facebookID);

		request.setAttribute("wishList", webtoonVO);
	}
}
