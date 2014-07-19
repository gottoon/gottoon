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
	MypageService mypageService = new MypageService();
	AuthorService authorService = new AuthorService();
	UserWebtoonMapsService userWebtoonMpasService = new UserWebtoonMapsService();

	public CommandResult execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("마이페이지 실행");
		String todo = request.getParameter("todo");
		
		System.out.println("todo :" + todo); 
		
		HttpSession session = request.getSession(true);
		
		long CurrentUser_facebookID = (long) session
				.getAttribute("CurrentUser");
		
		
		// 메인 부분
		if (todo == null) {
			System.out.println("내가 본 웹툰 실행");
			this.doMypageReadWebtoon(request, CurrentUser_facebookID);
			commandResult = new CommandResult("/WEB-INF/jsp/mypage/mypageReadWebtoon.jsp");
		} else if (todo.equals("mypageReadWebtoon")) {
			System.out.println("내가 본 웹툰 실행");
			this.doMypageReadWebtoon(request, CurrentUser_facebookID);
			commandResult = new CommandResult("/WEB-INF/jsp/mypage/mypageReadWebtoon.jsp");
		} else if (todo.equals("mypageRecommend")) {
			System.out.println("추천 웹툰 실행");
			this.doMypageRecommend(request, response);
			commandResult = new CommandResult("/WEB-INF/jsp/mypage/mypageRecommendWebtoon.jsp");
		} else if (todo.equals("mypageSetting")) {
			System.out.println("설정 실행");
			this.doMypageSetting(request, response);
			commandResult = new CommandResult( "/WEB-INF/jsp/mypage/mypageSetting.jsp");
		
		// 추천 부분	
		} else if (todo.equals("recommendNew")) {
			System.out.println("신작 추천 실행");
			this.doRecommendNew(request, CurrentUser_facebookID);
			commandResult = new CommandResult("/WEB-INF/jsp/mypage/mypageRecommendWebtoon.jsp");
		} else if (todo.equals("RecommendAuthor")) {
			System.out.println("작가 추천 실행");
			this.doRecommendAuthor(request, response);
			commandResult = new CommandResult("/WEB-INF/jsp/mypage/mypageRecommendWebtoon.jsp");
		} else if (todo.equals("searchAuthor")) {
			System.out.println("작가 검색 실행");
//			this.doSearchAuthor(request, response);
			commandResult = new CommandResult("/WEB-INF/jsp/mypage/mypageRecommendWebtoon.jsp");
		} else if (todo.equals("viewWishlist")) {
			System.out.println("찜한 웹툰 실행");
			this.doViewWishList(request, CurrentUser_facebookID);
			commandResult = new CommandResult("/WEB-INF/jsp/mypage/mypageRecommendWebtoon.jsp");	
			
		// 세팅 부분	
		} else if (todo.equals("onoffRecommendNew")) {
			System.out.println("신작 추천 ON/OFF 확인");
			this.doOnoffRecommendNew(request, response);
			commandResult = new CommandResult("/WEB-INF/jsp/mypage/mypageSetting.jsp");
		} else if (todo.equals("setOnoffRecommendNew")) {
			System.out.println("신작 추천 ON/OFF 적용");
			this.doSetOnoffRecommendNew(request, response);
			commandResult = new CommandResult("/WEB-INF/jsp/mypage/mypageSetting.jsp");
		} else if (todo.equals("onoffRecommendAuthor")) {
			System.out.println("작가 추천 ON/OFF 확인");
			this.doOnoffRecommendAuthor(request, response);
			commandResult = new CommandResult("/WEB-INF/jsp/mypage/mypageSetting.jsp");
		} else if (todo.equals("setOnoffRecommendAuthor")) {
			System.out.println("작가 추천 ON/OFF 적용");
			this.doSetOnoffRecommendAuhtor(request, response);
			commandResult = new CommandResult("/WEB-INF/jsp/mypage/mypageSetting.jsp");
		} 

		// 테스트
		else if (todo.equals("readWebtoonCount")) {
			System.out.println("웹툰 카운트 실행");
			
			int count = this.doReadWebtoonCount(request, CurrentUser_facebookID);
			
			PrintWriter out = response.getWriter();
			out.print(count);
			out.flush();
			out.close();
			
			this.doSetUserGrade(CurrentUser_facebookID, count);
			
//			this.doReadWebtoonCount(request, CurrentUser_facebookID);
//			System.out.println("웹툰 카운트 : " + this.doReadWebtoonCount(request, CurrentUser_facebookID) + "편");
			commandResult = new CommandResult("/WEB-INF/jsp/mypage/mypageReadWebtoon.jsp");
		}

		return commandResult;
	}

	// ReadWebtoon Command
	public void doMypageReadWebtoon(HttpServletRequest request,
			long CurrentUser_facebookID) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		UserVO userVO = userService.getUserGrade(CurrentUser_facebookID);
		List<WebtoonVO> webtoonVO = webtoonService
				.getReadToon(CurrentUser_facebookID);

		session.setAttribute("grade", userVO.getUsers_grade().toString());
		session.setAttribute("readToon", webtoonVO);
	}

	// Recommend Command
	public void doMypageRecommend(HttpServletRequest request,
			HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
		
		HttpSession session = request.getSession(true);
		
		request.setAttribute("sessionRecommend", session);
	}

	// Setting Command
	public void doMypageSetting(HttpServletRequest request,
			HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		HttpSession session = request.getSession(true);

		request.setAttribute("sessionSetting", session);
	}

	// Recommend new webtoon recommend
	public void doRecommendNew(HttpServletRequest request,
			long CurrentUser_facebookID) throws javax.servlet.ServletException,
			java.io.IOException {

		HttpSession session = request.getSession(true);

		MypageVO mypageVO = mypageService.getNewOnOff(CurrentUser_facebookID);
		List<WebtoonVO> webtoonVO = webtoonService.getNewToon();

		request.setAttribute("sessionRecommendNew", session);
		request.setAttribute("sessionOnoff", mypageVO.getMypage_onoff());
		request.setAttribute("recommendNewWebtoon", webtoonVO);
	}

	// Recommend author webtoon recommend
	public void doRecommendAuthor(HttpServletRequest request,
			HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		HttpSession session = request.getSession(true);

		long CurrentUser_facebookID = (long) session
				.getAttribute("CurrentUser");

		MypageVO mypageVO = mypageService
				.getAuthorOnOff(CurrentUser_facebookID);

		List<AuthorVO> authorVO = authorService.getAllAuthor();

		 request.setAttribute("sessionRecommendAuthor", session);
		 request.setAttribute("sessionOnoff", mypageVO.getMypage_onoff());
		 request.setAttribute("author", authorVO);
	}

	// 
	/*// Recommend author webtoon search // 중복을 제거
	public void doSearchAuthor(HttpServletRequest request,
			HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		HttpSession session = request.getSession(true);
		
		long CurrentUser_facebookID = (long) session
				.getAttribute("CurrentUser");
		
		MypageVO mypageVO = mypageService
				.getAuthorOnOff(CurrentUser_facebookID);
		List<AuthorVO> authorVO = authorService.getAllAuthor();
		
		request.setAttribute("sessionRecommendAuthor", session);
		request.setAttribute("sessionOnoff", mypageVO.getMypage_onoff());
		request.setAttribute("author", authorVO);

		List<WebtoonVO> webtoonVO = webtoonService.getAuthorToon(Integer
				.parseInt(request.getParameter("author")));

		request.setAttribute("recommendAuthorWebtoon", webtoonVO);
	}*/
	
	
	public void doViewWishList(HttpServletRequest request,
			long CurrentUser_facebookID) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		
		List<WebtoonVO> webtoonVO = webtoonService
				.getWishList(CurrentUser_facebookID);

		request.setAttribute("sessionWishList", session);
		request.setAttribute("wishList", webtoonVO);
	}
	

	// Setting - new webtoon recommend onoff state
	public void doOnoffRecommendNew(HttpServletRequest request,
			HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
		
		HttpSession session = request.getSession(true);

		long CurrentUser_facebookID = (long) session
				.getAttribute("CurrentUser");
		
		MypageVO mypageVO = mypageService.getNewOnOff(CurrentUser_facebookID);

		request.setAttribute("sessionOnoff", session);
		request.setAttribute("recommendNewOnoff", mypageVO.getMypage_onoff());
	}

	// Setting - new webtoon recommend onoff set
	public void doSetOnoffRecommendNew(HttpServletRequest request,
			HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		HttpSession session = request.getSession(true);

		long CurrentUser_facebookID = (long) session
				.getAttribute("CurrentUser");

		mypageService.setNewOnOff(CurrentUser_facebookID);
	}

	// Setting - author recommend onoff state
	public void doOnoffRecommendAuthor(HttpServletRequest request,
			HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		HttpSession session = request.getSession(true);

		long CurrentUser_facebookID = (long) session
				.getAttribute("CurrentUser");

		MypageVO mypageVO = mypageService
				.getAuthorOnOff(CurrentUser_facebookID);

		request.setAttribute("sessionOnoff", session);
		request.setAttribute("recommendAuthorOnoff", mypageVO.getMypage_onoff());
	}

	// Setting - author recommend onoff set
	public void doSetOnoffRecommendAuhtor(HttpServletRequest request,
			HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		HttpSession session = request.getSession(true);

		long CurrentUser_facebookID = (long) session
				.getAttribute("CurrentUser");

		mypageService.setAuthorOnOff(CurrentUser_facebookID);
	}
	
	public int doReadWebtoonCount(HttpServletRequest request,
			long CurrentUser_facebookID) throws ServletException, IOException {

		return userWebtoonMpasService.doGetWebtoonCount(CurrentUser_facebookID);
	}
	
	public void doSetUserGrade(long CurrentUser_facebookID, int count) throws ServletException, IOException {

		userService.doSetUserGrade(CurrentUser_facebookID, count);
	}

}
