package mypkg.service;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mypkg.dao.MySqlDAOFactory;
import mypkg.dao.User_Genre_MapsDAO;
import mypkg.vo.UserGenreMapsVO;
import mypkg.vo.WebtoonVO;

public class UserGenreMapsService {
	int genresId = 0;

	// 유저 선택장르 DB입력 희철 7.20
	public void UserGenreMapsSet(HttpServletRequest request, long CurrentUser_facebookID) {

		String[] userGenreMaps = request.getParameterValues("genrechek");

		for (String userGenre : userGenreMaps) {
			genresId = Integer.parseInt(userGenre);
			UserGenreMapsVO ugm = new UserGenreMapsVO(CurrentUser_facebookID, genresId);
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
		User_Genre_MapsDAO userGenreMapsDAO = mysqlFactory.getUser_Genre_MapsDAO();

		userGenreMapsDAO.UserGenreMapsSet(userGenreVO);

	}

	// 장르 삭제 7.17 희철
	public void DelUserGenre(HttpServletRequest request, long CurrentUser_facebookID) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		User_Genre_MapsDAO userGenreMapsDAO = mysqlFactory.getUser_Genre_MapsDAO();

		userGenreMapsDAO.DelUserGenre(CurrentUser_facebookID);
	}

	// 유저가 선택한 장르 불러오기 //7.17 희철
	public List<UserGenreMapsVO> UserGenreMaps(long users_facebookID) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		User_Genre_MapsDAO userGenreMapsDAO = mysqlFactory.getUser_Genre_MapsDAO();
		System.out.println("maps불러오기 시작");
		return userGenreMapsDAO.findAllGenreMaps(users_facebookID);
	}

	// 2014.07.20 박태균 웹툰들 return
	public List<WebtoonVO> getToonByUserSelectedGenre(List<UserGenreMapsVO> genres, long user_facebookID_fk, String num) {
		return getUserGenreMaps().findToonByUserSelectedGenre(genres, user_facebookID_fk, num);
	}

	// 2014.07.20 박태균 커멘드로 return
	public List<WebtoonVO> returnAllToonByUserSelectedGenre(long user_facebookID, String num) {

		return getToonByUserSelectedGenre(returnGenresList(user_facebookID), user_facebookID, num);

	}

	// 2014.07.20 박태균 선택한 장르들을 return
	public List<UserGenreMapsVO> returnGenresList(long user_facebookID) {
		return getUserGenreMaps().findSelectedGenres(user_facebookID);

	}

	// 2014.07.20 박태균
//	public String makeGenreStringList(long CurrentUser_facebookID) {
//		Iterator<UserGenreMapsVO> genresIter = returnGenresList(CurrentUser_facebookID).iterator();
//		String selectedGenres = null;
//		boolean button = true;
//		
//		while (genresIter.hasNext()) {
//			
//			UserGenreMapsVO genres = genresIter.next();
//			
//			if(button){
//				selectedGenres = " genre_id_fk = " + genres.getGenres_id_fk();
//				button = false;
//			}
//			
//			if (returnGenresList(CurrentUser_facebookID).size() != 1) {
//			String secondSelectedGenres = "or genre_id_fk = " + genres.getGenres_id_fk() + " ";
//			selectedGenres += secondSelectedGenres;
//			}
//		}
//		return selectedGenres;
//	}

	// public List<WebtoonVO> findToonByUserSelectedGenre(
	// List<UserGenreMapsVO> genresList, long user_facebookID_fk , String num) {
	//
	//
	// Iterator<UserGenreMapsVO> genresIter = genresList.iterator();
	//
	// while (genresIter.hasNext()) {
	//
	// UserGenreMapsVO genres = genresIter.next();
	//
	// String selectedGenres = " genre_id_fk = "+genres.getGenres_id_fk();
	//
	// if( genresList.size() != 1){
	// }else{
	// String secondSelectedGenres =
	// "or genre_id_fk = "+genres.getGenres_id_fk()+" ";
	//
	// selectedGenres += secondSelectedGenres;
	//
	// }
	//
	// }

}
