package mypkg.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mypkg.dao.AuthorDAO;
import mypkg.dao.MySqlDAOFactory;
import mypkg.vo.AuthorVO;


public class AuthorService {
	//json 타입으로 가져오기 - bj 7.18
	public String doGetAllAuthorJson(HttpServletResponse response) {
		List<AuthorVO> authorVOs = getAllAuthor();
		List<String> authorsNames = new ArrayList<String>();

		Gson gson = new Gson();

		for (int i = 0; i < authorVOs.size(); i++) {
			String name = authorVOs.get(i).getAuthors_name();
			authorsNames.add(name);
		}
		response.setContentType("text/html;charset=UTF-8");
		String authors = gson.toJson(authorsNames);
		return authors;
	}
	//모든 작가 가져오기 - bj 7.18
	public List<AuthorVO> getAllAuthor() {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		AuthorDAO authorDAO = mysqlFactory.getAuthorDAO();

		return authorDAO.findAuthor();
	}
}
