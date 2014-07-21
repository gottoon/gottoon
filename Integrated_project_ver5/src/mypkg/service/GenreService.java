package mypkg.service;

import java.util.List;

import mypkg.dao.GenreDAO;
import mypkg.dao.KeywordDAO;
import mypkg.dao.MySqlDAOFactory;
import mypkg.vo.GenreVO;

public class GenreService {
	
	//getAllGenres-  bj  7.18   희철 사용
	public List<GenreVO> getAllGenres() {
		System.out.println("getAllgenres 시작 ");
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		GenreDAO genreDAO = mysqlFactory.getGenreDAO();

		return genreDAO.findAllGenre();
	}
	
	//doAddGenre - bj 7.18
	
	public String doAddGenre(String genre) {
		MySqlDAOFactory mySqlDAOFactory = new MySqlDAOFactory();
		GenreDAO genreDAO = mySqlDAOFactory.getGenreDAO();
		String resultMent = null;
		int result =  genreDAO.addGenre(genre);
		if(result == 0){
			resultMent = "이미 있는 키워드 에요 ㅠㅠ";
		}else if(result == 1){
			resultMent = "키워드가 등록 됬어요 ^.^! ";
		}
		return resultMent;
	}
}
