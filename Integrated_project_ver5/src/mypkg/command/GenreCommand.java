package mypkg.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mypkg.control.Command;
import mypkg.control.CommandResult;
import mypkg.dao.GenreDAO;
import mypkg.dao.MySqlDAOFactory;
import mypkg.service.GenreService;
import mypkg.service.UserGenreMapsService;
import mypkg.vo.GenreVO;
import mypkg.vo.UserGenreMapsVO;

public class GenreCommand implements Command {
	CommandResult commandResult = null;
	GenreService genreService = new GenreService();
	UserGenreMapsService userGenreMapsService = new UserGenreMapsService();
	public CommandResult execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("장르 커맨드 시작");
		String todo = request.getParameter("todo");
		
		HttpSession session = request.getSession();
		long CurrentUser_facebookID = (long) session
				.getAttribute("CurrentUser");
		
		System.out.println("todo = " +todo);
		if (todo.equals("showGenres")) {
			System.out.println("showGenre if 시작 ");
						
			this.doShowGenre(request, response);  //장르 불러오기
			this.doUserGenreMaps(request,CurrentUser_facebookID); // 유저 장르 불러오기 7.17
			
			commandResult = new CommandResult("/WEB-INF/jsp/showGenre.jsp");
		}

		return commandResult;
	}
	// 유저 장르 불러오기  7.17
	public void doUserGenreMaps(HttpServletRequest request,
			long CurrentUser_facebookID){
		List<UserGenreMapsVO> allUserGenreMaps = userGenreMapsService.UserGenreMaps(CurrentUser_facebookID);
		System.out.println("유저장르불러오기");
		request.setAttribute("allusergenremaps", allUserGenreMaps);
		System.out.println(request+"6666666666");
	}
	// DB장르 불러오기
	public void doShowGenre(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("doshowGenre 시작 ");
		List<GenreVO> allGenres = genreService.getAllGenres();
		
		request.setAttribute("allGenres", allGenres);
	}
}
