package mypkg.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.HTTP;

import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.GOTO;

import mypkg.control.Command;
import mypkg.control.CommandResult;
import mypkg.service.UserGenreMapsService;
import mypkg.vo.MypageVO;
import mypkg.vo.UserGenreMapsVO;

public class UserGenreMapsCommand implements Command {

	// private static final HttpServletRequest = null;

	CommandResult commandResult = null;
	UserGenreMapsService userGenreMapsService = new UserGenreMapsService();

	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UserGenreMapsCommand 시작 ");

		String todo = request.getParameter("todo");
		HttpSession session = request.getSession();
		long CurrentUser_facebookID = (long) session.getAttribute("CurrentUser");
		System.out.println("UserGenreMapsCommand 시작 ");

		if (todo.equals("")) {
			System.out.println("기존 회원 또는 다시 평가하기 실행");
			this.doShowToonByUserSelectedGenre(request, CurrentUser_facebookID);

		} else if (todo.equals("addUserGenre")) {
			System.out.println("신규유져가 장르 선택할때");

			// this.doUserGenreMaps(request,CurrentUser_facebookID);
			// 장르 삭제 7.17 희철
			this.DelUserGenre(request, CurrentUser_facebookID);
			// 장르 입력 희철
			this.UserGenreMapsSet(request, CurrentUser_facebookID);
			// 태균 웹툰 별점 보여주기
			this.doShowToonByUserSelectedGenre(request, CurrentUser_facebookID);
			
		} else if (todo.equals("infinite")) {
			
			commandResult = new CommandResult("application/json", this.doShowToonByUserSelectedGenreByAjax(request, CurrentUser_facebookID));
		}
		return commandResult;
	}

	// 장르삭제 7.17 희철
	private void DelUserGenre(HttpServletRequest request, long CurrentUser_facebookID) {

		userGenreMapsService.DelUserGenre(request, CurrentUser_facebookID);
	}

	// 장르 입력 희철
	private void UserGenreMapsSet(HttpServletRequest request, long CurrentUser_facebookID) {

		userGenreMapsService.UserGenreMapsSet(request, CurrentUser_facebookID);
	}

	// 태균 무한스크롤 Gson 14.07.27
	public String doShowToonByUserSelectedGenreByAjax(HttpServletRequest request, long CurrentUser_facebookID) {
		 String num = request.getParameter("count");
		 System.out.println("무한 스크롤 카운트값 : "+num);
		Gson gSon = new Gson();
		return gSon.toJson(userGenreMapsService.returnAllToonByUserSelectedGenre(CurrentUser_facebookID , num));
	}

	// 태균 선택한 장르의 웹툰 보여주기 14.07.27
	public void doShowToonByUserSelectedGenre(HttpServletRequest request, long CurrentUser_facebookID) {
		// request.setAttribute("showWebtoons",
		// userGenreMapsService.ShowToonByUserSelectedGenre(CurrentUser_facebookID));
		commandResult = new CommandResult("/WEB-INF/jsp/showToonByUserSelectedGenre.jsp");

	}

}
