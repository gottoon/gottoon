package mypkg.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import mypkg.dao.KeywordDAO;
import mypkg.dao.MySqlDAOFactory;
import mypkg.dao.UserDAO;
import mypkg.dao.WebtoonDAO;
import mypkg.vo.AuthorVO;
import mypkg.vo.KeywordsVO;
import mypkg.vo.UserVO;
import mypkg.vo.Webtoon_keyword_mapsVO;

public class ChartService {

	
	//bj json doGetAllUsersList 7.18 
	public JSONObject doGetAllUsersList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			JSONException {

		List<JSONObject> allusersJson = new ArrayList<JSONObject>();
		List<UserVO> getAllUsersVOList = getAllUsersList();
		JSONObject userJson = null;
		JSONObject responseJson = new JSONObject();

		for (int i = 0; i < getAllUsersVOList.size(); i++) {
			String name = getAllUsersVOList.get(i).getUsers_name();
			long id = getAllUsersVOList.get(i).getUsers_facebookID_pk();
			userJson = new JSONObject();
			userJson.put("name", name);
			userJson.put("id", id);
			allusersJson.add(userJson);

		}

		responseJson.put("responseJson", allusersJson);

		System.out.println("올유저즈 json " + allusersJson.get(0).toString());
		System.out.println("responseJson = " + responseJson.toString());
		String num = "asdf";
		request.setAttribute("responseJson", responseJson.toString());
		request.setAttribute("name", num);

		String testRequest = request.getParameter("responseJson");
		System.out.println("testRequest = " + testRequest);

		return responseJson;

	}
	
	
	//bj getAllUsersList 7.18
	List<UserVO> getAllUsersList() {
		MySqlDAOFactory mySqlDAOFactory = new MySqlDAOFactory();
		UserDAO userDAO = mySqlDAOFactory.getUserDAO();

		return userDAO.getAllUsers();

	}
	
	//bj doGetAllKeywords 7.18
	public List<KeywordsVO> doGetAllKeywords() {
		MySqlDAOFactory mySqlDAOFactory = new MySqlDAOFactory();
		KeywordDAO keywordDAO = mySqlDAOFactory.getKeywordDAO();

		return keywordDAO.getAllKeywords();
	}

	// doAddKeyword - bj 7.18 
	public String doAddKeyword(String keyword) {
		MySqlDAOFactory mySqlDAOFactory = new MySqlDAOFactory();
		KeywordDAO keywordDAO = mySqlDAOFactory.getKeywordDAO();
		String resultMent = null;
		int result = keywordDAO.addKeyword(keyword);
		if (result == 0) {
			resultMent = "이미 있는 키워드 에요 ㅠㅠ";
		} else if (result == 1) {
			resultMent = "키워드가 등록 됬어요 ^.^! ";
		}
		return resultMent;
	}
	
	//doGetKeywordByWebtoonID - bj 7.18 
	public String doGetKeywordByWebtoonID(int webtoonID) {
		MySqlDAOFactory mySqlDAOFactory = new MySqlDAOFactory();
		KeywordDAO keywordDAO = mySqlDAOFactory.getKeywordDAO();

		Gson gson = new Gson();
		String keyword = gson.toJson(keywordDAO
				.getKeywordByWebtoonID(webtoonID));

		return keyword;

	}

	//doGetKeywordByUserID - bj 7.18 
	public String doGetKeywordByUserID(long userID) {
		MySqlDAOFactory mySqlDAOFactory = new MySqlDAOFactory();
		KeywordDAO keywordDAO = mySqlDAOFactory.getKeywordDAO();

		Gson gson = new Gson();
		String keyword = gson.toJson(keywordDAO.getKeywordByUserID(userID));

		return keyword;

	}
	
	//doGetWebtoonsByKeywordID - bj 7.18 

	public String doGetWebtoonsByKeywordID(int keywordID) {

		MySqlDAOFactory mySqlDAOFactory = new MySqlDAOFactory();
		WebtoonDAO webtoonDAO = mySqlDAOFactory.getWebtoonsDAO();

		Gson gson = new Gson();
		String webtoons = gson.toJson(webtoonDAO
				.getWebtoonsByKeywordID(keywordID));

		return webtoons;
	}
	
	
	// doGetWebtoonsByAuthorID - bj 7.18 
	public String doGetWebtoonsByAuthorID(int authorID) {

		MySqlDAOFactory mySqlDAOFactory = new MySqlDAOFactory();
		WebtoonDAO webtoonDAO = mySqlDAOFactory.getWebtoonsDAO();

		Gson gson = new Gson();
		String webtoons = gson.toJson(webtoonDAO.findAuthorToon(authorID));

		return webtoons;
	}
	
//	
	public String doGetAllKeywordJson(){
		System.out.println("doGetAllKeywordJson 서비스 시작");
		
		
		List<KeywordsVO> KeywordsVOs = doGetAllKeywords();
		List<String> keywordsNames = new ArrayList<String>();

		Gson gson = new Gson();

		for (int i = 0; i < KeywordsVOs.size(); i++) {
			String name = KeywordsVOs.get(i).getKeywords_name();
			keywordsNames.add(name);
		}
		String keywords = gson.toJson(keywordsNames);
		System.out.println("키워드 스트링 " + keywords);
		return keywords;
		
	}
	

}
