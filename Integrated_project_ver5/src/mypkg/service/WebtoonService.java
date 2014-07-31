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
	public List<WebtoonVO> getReadToon(long users_facebookID_pk, String num) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		WebtoonDAO webtoonDAO = mysqlFactory.getWebtoonsDAO();

		return webtoonDAO.findReadToon(users_facebookID_pk, num);
	}

	// 7.18 영규꺼
	public List<WebtoonVO> getNewToon(String num) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		WebtoonDAO webtoonDAO = mysqlFactory.getWebtoonsDAO();

		return webtoonDAO.findNewToon(num);
	}

	// 7.18 영규꺼
	public List<WebtoonVO> getWishList(long users_facebookID_pk, String num) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		WebtoonDAO webtoonDAO = mysqlFactory.getWebtoonsDAO();

		return webtoonDAO.findWishList(users_facebookID_pk, num);
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
	
	
	// 7.31 영규 유저 등급, 카운트 정보 가져오기
	public double[] getWebtoonCount(long users_facebookID_pk) {
		MySqlDAOFactory mysqlDAOFactory = new MySqlDAOFactory();
		WebtoonDAO webtoonDAO = mysqlDAOFactory.getWebtoonsDAO();
		double[] allInfo = new double[3];
		double userGrade = 1.0;
		
		double readWebtoonCount = webtoonDAO.getReadWebtoonCount(users_facebookID_pk);
		double allWebtoonCount= webtoonDAO.getAllWebtoonCount();
		
		double wholeExperiencePoint = (readWebtoonCount / allWebtoonCount) * 100;
		double userExperiencePoint = 0.0;
		
		//모든 웹툰 카운트 대비 읽은 웹툰 카운트
		// 유저 경험치 계산 공식 : ((전체 대비 내가 읽은 웹툰[%] - 등급 구간 최소치[%]) / 등급 구간 차이[%]) * 100
		
		if(readWebtoonCount >= 10 && wholeExperiencePoint < 5.0) {
			userGrade = 2.0;
		} else if(wholeExperiencePoint >= 5.0 && wholeExperiencePoint < 10.0) {
			userExperiencePoint = ((wholeExperiencePoint - 5.0) / 4.999999999999999) * 100;
			userGrade = 3.0;
		} else if(wholeExperiencePoint >= 10.0 && wholeExperiencePoint < 15.5) {
			userExperiencePoint = ((wholeExperiencePoint - 10.0) / 5.499999999999999) * 100;
			System.out.println(userExperiencePoint + "경험치 는");
			userGrade = 4.0;
		} else if(wholeExperiencePoint >= 15.5 && wholeExperiencePoint < 21.0) {
			userExperiencePoint = ((wholeExperiencePoint - 15.5) / 5.499999999999999) * 100;
			userGrade = 5.0;
		} else if(wholeExperiencePoint >= 21.0 && wholeExperiencePoint < 27.5) {
			userExperiencePoint = ((wholeExperiencePoint - 21.0) / 6.499999999999999) * 100;
			userGrade = 6.0;
		} else if(wholeExperiencePoint >= 27.5 && wholeExperiencePoint < 34.0) {
			userExperiencePoint = ((wholeExperiencePoint - 27.5) / 6.499999999999999) * 100;
			userGrade = 7.0;
		} else if(wholeExperiencePoint >= 34.0 && wholeExperiencePoint < 42.0) {
			userExperiencePoint = ((wholeExperiencePoint - 34.0) / 7.999999999999999) * 100;
			userGrade = 8.0;
		} else if(wholeExperiencePoint >= 42.0 && wholeExperiencePoint < 50.0) {
			userExperiencePoint = ((wholeExperiencePoint - 42.0) / 7.999999999999999) * 100;
			userGrade = 9.0;
		} else if(wholeExperiencePoint >= 50.0 && wholeExperiencePoint < 60.0) {
			userExperiencePoint = ((wholeExperiencePoint - 50.0) / 9.999999999999999) * 100;
			userGrade = 10.0;
		} else if(wholeExperiencePoint >= 60.0 && wholeExperiencePoint < 70.0) {
			userExperiencePoint = ((wholeExperiencePoint - 60.0) / 9.999999999999999) * 100;
			userGrade = 11.0;
		} else if(wholeExperiencePoint >= 70.0 && wholeExperiencePoint < 82.5) {
			userExperiencePoint = ((wholeExperiencePoint - 70.0) / 12.499999999999999) * 100;
			userGrade = 12.0;
		} else if(wholeExperiencePoint >= 82.5 && wholeExperiencePoint < 95.0) {
			userExperiencePoint = ((wholeExperiencePoint - 82.5) / 12.499999999999999) * 100;
			userGrade = 13.0;
		} else if(wholeExperiencePoint >= 95.0) {
			userExperiencePoint = 100.0;
			userGrade = 14.0;
		}
		
		//유저가 읽은 웹툰 카운트
		allInfo[0] = readWebtoonCount;
		//카운트 계산 경험치
		allInfo[1] = userExperiencePoint;
		//계산된 유저 등급
		allInfo[2] = userGrade;
		
		return allInfo;
	}
	
}
