package mypkg.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.HTTP;

import mypkg.control.Command;
import mypkg.control.CommandResult;
import mypkg.service.AuthorService;
import mypkg.service.ChartService;
import mypkg.service.GenreService;
import mypkg.service.ManagerService;
import mypkg.service.PublisherService;
import mypkg.service.UserService;
import mypkg.service.WebtoonService;
import mypkg.vo.WebtoonVO;

//다 bj 꺼 
public class ManagerCommand implements Command {
	CommandResult commandResult = null;
	ManagerService managerService = new ManagerService();
	AuthorService authorService = new AuthorService();
	WebtoonService webtoonService = new WebtoonService();
	ChartService dnaService = new ChartService();
	GenreService genreService = new GenreService();
	UserService userService = new UserService();
	PublisherService publisherService = new PublisherService();

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
					"/WEB-INF/jsp/manager/managerForm_test.jsp");

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
		} else if (todo.equals("addWebtoonInfo")) {
			this.doAddWebtoonInfo(request);
		} else if (todo.equals("getAllKeywordsJson")) {
			this.doGetAllKeywordsJson();
		} else if (todo.equals("getAllGenresJson")) {
			this.doGetAllGenresJson();
		} else if (todo.equals("getAllPublishersJson")) {
			this.doGetAllPublishersJson();
		} else if (todo.equals("mansony")) {
			this.doGetAllKeywords(request);
			commandResult = new CommandResult(
					"/WEB-INF/jsp/manager/mansonyTest.jsp");
		}

		return commandResult;
	}

	public void doAddWebtoon(HttpServletRequest request) {
		commandResult = new CommandResult("/WEB-INF/jsp/manager/addWebtoon.jsp");
	}

	public String doGetAllAuthors(HttpServletResponse response) {
		return authorService.doGetAllAuthorJson(response);
	}

	public void doGetAllKeywordsJson() {
		System.out.println("doGetAllKeywordsJson 시작");
		String keywords = dnaService.doGetAllKeywordJson();
		commandResult = new CommandResult("text/plain", keywords);
	}
	public void doGetAllGenresJson() {
		System.out.println("doGetAllGenresJson 시작");
		
		String Genres = genreService.doGetAllGenreJson();
		commandResult = new CommandResult("text/plain", Genres);
	}
	public void doGetAllPublishersJson() {
		System.out.println("doGetAllPublishersJson 시작");
		
		String publishers = publisherService.doGetAllPublishersJson();
		commandResult = new CommandResult("text/plain", publishers);
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

	public void doAddWebtoonInfo(HttpServletRequest request) {
		System.out.println("doAddWebtoonInfo 시작");
		// int genre_id_fk =
		// Integer.parseInt(request.getParameter("genre_id_fk"));
		int genre_id_fk = 1;
		String webtoons_title = request.getParameter("webtoons_title");
		String webtoons_summary = request.getParameter("webtoons_summary");
		String webtoons_update_days = request
				.getParameter("webtoons_update_days");
		String webtoons_completed = request.getParameter("webtoons_completed");
		boolean webtoons_professional = Boolean.parseBoolean(request
				.getParameter("webtoons_professional"));
		String webtoons_pgrating = request.getParameter("webtoons_pgrating");
		String webtoons_publisher = request.getParameter("webtoons_publisher");
		// String webtoons_main_image;
		// String webtoons_thumbnail;
		String webtoons_url = request.getParameter("webtoons_url");
		// String webtoons_first_update =
		// request.getParameter("webtoons_first_update");
		String webtoons_first_update = "2232.10.3";
		String webtoon_viewfree = request.getParameter("webtoons_viewfree");
		String authors_name = request.getParameter("authors_name");
		System.out.println("=====================================");
		System.out.println("webtoons_title" + webtoons_title);
		System.out.println("webtoons_summary" + webtoons_summary);
		System.out.println("authors_name" + authors_name);
		System.out.println("webtoons_url" + webtoons_url);
		System.out.println("webtoons_update_days" + webtoons_update_days);
		System.out.println("webtoons_completed" + webtoons_completed);
		System.out.println("webtoon_viewfree" + webtoon_viewfree);
		System.out.println("webtoons_professional" + webtoons_professional);
		System.out.println("webtoons_pgrating" + webtoons_pgrating);

		WebtoonVO webtoonVO = new WebtoonVO(genre_id_fk, webtoons_title,
				webtoons_summary, webtoons_update_days, webtoons_completed,
				webtoons_professional, webtoons_pgrating, webtoons_publisher,
				webtoons_url, webtoons_first_update, webtoon_viewfree,
				authors_name);

		webtoonService.addWebtoon(webtoonVO);

	}

}
