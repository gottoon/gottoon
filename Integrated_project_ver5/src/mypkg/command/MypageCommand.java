package mypkg.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import mypkg.control.Command;
import mypkg.control.CommandResult;
import mypkg.service.UserService;
import mypkg.service.UserWebtoonMapsService;
import mypkg.service.WebtoonService;

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

		long CurrentUser_facebookID = (long) session
				.getAttribute("CurrentUser");

		if (todo == null) {
			System.out.println("내가 본 웹툰 실행");
			commandResult = new CommandResult(
					"/WEB-INF/jsp/mypage/mypageReadWebtoon.jsp");
		} else if (todo.equals("mypageReadWebtoon")) {
			System.out.println("내가 본 웹툰 실행");
			commandResult = new CommandResult(
					"/WEB-INF/jsp/mypage/mypageReadWebtoon.jsp");
		} else if (todo.equals("mypageWishWebtoon")) {
			System.out.println("찜 웹툰 실행");
			commandResult = new CommandResult(
					"/WEB-INF/jsp/mypage/mypageWishWebtoon.jsp");
		} else if (todo.equals("mypageNewWebtoon")) {
			System.out.println("신작 웹툰 실행");
			commandResult = new CommandResult(
					"/WEB-INF/jsp/mypage/mypageNewWebtoon.jsp");
		}

		else if (todo.equals("readWebtoon")) {
			System.out.println("Ajax : 읽은 웹툰 실행");

			commandResult = new CommandResult("application/json",
					this.doReadWebtoon(request, CurrentUser_facebookID));
		}

		else if (todo.equals("wishWebtoon")) {
			System.out.println("Ajax : 찜 웹툰 실행");

			commandResult = new CommandResult("application/json",
					this.doWishWebtoon(request, CurrentUser_facebookID));
		}

		else if (todo.equals("newWebtoon")) {
			System.out.println("Ajax : 신작 웹툰 실행");

			commandResult = new CommandResult("application/json",
					this.doNewWebtoon(request));
		}

		else if (todo.equals("readWebtoonCount")) {
			System.out.println("Ajax : 유저 등급, 경험치 프로그레스, 읽은 웹툰 카운트 실행");

			commandResult = new CommandResult("application/json",
					this.doGradeInfo(request, CurrentUser_facebookID));
		}

		return commandResult;
	}

	public String doReadWebtoon(HttpServletRequest request,
			long CurrentUser_facebookID) {

		String num = request.getParameter("count");
		System.out.println("읽은 웹툰 무한 스크롤 카운트값 : " + num);
		Gson gSon = new Gson();

		return gSon.toJson(webtoonService.getReadToon(CurrentUser_facebookID,
				num));
	}

	public String doWishWebtoon(HttpServletRequest request,
			long CurrentUser_facebookID) {

		String num = request.getParameter("count");
		System.out.println("찜 웹툰 무한 스크롤 카운트값 : " + num);
		Gson gSon = new Gson();

		return gSon.toJson(webtoonService.getWishList(CurrentUser_facebookID,
				num));
	}

	public String doNewWebtoon(HttpServletRequest request) {

		String num = request.getParameter("count");
		System.out.println("신작 웹툰 무한 스크롤 카운트값 : " + num);
		Gson gSon = new Gson();

		return gSon.toJson(webtoonService.getNewToon(num));
	}

	public String doGradeInfo(HttpServletRequest request,
			long CurrentUser_facebookID) {

		Gson gSon = new Gson();

		double[] allInfo = webtoonService
				.getWebtoonCount(CurrentUser_facebookID);

		// 유저 등급을 저장
		System.out.println("==============================\n유저가 본 웹툰 카운트 : "
				+ (int) (allInfo[0]) + "\n전체 대비 % : " + allInfo[1]
				+ "%\n사용자 등급 " + (int) (allInfo[2])
				+ "저장\n==============================");
		
		allInfo[3] = userService.doSetUserGrade(CurrentUser_facebookID, (int) (allInfo[2]));
		
		return gSon.toJson(allInfo);
	}
}
