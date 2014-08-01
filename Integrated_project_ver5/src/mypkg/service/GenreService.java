package mypkg.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mypkg.dao.GenreDAO;
import mypkg.dao.KeywordDAO;
import mypkg.dao.MySqlDAOFactory;
import mypkg.vo.AuthorVO;
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
	
	public String doGetAllGenreJson() {
		List<GenreVO> genreVOs = getAllGenres();
		List<String> genresNames = new ArrayList<String>();

		Gson gson = new Gson();

		for (int i = 0; i < genreVOs.size(); i++) {
			String name = genreVOs.get(i).getGenres_name();
			genresNames.add(name);
		}
		String genres = gson.toJson(genresNames);
		return genres;
	}
}
