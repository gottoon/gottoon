package mypkg.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypkg.control.Command;
import mypkg.control.CommandResult;
import mypkg.service.AuthorService;
import mypkg.service.ChartService;
import mypkg.service.GenreService;
import mypkg.service.ManagerService;
import mypkg.service.UserService;
import mypkg.service.WebtoonService;

//다 bj 꺼 
public class ManagerCommand implements Command {
	CommandResult commandResult = null;
	ManagerService managerService = new ManagerService();
	AuthorService authorService = new AuthorService();
	WebtoonService webtoonService = new WebtoonService();
	ChartService dnaService = new ChartService();
	GenreService genreService = new GenreService();
	UserService userService = new UserService();

	@Override
	public CommandResult execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		System.out.println("ManagerCommand execute 시작 ");
		String todo = request.getParameter("todo");
		System.out.println("todo = " + todo);

		if (todo.equals("manager")) {
			commandResult = new CommandResult(
					"/WEB-INF/jsp/manager/managerMenu.jsp");

		} else if (todo.equals("getAuthors")) {
			String authors = doGetAllAuthors(response);
			commandResult = new CommandResult("text/plain", authors);
		} else if (todo.equals("getAllWebtoons")) {
			this.doGetAllWebtoons(request);
		} else if (todo.equals("getAllKeywords")) {
			this.doGetAllKeywords(request);
		} else if (todo.equals("getAllGenres")) {
			this.doGetAllGenres(request);
		} else if (todo.equals("getRelativeRate")) {
		} else if (todo.equals("getAllAuthors")) {
			this.doGetAllAuthors(request);
		} else if (todo.equals("addKeyword")) {
			this.doAddKeyword(request);
		} else if (todo.equals("addGenre")) {
			this.doAddGenre(request);
		} else if (todo.equals("getKeywordByWebtoonID")) {
			this.doGetKeywordByWebtoonID(request);
		} else if (todo.equals("getAllUsers")) {
			this.doGetAllUsers(request);
		} else if (todo.equals("getKeywordByUserID")) {
			this.doGetKeywordByUserID(request);
		} else if (todo.equals("getWebtoonsByKeywordID")) {
			this.doGetWebtoonsByKeywordID(request);
		} else if (todo.equals("getWebtoonsByAuthorID")) {
			this.doGetWebtoonsByAuthorID(request);
		} else if (todo.equals("addWebtoon")) {
			this.doAddWebtoon(request);
		}else if(todo.equals("mansony")){
			this.doGetAllKeywords(request);
			commandResult = new CommandResult("/WEB-INF/jsp/manager/mansonyTest.jsp");
		}

		return commandResult;
	}

	public void doAddWebtoon(HttpServletRequest request) {
		commandResult = new CommandResult("/WEB-INF/jsp/manager/addWebtoon.jsp");
	}

	public String doGetAllAuthors(HttpServletResponse response) {
		return authorService.doGetAllAuthorJson(response);
	}

	public void doGetAllWebtoons(HttpServletRequest request) {
		request.setAttribute("allWebtoons", webtoonService.doGetAllWebtoons());
		commandResult = new CommandResult(
				"/WEB-INF/jsp/manager/webtoonList.jsp");
	}

	public void doGetAllKeywords(HttpServletRequest request) {
		request.setAttribute("allKeywords", dnaService.doGetAllKeywords());
		commandResult = new CommandResult(
				"/WEB-INF/jsp/manager/keywordList.jsp");

	}

	public void doGetAllGenres(HttpServletRequest request) {
		request.setAttribute("allGenres", genreService.getAllGenres());
		commandResult = new CommandResult("/WEB-INF/jsp/manager/genreList.jsp");
	}

	public void doGetAllRelativeRate(HttpServletRequest request) {
	}

	public void doGetAllAuthors(HttpServletRequest request) {
		request.setAttribute("allAuthors", authorService.getAllAuthor());
		commandResult = new CommandResult("/WEB-INF/jsp/manager/authorList.jsp");
	}

	public void doAddKeyword(HttpServletRequest request) {
		System.out.println("doAddKeyword command 시작 ");
		String keyword = request.getParameter("keywordInput");
		System.out.println(keyword);
		String resultMent = dnaService.doAddKeyword(keyword);
		commandResult = new CommandResult("text/plain", resultMent);

	}

	public void doAddGenre(HttpServletRequest request)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		System.out.println("doAddGenre 시작");
		String genre = request.getParameter("genreInput");
		String resultMent = genreService.doAddGenre(genre);

		commandResult = new CommandResult("text/plain", resultMent);
	}

	public void doGetKeywordByWebtoonID(HttpServletRequest request)
			throws UnsupportedEncodingException {
		int webtoonID = Integer.parseInt(request.getParameter("webtoonID"));
		String Webtoon_keyword_mapsVOs = dnaService
				.doGetKeywordByWebtoonID(webtoonID);
		request.setCharacterEncoding("UTF-8");
		commandResult = new CommandResult("text/plain", Webtoon_keyword_mapsVOs);

	}

	public void doGetAllUsers(HttpServletRequest request) {
		request.setAttribute("allUsers", userService.getAllUsers());
		commandResult = new CommandResult("/WEB-INF/jsp/manager/userList.jsp");
	}

	public void doGetKeywordByUserID(HttpServletRequest request)
			throws UnsupportedEncodingException {
		System.out.println("doGetKeywordByUserID 시작 command");

		long userID = Long.parseLong(request.getParameter("userID"));
		System.out.println(userID);
		String keywordsByUserID = dnaService.doGetKeywordByUserID(userID);
		request.setCharacterEncoding("UTF-8");
		commandResult = new CommandResult("text/plain", keywordsByUserID);

	}

	public void doGetWebtoonsByKeywordID(HttpServletRequest request)
			throws UnsupportedEncodingException {
		System.out.println("doGetWebtoonsByKeywordID시작 커맨드");
		System.out.println(request.getParameter("keywordID"));

		int keywordID = Integer.parseInt(request.getParameter("keywordID"));
		System.out.println("keywordID = " + keywordID);
		String Webtoon_keyword_mapsVOs = dnaService
				.doGetWebtoonsByKeywordID(keywordID);
		request.setCharacterEncoding("UTF-8");
		commandResult = new CommandResult("text/plain", Webtoon_keyword_mapsVOs);

	}

	public void doGetWebtoonsByAuthorID(HttpServletRequest request)
			throws UnsupportedEncodingException {
		System.out.println("doGetWebtoonsByAuthorID시작 커맨드");
		System.out.println(request.getParameter("authorID"));

		int authorID = Integer.parseInt(request.getParameter("authorID"));
		System.out.println("authorID = " + authorID);
		String Webtoon_author_mapsVOs = dnaService
				.doGetWebtoonsByAuthorID(authorID);
		request.setCharacterEncoding("UTF-8");
		commandResult = new CommandResult("text/plain", Webtoon_author_mapsVOs);

	}

}
