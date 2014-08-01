package mypkg.service;

import java.util.Iterator;
import java.util.List;


import mypkg.dao.MySqlDAOFactory;
import mypkg.dao.User_Webtoon_MapsDAO;
import mypkg.dao.WebtoonDAO;
import mypkg.vo.AverageRateVO;
import mypkg.vo.UserWebtoonMapsVO;
import mypkg.vo.WebtoonVO;

public class UserWebtoonMapsService {

	// 2014.07.12 박태균 : ajax값을 받아서 유저가 선택한 퉵툰을 CRUD
	// 2014.07.14 soo : 구조 수정 - CRUD 메소드 따로 뺌
	public List<WebtoonVO> saveWebtoon(long CurruntUser_facebookID,
			int user_webtoon_rate, int webtoons_id) {
		List<WebtoonVO> webtoon_ids = this
				.getReadWebtoons(CurruntUser_facebookID);

		// ver.5 태균 확인!!
		if (0 == user_webtoon_rate) {
			this.deleteReadWebtoon(webtoons_id);// 수정완료
			System.out.println("0점 선택 ,삭제");
			this.calculateAverageRate(webtoons_id, user_webtoon_rate);
			return null;
		} else {

			if (webtoon_ids.isEmpty()) {

				System.out.println(" 첫 웹툰 추가   웹툰아이디 :" + webtoons_id);

				this.addFirstReadWebtoon(webtoons_id, user_webtoon_rate,
						CurruntUser_facebookID);
			} else {
				Iterator<WebtoonVO> webtoonIter = webtoon_ids.iterator();

				while (webtoonIter.hasNext()) {
					if (webtoons_id == webtoonIter.next().getWebtoons_id_pk()) {
						System.out.println("동일한 웹툰 별점수 또는 이미 평가한 웹툰 upDateWebtoon실행");
						this.updateReadWebtoon(webtoons_id, user_webtoon_rate,
								CurruntUser_facebookID);
					} else {
						System.out.println("새로운 웹툰 추가 addReadWebtoon 실행 ");
						this.addReadWebtoon(webtoons_id, user_webtoon_rate,
								CurruntUser_facebookID);
					}
				}
			}
		}

		this.calculateAverageRate(webtoons_id, user_webtoon_rate);

		return (List<WebtoonVO>) webtoon_ids;
	}

	// 2014.07.14 soo 찜한 웹툰 정보 넣어서 성공/실패 여부 리턴 수정
	public String doInsertReserveWebtoon(long users_facebookID_fk,
			int webtoons_id) {
		boolean result = addReserveWebtoon(users_facebookID_fk, webtoons_id);

		if (result == true) {
			return "찜하기 성공!";
//			 return "See Reserve Success!";
		} else {
			return "이미 찜하거나 본 웹툰입니다!";
//			 return "Error!";
		}
	}

	// 2014.07.10 soo 찜한 웹툰 넣는 DAO 불러오기
	public boolean addReserveWebtoon(long users_facebookID_fk,
			int webtoons_id_fk) {
		MySqlDAOFactory mysqlDAOFactory = new MySqlDAOFactory();
		User_Webtoon_MapsDAO userWebtoonMapsDAO = mysqlDAOFactory
				.getUser_Webtoon_MapsDAO();

		return userWebtoonMapsDAO.addReserveWebtoon(users_facebookID_fk,
				webtoons_id_fk);
	}
	
	//

	// 2014.07.2 soo 구조 변경 (첫 읽은 웹툰 별점 추가 - DAO 빼기)
	// 2014.07.20 박태균  신규가입자 첫 웹툰 저장 
	public void addFirstReadWebtoon(int webtoons_id, int user_webtoon_rate,
			long users_facebookID_fk) {
		MySqlDAOFactory mysqlDAOFactory = new MySqlDAOFactory();
		User_Webtoon_MapsDAO userWebtoonMapsDAO = mysqlDAOFactory
				.getUser_Webtoon_MapsDAO();

		userWebtoonMapsDAO.addFirstReadWebtoon(webtoons_id, user_webtoon_rate,
				users_facebookID_fk);
	}

	// 2014.07.14 soo 구조 변경 (읽은 웹툰 별점 추가 - DAO 빼기)
	// 2014.07.20 박태균  웹툰 추가 
	public void addReadWebtoon(int webtoons_id, int user_webtoon_rate,
			long users_facebookID_fk) {
		MySqlDAOFactory mysqlDAOFactory = new MySqlDAOFactory();
		User_Webtoon_MapsDAO userWebtoonMapsDAO = mysqlDAOFactory
				.getUser_Webtoon_MapsDAO();

		userWebtoonMapsDAO.addReadWebtoon(webtoons_id, user_webtoon_rate,
				users_facebookID_fk);
	}

	// 2014.07.14 soo 구조 변경 (읽은 웹툰 가져오기 - DAO 빼기)
	// 2014.07.20 박태균 읽은 웹툰 가져오기 
	public List<WebtoonVO> getReadWebtoons(long users_facebookID_fk) {
		MySqlDAOFactory mysqlDAOFactory = new MySqlDAOFactory();
		User_Webtoon_MapsDAO userWebtoonMapsDAO = mysqlDAOFactory
				.getUser_Webtoon_MapsDAO();

		return userWebtoonMapsDAO.readWebtoon(users_facebookID_fk);
	}

	// 2014.07.14 soo 구조 변경 (읽은 웹툰 별점 수정 - DAO 빼기)
	// 2014.07.20 박태균 읽은 웹툰 수정 (별점 변경 )
	public void updateReadWebtoon(int webtoons_id, int user_webtoon_rate,
			long users_facebookID_fk) {
		MySqlDAOFactory mysqlDAOFactory = new MySqlDAOFactory();
		User_Webtoon_MapsDAO userWebtoonMapsDAO = mysqlDAOFactory
				.getUser_Webtoon_MapsDAO();

		userWebtoonMapsDAO.updateReadWebtoon(webtoons_id, user_webtoon_rate,
				users_facebookID_fk);
	}

	// 2014.07.14 soo 구조 변경 (읽은 웹툰 삭제 - DAO 빼기)
	// 2014.07.20 박태균  평가한 웹툰 삭제 
	public void deleteReadWebtoon(int webtoons_id) {
		MySqlDAOFactory mysqlDAOFactory = new MySqlDAOFactory();
		User_Webtoon_MapsDAO userWebtoonMapsDAO = mysqlDAOFactory
				.getUser_Webtoon_MapsDAO();

		userWebtoonMapsDAO.deleteReadWebtoon(webtoons_id);
	}

	// 2014.07.17 박태균 사용자가 평가한 웹툰들을 소환
	public List<UserWebtoonMapsVO> LoadingWebtoon(long CurruntUser_facebookID) {
		User_Webtoon_MapsDAO user_Webtoon_MapsDAO = new User_Webtoon_MapsDAO();

		return user_Webtoon_MapsDAO
				.readWebtoonWithOUtIsRead(CurruntUser_facebookID);
	}
	
	

	// 2014.07.17 박태균 유저가 본 웹툰중 찜한 웹툰을 제외한 모든웹툰
	public List<UserWebtoonMapsVO> readWebtoonWithOUtIsRead(
			long CurruntUser_facebookID) {
		User_Webtoon_MapsDAO userWebtoonMaps = new User_Webtoon_MapsDAO();
		return userWebtoonMaps.readWebtoonWithOUtIsRead(CurruntUser_facebookID);
	}
	
	// 2014.07.12 박태균 : 웹툰 카운트 (게이지 바)
	public int countWebtoon(long CurruntUser_facebookID) {
		User_Webtoon_MapsDAO user_Webtoon_MapsDAO = new User_Webtoon_MapsDAO();

		return user_Webtoon_MapsDAO.countWebtoon(CurruntUser_facebookID);
	}
	
	// 2014.07.14 soo 평균별점 계산
	public void calculateAverageRate(int webtoons_id, int user_webtoon_rate) {
		AverageRateVO averageRateVO = this.getRateOfReadWebtoon(webtoons_id);
		if (averageRateVO.getCountUsers() == 0 || averageRateVO.getSumRate() == 0) {
			System.out.println("평균별점 : " + 0);
			updateWebtoonRate(webtoons_id, 0);
		} else {
			double averageRate = (double) averageRateVO.getSumRate()
					/ averageRateVO.getCountUsers();
			
			System.out.println("평균별점 : " + averageRate);
			updateWebtoonRate(webtoons_id, averageRate);
		}
	}

	// 2014.07.14 soo 해당 웹툰 별점 가져오기
	public AverageRateVO getRateOfReadWebtoon(int webtoons_id) {
		MySqlDAOFactory mysqlDAOFactory = new MySqlDAOFactory();
		User_Webtoon_MapsDAO userWebtoonMapsDAO = mysqlDAOFactory
				.getUser_Webtoon_MapsDAO();

		return userWebtoonMapsDAO.getRateOfReadWebtoon(webtoons_id);
	}

	// 2014.07.14 soo 해당 웹툰 별점 업데이트
	public void updateWebtoonRate(int webtoons_id, double rate) {
		MySqlDAOFactory mysqlDAOFactory = new MySqlDAOFactory();
		WebtoonDAO webtoonDAO = mysqlDAOFactory.getWebtoonsDAO();

		webtoonDAO.updateWebtoonRate(webtoons_id, rate);
	}

	// 7.18 영규꺼
	public int doGetWebtoonCount(long users_facebookID_fk) {
		MySqlDAOFactory mysqlDAOFactory = new MySqlDAOFactory();
		User_Webtoon_MapsDAO userWebtoonMapsDAO = mysqlDAOFactory
				.getUser_Webtoon_MapsDAO();

		return userWebtoonMapsDAO.getWebtoonCount(users_facebookID_fk);
	}
	
}
