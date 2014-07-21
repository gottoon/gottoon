package mypkg.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mypkg.dao.MySqlDAOFactory;
import mypkg.dao.User_Genre_MapsDAO;
import mypkg.vo.UserGenreMapsVO;
import mypkg.vo.WebtoonVO;

public class UserGenreMapsService {
	int genresId = 0;
	// 유저 선택장르 DB입력  희철 7.20
	public void UserGenreMapsSet(HttpServletRequest request,
			long CurrentUser_facebookID) {

		String[] userGenreMaps = request.getParameterValues("genrechek");

		for (String userGenre : userGenreMaps) {
			genresId = Integer.parseInt(userGenre);
			UserGenreMapsVO ugm = new UserGenreMapsVO(CurrentUser_facebookID,
					genresId);
			this.doAddUserSelectedGenre(ugm);
		}
	}

	public User_Genre_MapsDAO getUserGenreMaps() {
		MySqlDAOFactory factory = new MySqlDAOFactory();
		return factory.getUser_Genre_MapsDAO();
	}

	// 유저 선택장르 DB입력 희철
	public void doAddUserSelectedGenre(UserGenreMapsVO userGenreVO) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		User_Genre_MapsDAO userGenreMapsDAO = mysqlFactory
				.getUser_Genre_MapsDAO();

		userGenreMapsDAO.UserGenreMapsSet(userGenreVO);

	}

	// 장르 삭제 7.17 희철
	public void DelUserGenre(HttpServletRequest request,
			long CurrentUser_facebookID) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		User_Genre_MapsDAO userGenreMapsDAO = mysqlFactory
				.getUser_Genre_MapsDAO();

		userGenreMapsDAO.DelUserGenre(CurrentUser_facebookID);
	}

	// 유저가 선택한 장르 불러오기 //7.17 희철
	public List<UserGenreMapsVO> UserGenreMaps(long users_facebookID) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		User_Genre_MapsDAO userGenreMapsDAO = mysqlFactory
				.getUser_Genre_MapsDAO();
		System.out.println("maps불러오기 시작");
		return userGenreMapsDAO.findAllGenreMaps(users_facebookID);
	}
	//2014.07.20 박태균 
	public List<WebtoonVO> findToonByUserSelectedGenre(
			List<UserGenreMapsVO> genres , long user_facebookID_fk) {
		return getUserGenreMaps().findToonByUserSelectedGenre(genres , user_facebookID_fk);
	}
	
	public List<WebtoonVO> ShowToonByUserSelectedGenre(long user_facebookID) {

		return findToonByUserSelectedGenre(findSelectedGenres(user_facebookID) , user_facebookID);

	}
	
	public List<UserGenreMapsVO> findSelectedGenres(long user_facebookID) {
		return getUserGenreMaps().findSelectedGenres(user_facebookID);

	}

	
}
