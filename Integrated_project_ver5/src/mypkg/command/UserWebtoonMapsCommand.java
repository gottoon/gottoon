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
import mypkg.service.UserWebtoonMapsService;
import mypkg.service.WebtoonService;
import mypkg.vo.UserWebtoonMapsVO;
import mypkg.vo.WebtoonVO;

//박태균 
public class UserWebtoonMapsCommand implements Command {
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		CommandResult commandResult = null;
		System.out.println("UserWebtoonMapsCommand 실행  ");

		String todo = request.getParameter("todo");
		
		if (todo.equals("insertWebtoon")) {//2014.07.20 박태균  웹툰  CRUD 
System.out.println("UserWebtoonMapsCommand시작 ,doInsertWebtoon실행 ");
			
			this.doInsertWebtoon(request);
			
			PrintWriter out = response.getWriter();
			out.print(this.doCountWebtoon(request));
			out.flush();
			out.close();
			
			System.out.println("선택한 웹툰의 갯수 : "+this.doCountWebtoon(request));
			String comment = "ajax 성공 "; 
			commandResult = new CommandResult("text/plain;charset=UTF-8", comment);
		}else if(todo.equals("getCount")){//2014.07.20 박태균 웹툰 총갯수구하기 (게이지바 , 사용자등급에 사용)
				PrintWriter out = response.getWriter();
				out.print(this.doCountWebtoon(request));
				out.flush();
				out.close();
				System.out.println("선택한 웹툰의 갯수 : "+this.doCountWebtoon(request));
				String comment = "자동 ajax 성공 "; 
				commandResult = new CommandResult("text/plain;charset=UTF-8", comment);
		}else if(todo.equals("loadingWebtoon")) {//2014.07.20 박태균 웹툰 총갯수구하기 사용자가 선택했던 장르의 웹툰 불러오기
					
					request.setAttribute("loadingWebtoons", doLoadingWebtoon(request));
					
					
					commandResult = new CommandResult("/WEB-INF/jsp/TestStarPoint.jsp");

		} else if (todo.equals("seeReserve")) { // 2014.07.10 soo 찜한 웹툰 todo
												// 걸러내기
			String result = this.doInsertReserveWebtoon(request);

			commandResult = new CommandResult("text/plain;charset=UTF-8", result);
		
		} else if (todo.equals("authorLabel")) { // 2014.07.17 soo 라벨 비교 작가 뽑아오기
			String result = this.doGetWebtoonAuthors(request);

			if (result == null) {
				commandResult = new CommandResult("text/plain", "");
			} else {
				commandResult = new CommandResult("text/plain", result);
			}
		}

		return commandResult;

	}
	// 2014.07.12 박태균 유저가 선택했던 , 찜을 제외한 모든 웹툰

		public List<UserWebtoonMapsVO> doLoadingWebtoon(HttpServletRequest request){
			HttpSession session = request.getSession();
			long CurruntUser_facebookID = (long) session.getAttribute("CurrentUser");
			
			UserWebtoonMapsService service = new UserWebtoonMapsService();
		
			return service.LoadingWebtoon(CurruntUser_facebookID);
		}
	
	// 2014.07.12 박태균 : 웹툰 카운트 (게이지 바)
	public int doCountWebtoon(HttpServletRequest request){
		HttpSession session = request.getSession();
		long CurruntUser_facebookID = (long) session.getAttribute("CurrentUser");
		
		UserWebtoonMapsService service = new UserWebtoonMapsService();

		return service.countWebtoon(CurruntUser_facebookID);
	}
	
	

	// 2014.07.12 박태균 : 사용자가 선택한 웹툰의 CRUD
	public List<WebtoonVO> doInsertWebtoon(HttpServletRequest request) {
		HttpSession session = request.getSession();
		long CurruntUser_facebookID = (long) session.getAttribute("CurrentUser");
		
		int user_webtoon_rate  = Integer.parseInt(request.getParameter("rate"));
		int webtoons_id = Integer.parseInt(request.getParameter("Id"));
		
		UserWebtoonMapsService service = new UserWebtoonMapsService();
		
		return service.saveWebtoon(CurruntUser_facebookID, user_webtoon_rate, webtoons_id);
	}
	
	// 2014.07.14 soo 찜한 웹툰 정보 넣기 수정
	public String doInsertReserveWebtoon(HttpServletRequest request) {
		HttpSession session = request.getSession();
		long CurruntUser_facebookID = (long) session.getAttribute("CurrentUser");
		
		int webtoons_id = Integer.parseInt(request.getParameter("webtoon_id"));
		
		UserWebtoonMapsService service = new UserWebtoonMapsService();
		return service.doInsertReserveWebtoon(CurruntUser_facebookID, webtoons_id);
	}
	
	// 2014.07.17 soo 별점 4점이상 웹툰 작가
	public String doGetWebtoonAuthors(HttpServletRequest request) {
		HttpSession session = request.getSession();
		long curruntUser_facebookID = (long) session.getAttribute("CurrentUser");
		
		String authors_name = request.getParameter("authorsName");

		WebtoonService service = new WebtoonService();
		return service.doGetWebtoonsAuthors(curruntUser_facebookID, authors_name);
	}
}
