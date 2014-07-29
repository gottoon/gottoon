package mypkg.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.google.gson.Gson;

import mypkg.dao.AuthorDAO;
import mypkg.dao.MySqlDAOFactory;
import mypkg.dao.User_Webtoon_MapsDAO;
import mypkg.dao.WebtoonDAO;
import mypkg.vo.AuthorVO;
import mypkg.vo.WebtoonVO;

public class WebtoonService {

	// 2014.07.11 soo 웹툰 상세보기 - 2014.07.19 수정
	public WebtoonVO doGetWebtoonDetail(long curruntUser_facebookID,
			int webtoon_id) {
		WebtoonVO webtoonDetail = null;

		if (this.checkMyWebtoon(curruntUser_facebookID, webtoon_id)) {
			webtoonDetail = this.getMyWebtoonDetail(curruntUser_facebookID,
					webtoon_id);
		} else {
			webtoonDetail = this.getWebtoonDetail(webtoon_id);
		}

		return webtoonDetail;
	}

	// 2014.07.19 soo 상세보기 작가 가져오기
	public String doGetAuthors(int webtoon_id) {
		List<AuthorVO> authors = this.getAuthors(webtoon_id);

		String authors_name = "";

		for (int i = 0; i < authors.size(); i++) {
			authors_name += (i == 0) ? authors.get(i).getAuthors_name() : ", "
					+ authors.get(i).getAuthors_name();
		}

		return authors_name;
	}

	// 2014.07.17 soo 별점4 이상 본 웹툰 작가 뽑아오기
	public String doGetWebtoonsAuthors(long users_facebookID_pk,
			String authors_name) {
		String author_name = null;
		if (authors_name.indexOf(",") > 0) {
			String[] authors = authors_name.split(",");
			author_name = authors[0];
		} else {
			author_name = authors_name;
		}

		String webtoonAuthor = this.getWebtoonsAuthors(users_facebookID_pk,
				author_name);
		Gson gson = new Gson();

		return gson.toJson(webtoonAuthor);
		// return webtoonAuthor;
	}

	// 2014.07.17 soo 별점4 이상 본 웹툰 작가 뽑아오기 DAO
	public String getWebtoonsAuthors(long users_facebookID_pk,
			String author_name) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		WebtoonDAO webtoonDAO = mysqlFactory.getWebtoonsDAO();

		return webtoonDAO.findHighRatedWebtoonsAuthors(users_facebookID_pk,
				author_name);
	}

	// 2014.07.11 soo (내가 본&찜한) 상세보기 웹툰 정보 뽑아오기 DAO
	public WebtoonVO getMyWebtoonDetail(long curruntUser_facebookID,
			int webtoon_id) {
		MySqlDAOFactory mysqlDAOFactory = new MySqlDAOFactory();
		WebtoonDAO webtoonDAO = mysqlDAOFactory.getWebtoonsDAO();

		return webtoonDAO.getMyWebtoonInfo(curruntUser_facebookID, webtoon_id);
	}

	// 2014.07.11 soo 상세보기 작가 뽑아오기 DAO
	public List<AuthorVO> getAuthors(int webtoon_id) {
		MySqlDAOFactory mysqlDAOFactory = new MySqlDAOFactory();
		AuthorDAO authorDAO = mysqlDAOFactory.getAuthorDAO();

		return authorDAO.getAuthors(webtoon_id);
	}

	// 7.18 영규꺼
	public List<WebtoonVO> getReadToon(long users_facebookID_pk) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		WebtoonDAO webtoonDAO = mysqlFactory.getWebtoonsDAO();

		return webtoonDAO.findReadToon(users_facebookID_pk);
	}

	// 7.18 영규꺼
	public List<WebtoonVO> getNewToon() {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		WebtoonDAO webtoonDAO = mysqlFactory.getWebtoonsDAO();

		return webtoonDAO.findNewToon();
	}

	// 7.18 영규꺼
	public List<WebtoonVO> getWishList(long users_facebookID_pk) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		WebtoonDAO webtoonDAO = mysqlFactory.getWebtoonsDAO();

		return webtoonDAO.findWishList(users_facebookID_pk);
	}

	// 2014.7.16 bj 모든 웹툰 가져오기
	public List<WebtoonVO> doGetAllWebtoons() {
		MySqlDAOFactory mySqlDAOFactory = new MySqlDAOFactory();
		WebtoonDAO webtoonDAO = mySqlDAOFactory.getWebtoonsDAO();

		return webtoonDAO.getAllWebtoons();
	}

	// 2014.07.23 soo 내가 본 웹툰, 찜한 웹툰 체크
	public boolean checkMyWebtoon(long users_facebookID_pk, int webtoon_id) {
		MySqlDAOFactory mySqlDAOFactory = new MySqlDAOFactory();
		User_Webtoon_MapsDAO userWebtoonDAO = mySqlDAOFactory
				.getUser_Webtoon_MapsDAO();

		return userWebtoonDAO.checkMyWebtoon(users_facebookID_pk, webtoon_id);
	}

	// 2014.07.23 soo 걍 웹툰 상세정보 가져오기
	public WebtoonVO getWebtoonDetail(int webtoon_id) {
		MySqlDAOFactory mysqlDAOFactory = new MySqlDAOFactory();
		WebtoonDAO webtoonDAO = mysqlDAOFactory.getWebtoonsDAO();

		return webtoonDAO.getWebtoonInfo(webtoon_id);
	}
	
	public void addWebtoon(WebtoonVO webtoonVO){
		MySqlDAOFactory mysqlDAOFactory = new MySqlDAOFactory();
		WebtoonDAO webtoonDAO = mysqlDAOFactory.getWebtoonsDAO();

		 webtoonDAO.addWebtoon(webtoonVO);
	}
	
}
