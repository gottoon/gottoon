package mypkg.service;

import java.text.Format;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import mypkg.dao.KeywordDAO;
import mypkg.dao.MySqlDAOFactory;
import mypkg.dao.RecommendDAO;
import mypkg.dao.UserDAO;
import mypkg.dao.User_Genre_MapsDAO;
import mypkg.dao.User_Webtoon_MapsDAO;
import mypkg.dao.WebtoonDAO;
import mypkg.dao.Webtoon_Another_Webtoon_Relative_MapsDAO;
import mypkg.vo.DuplicationExceptVO;
import mypkg.vo.KeywordsCountVO;
import mypkg.vo.MatchingVO;
import mypkg.vo.RecommendWebtoonVO;
import mypkg.vo.RecommenderVO;
import mypkg.vo.RecommenderWebtoonVO;
import mypkg.vo.UserGenreMapsVO;
import mypkg.vo.UserVO;
import mypkg.vo.UserWebtoonMapsVO;

// soo 
public class RecommendService {
	int countSameWebtoons = 0; // 카운트
	int sameWebtoonsScore = 0; // 웹툰 스코어
	int relativeRate = 0; // 연관성 점수
	double recommender_matching = 0.0;
	double relative_matching = 0.0;
	final double averageRelativeScore = 9.0;
	boolean isWebtoonHighRated = true; // 
	
	public List<RecommendWebtoonVO> getRecommendWebtoons(long currentUser_facebookID, String viewfreeValue) {
		// 1. 세션값 불러오기 (유저 facebook ID)

		List<UserWebtoonMapsVO> userReadWebtoons = this.getAllReadWebtoons(currentUser_facebookID); // 2-1. 사용자 읽은 웹툰 다 가져오기
		List<UserWebtoonMapsVO> ratedWebtoons = this.checkWebtoonsRated(userReadWebtoons); // 2-2. 사용자 웹툰 별점확인
		
		// 3. 유저 중 웹툰 비교해서 추천자 선택하고 정렬
		List<RecommenderVO> recommenders = this.chooseSimilarUsers(currentUser_facebookID, ratedWebtoons);
		
		// 4. 사용자, 추천자 웹툰 비교해서 추천웹툰목록 뽑기
		List<DuplicationExceptVO> repeatWebtoonRemove = this.compareToRecommender(currentUser_facebookID, 
				recommenders, userReadWebtoons, ratedWebtoons);

		// 5. 중복제거된 웹툰 객체로 정보 뽑아오기
		List<MatchingVO> completed_webtoons =  this.completeRecommendWebtoons(repeatWebtoonRemove, viewfreeValue);
		
		// 08.01 키워드개수 뽑아오기 추가
		List<KeywordsCountVO> webtoons_keywords = this.compareToKeywords(ratedWebtoons, completed_webtoons);
		
		List<RecommendWebtoonVO> result = this.getWebtoonInfo_Keyword(webtoons_keywords);
		
		
		/*// 6. 추천 웹툰 5개만 뽑아오기
		List<WebtoonVO> infinite = new ArrayList<WebtoonVO>();
		
		int webtoonIndex = 5;
		if (completed.size() < 5) {
			webtoonIndex = completed.size();
		} 
		
		for (int i = 0; i < webtoonIndex; i++) {
			infinite.add(completed.get(i));
		}*/
		
		return result;
	} // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ return
	
	public List<UserGenreMapsVO> getSelectedGenres(long user_facebookID) {
		MySqlDAOFactory mysqlDAOFactory = new MySqlDAOFactory();
		User_Genre_MapsDAO userGenreDAO = mysqlDAOFactory.getUser_Genre_MapsDAO();
		
		return userGenreDAO.getAllSelectedGenres(user_facebookID);
	}
	
	public List<UserWebtoonMapsVO> getAllReadWebtoons(long user_facebookID) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		User_Webtoon_MapsDAO userWebtoonDAO = mysqlFactory.getUser_Webtoon_MapsDAO();
		
		return userWebtoonDAO.getAllReadWebtoons(user_facebookID);
	}
	
	public List<UserWebtoonMapsVO> findHighRatedWebtoons(long user_facebookID) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		User_Webtoon_MapsDAO userWebtoonDAO = mysqlFactory.getUser_Webtoon_MapsDAO();

		return userWebtoonDAO.findHighRatedWebtoons(user_facebookID);
	}
	
	public List<UserWebtoonMapsVO> fineLowRatedWebtoons(long user_facebookID) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		User_Webtoon_MapsDAO userWebtoonDAO = mysqlFactory.getUser_Webtoon_MapsDAO();
		
		return userWebtoonDAO.findLowRatedWebtoons(user_facebookID);
	}

	public List<UserVO> getExceptBlacklistAllUsers() {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		UserDAO userDAO = mysqlFactory.getUserDAO();

		return userDAO.getExceptBlacklistAllUsers();
	}
	
	public RecommendWebtoonVO getRecommendWebtoonInfo(String myWebtoon_title, int otherWebtoon_id, 
				int keywordsCount, String recommender_matching_percent, String relative_matching_percent) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		RecommendDAO recommendDAO = mysqlFactory.getRecommendDAO();
	
		return recommendDAO.getRecommendWebtoonInfo(myWebtoon_title, otherWebtoon_id, keywordsCount, 
					recommender_matching_percent, relative_matching_percent);
	}
	
	public List<Integer> getKeyWords(int webtoon_id) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		KeywordDAO keywordDAO = mysqlFactory.getKeywordDAO();
	
		return keywordDAO.getKeyWords(webtoon_id);
	}
	
	public boolean getViewfree(int webtoon_id) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		WebtoonDAO webtoonDAO = mysqlFactory.getWebtoonsDAO();
	
		return webtoonDAO.getViewfree(webtoon_id);
	}
	
	public int getRelativeRate(int webtoonId, int anotherWebtoonId) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		Webtoon_Another_Webtoon_Relative_MapsDAO relativeWebtoonDAO = mysqlFactory.getWebtoon_another_webtoon_relative_mapsDAO();

		return relativeWebtoonDAO.getWebtoonsRelativeRate(webtoonId, anotherWebtoonId);
	}
	
	public List<UserWebtoonMapsVO> checkWebtoonsRated(List<UserWebtoonMapsVO> allReadWebtoons) {
		List<UserWebtoonMapsVO> highRatedWebtoons = new ArrayList<UserWebtoonMapsVO>();
		List<UserWebtoonMapsVO> lowRatedWebtoons = new ArrayList<UserWebtoonMapsVO>();

		// 2-2-a. 별점 4점 이상 뽑기 - 없을시 3점 이하
		for (int i = 0; i < allReadWebtoons.size(); i++) {
			if (allReadWebtoons.get(i).getUser_webtoon_rate() > 3) {
				highRatedWebtoons.add(allReadWebtoons.get(i));
			} else {
				lowRatedWebtoons.add(allReadWebtoons.get(i));
			}
		}
		
		// 2-2-b. 4점 이상 : true / 3점 이하 : false 값 변경
		if (highRatedWebtoons.isEmpty()) {
			isWebtoonHighRated = false;
			return lowRatedWebtoons;
		} else {
			return highRatedWebtoons;
		}
	}
	
	public List<RecommenderVO> chooseSimilarUsers(long user_facebookID, List<UserWebtoonMapsVO> userRatedWebtoons) {
		List<UserWebtoonMapsVO> anotherRatedWebtoons = new ArrayList<UserWebtoonMapsVO>();
		List<RecommenderVO> recommenders = new ArrayList<RecommenderVO>();
		RecommenderVO recommender = null;
		
		List<UserVO> exceptBlacklistUsers = this.getExceptBlacklistAllUsers();	// 3-1. 블랙리스트 제거유저
		
		// 3-2. 블랙리스트 제거한 유저 한사람씩 forEach문
		for (UserVO exceptBlacklistUser : exceptBlacklistUsers) {
			// 3-2-a. 사용자 ID와 추천자 ID 같으면 건너뛰기
			if (user_facebookID == exceptBlacklistUser.getUsers_facebookID_pk()) {
				continue;
			}
			
			// 3-3-b. 유저 별점값(2-2-b)에 따라 뽑아오는 추천자의 웹툰 별점 달라짐
			if (isWebtoonHighRated == true) {
				anotherRatedWebtoons = this.findHighRatedWebtoons(exceptBlacklistUser.getUsers_facebookID_pk());
			} else {
				anotherRatedWebtoons = this.fineLowRatedWebtoons(exceptBlacklistUser.getUsers_facebookID_pk());
			}
			countSameWebtoons = 0;
			sameWebtoonsScore = 0;
			recommender_matching = 0.0;
			
			// 3-3-c. 추천자 뽑아온 웹툰이 없을시 건너뛰기
			if (anotherRatedWebtoons.isEmpty() == true) {
				continue;
			}
			
			// 3-3-d. 사용자 & 추천자 : 4점이상 or 3점이하 웹툰 비교
			this.compareToWebtoons(userRatedWebtoons, anotherRatedWebtoons);
			
			// 3-3-e. 유저ID, 계산된 카운트, 계산된 점수, 유저가 본 웹툰을 객체로 만들어서 리스트에 넣기.
			//		   유저가 본 웹툰은 사용자 별점이 4점 이상일시 그냥 넣고, 3점 이하일시 유저의 4점 이상 웹툰을 따로 뽑아와서 넣음.
			if (isWebtoonHighRated == true) {
				recommender = new RecommenderVO(exceptBlacklistUser.getUsers_facebookID_pk(), 
						countSameWebtoons, sameWebtoonsScore, anotherRatedWebtoons, recommender_matching);				
			} else {
				anotherRatedWebtoons = this.findHighRatedWebtoons(exceptBlacklistUser.getUsers_facebookID_pk());
				if (anotherRatedWebtoons.isEmpty() == true) {
					continue;
				}
				recommender = new RecommenderVO(exceptBlacklistUser.getUsers_facebookID_pk(), 
						countSameWebtoons, sameWebtoonsScore, anotherRatedWebtoons, recommender_matching);
			}
			
			recommenders.add(recommender);
		} // end for 블랙리스트s
		
		this.sortRecommenders(recommenders); // 3-3. 같이 본 웹툰 개수, 점수로 추천자 정렬
		
		return recommenders;
	}
	
	public List<DuplicationExceptVO> compareToRecommender(long user_facebookID, List<RecommenderVO> recommenders,
			List<UserWebtoonMapsVO> userReadWebtoons, List<UserWebtoonMapsVO> ratedWebtoons) {
		int checkSetSize = 0;
		int checkSetInfo = 0;
		List<DuplicationExceptVO> exceptDuplicationWebtoonsInfo = new ArrayList<DuplicationExceptVO>();
		HashSet<Integer> exceptDuplicationWebtoons = new LinkedHashSet<Integer>(); // 4-1. 중복제거 set
		List<MatchingVO> matchingInfo = new ArrayList<MatchingVO>(); // 매칭정보 리스트
		
		// 4-2. 추천자 한사람씩 forEach문
		for (RecommenderVO recommender : recommenders) {
			// 4-2-a. 사용자가 선택한 장르의 웹툰만 가져오기
			List<UserWebtoonMapsVO> selectedGenreWebtoons = 
					this.compareToGenre(user_facebookID, recommender.getReadWebtoons());
		
			// 4-2-b. 사용자가 본 웹툰 제외
			List<UserWebtoonMapsVO> exceptReadWebtoons = 
					this.exceptReadWebtoon(selectedGenreWebtoons, userReadWebtoons);
			
			// 4-2-c. 위 a,b를 제거하고 남은 추천자의 웹툰이 없을시 건너뛰기
			if (exceptReadWebtoons.isEmpty() == true) {
				continue;
			}

			// 4-2-d. 사용자, 추천자 웹툰들 연관성 점수 부여 계산
			List<RecommenderWebtoonVO> recommendWebtoons = 
					this.sumRelativeScore(ratedWebtoons, exceptReadWebtoons);
			// 4-2-e.
			if (isWebtoonHighRated == true) {
				this.sortRecommendWebtoons(recommendWebtoons); // 추천자 웹툰을 연관성 점수 기반으로 내림차순
			} else {
				this.reverseRecommendWebtoons(recommendWebtoons); // 추천자 웹툰을 연관성 점수 기반으로 오름차순
			}
			
			// 4-2-f. 중복제거하기 : Linked-HashSet에 위에 계산된 추천자 웹툰 ID 넣기
			for (int k = 0; k < recommendWebtoons.size(); k++) {
				exceptDuplicationWebtoons.add(recommendWebtoons.get(k).getWebtoonId());
				checkSetSize = exceptDuplicationWebtoons.size();
				
				// 중복 제거 했는지 check해서 매칭값 넣기
				if ((checkSetSize - 1) == checkSetInfo) {
					matchingInfo.add(new MatchingVO(recommender.getRecommender_matching(), 
							recommendWebtoons.get(k).getRelative_matching()));
					checkSetInfo++;
				}
				
				System.out.println("연관성 점수 : "+recommendWebtoons.get(k).getRelativeWebtoonScore()
						+ "추천매칭 : " + recommender.getRecommender_matching() 
						+ "연관성 매칭 : " + recommendWebtoons.get(k).getRelative_matching());
			}
			
			exceptDuplicationWebtoonsInfo.add(new DuplicationExceptVO(exceptDuplicationWebtoons, matchingInfo));
		} // end for (one user)
		
		return exceptDuplicationWebtoonsInfo;
	}
	
	public List<MatchingVO> completeRecommendWebtoons(List<DuplicationExceptVO> repeatWebtoonRemove, String viewfreeValue) {
		List<MatchingVO> completeRecommendWebtoons = new ArrayList<MatchingVO>(); // 완료된 추천웹툰
//		int filteringWebtoonInfo = 0; // 07.20 희철, 08.01 수정
		MatchingVO filteringWebtoonInfo;
		
		for (int i = 0; i < repeatWebtoonRemove.size(); i++) {
			int iterIndex = -1;
			
			Iterator<Integer> iter = repeatWebtoonRemove.get(i).getExceptDuplicationWebtoons().iterator();
			while (iter.hasNext()) {
				// 08.01 soo 웹툰 5개 되면 Iter 빠져나가기
				if (completeRecommendWebtoons.size() > 4) {
					break;
				}

				int recommendWebtoon_id = iter.next();
				iterIndex++;
				
				double recommender_matching = repeatWebtoonRemove.get(i).getMatchingInfo().get(iterIndex).getRecommender_matching();
				double relative_matching = repeatWebtoonRemove.get(i).getMatchingInfo().get(iterIndex).getRelative_matching();
				
				
				
				//7.20 희철 우료 / 무료 보기 
				if (viewfreeValue == null || viewfreeValue.equals("null")){
					filteringWebtoonInfo = new MatchingVO(recommendWebtoon_id, recommender_matching, relative_matching);
//				System.out.println(String.valueOf(webtoonInfo.isWebtoons_viewfree()));
				} else if (viewfreeValue.equals(String.valueOf(this.getViewfree(recommendWebtoon_id)))){
					filteringWebtoonInfo = new MatchingVO(recommendWebtoon_id, recommender_matching, relative_matching);
				} else {
					continue;
				}
				completeRecommendWebtoons.add(filteringWebtoonInfo);
			}
		}
		return completeRecommendWebtoons;
	}
	
	
	public List<RecommenderWebtoonVO> sumRelativeScore(List<UserWebtoonMapsVO> ratedWebtoons, 
			List<UserWebtoonMapsVO> exceptReadWebtoons) {
		List<RecommenderWebtoonVO> recommendWebtoons = new ArrayList<RecommenderWebtoonVO>();
		
		// 4-2-d-i. 추천자 웹툰들 for문
		for (int i = 0; i < exceptReadWebtoons.size(); i++) {
			relativeRate = 0;
			relative_matching = 0.0;
			
			// 4-2-d-ii. 사용자 본 웹툰들 for문
			// 			웹툰 같으면 건너뛰기. 다르면 미리 계산된 웹툰의 연관성 점수를 더해줌
			for (int j = 0; j < ratedWebtoons.size(); j++) {
				if (exceptReadWebtoons.get(i).getWebtoons_id_fk() == 
						ratedWebtoons.get(j).getWebtoons_id_fk()) {
					continue;
				}
				
				relativeRate += this.getRelativeRate(exceptReadWebtoons.get(i).getWebtoons_id_fk(),
						ratedWebtoons.get(j).getWebtoons_id_fk());
				 
			}
			
			this.calculateRelativeMatching(ratedWebtoons.size(), relativeRate);
			
			// 4-2-d-iii. 추천자 웹툰ID, 모두 더한 연관성 점수를 객체로 만들어 리스트에 넣기. 
			RecommenderWebtoonVO recommenderWebtoon = 
					new RecommenderWebtoonVO(exceptReadWebtoons.get(i).getWebtoons_id_fk(),
							relativeRate, relative_matching);
			recommendWebtoons.add(recommenderWebtoon);
		}
		return recommendWebtoons;
	}
	
	public void calculateScore(UserWebtoonMapsVO webtoon, UserWebtoonMapsVO anotherWebtoon) {
		//카운트, 점수계산 : 본 웹툰이 있으면 카운트++ / 별점 같으면 2점, 다르면 1점
		if (webtoon.getUser_webtoon_rate() == anotherWebtoon.getUser_webtoon_rate()) {
			sameWebtoonsScore += 2;
		} else {
			sameWebtoonsScore++;
		}
		countSameWebtoons++;
		
		this.calculateRecommenderMatching(webtoon.getUser_webtoon_rate(), anotherWebtoon.getUser_webtoon_rate());
	}
	
	public List<UserWebtoonMapsVO> exceptReadWebtoon(List<UserWebtoonMapsVO> selectedGenreWebtoons, 
			List<UserWebtoonMapsVO> userReadWebtoons) {
		List<UserWebtoonMapsVO> exceptReadWebtoons = new ArrayList<UserWebtoonMapsVO>();

		for (int i = 0; i < selectedGenreWebtoons.size(); i++) {
			for (int j = 0; j < userReadWebtoons.size(); j++) {
				if (selectedGenreWebtoons.get(i).getWebtoons_id_fk() == 
						userReadWebtoons.get(j).getWebtoons_id_fk()) {
					break;
				}
				if (j == userReadWebtoons.size() - 1) {
					exceptReadWebtoons.add(selectedGenreWebtoons.get(i));
				}
			}
		}
		
		return exceptReadWebtoons;
	}
	
	public List<UserWebtoonMapsVO> compareToGenre(long userId, List<UserWebtoonMapsVO> recommenderWebtoons) {
		List<UserGenreMapsVO> selectedGenre = this.getSelectedGenres(userId);
		List<UserWebtoonMapsVO> selectedGenreWebtoons = new ArrayList<UserWebtoonMapsVO>();

		for (int i = 0; i < recommenderWebtoons.size(); i++) {
			for (int j = 0; j < selectedGenre.size(); j++) {
				if (recommenderWebtoons.get(i).getGenres_id_fk() == selectedGenre.get(j).getGenres_id_fk()) {
					selectedGenreWebtoons.add(recommenderWebtoons.get(i));
					break;
				}
			}
		}
		
		return selectedGenreWebtoons;
	}
	
	public void compareToWebtoons(List<UserWebtoonMapsVO> webtoon, List<UserWebtoonMapsVO> anotherWebtoon) {
		for (int i = 0; i < webtoon.size(); i++) {
			for (int j = 0; j < anotherWebtoon.size(); j++) {
				if (webtoon.get(i).getWebtoons_id_fk() == anotherWebtoon.get(j).getWebtoons_id_fk()) {
					this.calculateScore(webtoon.get(i), anotherWebtoon.get(j)); // 카운트,점수계산
					break;
				}
			}
		}
		
		recommender_matching = recommender_matching/webtoon.size();
	}
	
	public void sortRecommenders(List<RecommenderVO> recommenders) {
		Collections.sort(recommenders, new Comparator<RecommenderVO>() {
			public int compare(RecommenderVO arg1, RecommenderVO arg2) {
				return arg1.getCountSameWebtoons() > arg2.getCountSameWebtoons() ? 
						-1 : arg1.getCountSameWebtoons() < arg2.getCountSameWebtoons() ?
								1 : arg1.getSameWebtoonsScore() > arg2.getSameWebtoonsScore() ?
										-1 : arg1.getSameWebtoonsScore() < arg2.getSameWebtoonsScore() ?
												1 : 0;
			}
		});
	}
	
	public void sortRecommendWebtoons(List<RecommenderWebtoonVO> recommendWebtoons) {
		Collections.sort(recommendWebtoons, new Comparator<RecommenderWebtoonVO>() {
			public int compare(RecommenderWebtoonVO arg1, RecommenderWebtoonVO arg2) {
				return arg1.getRelativeWebtoonScore() > arg2.getRelativeWebtoonScore() ?
						-1 : arg1.getRelativeWebtoonScore() < arg2.getRelativeWebtoonScore() ?
								1 : 0;
			}
		});
	}
	
	public void reverseRecommendWebtoons(List<RecommenderWebtoonVO> recommendWebtoons) {
		Collections.sort(recommendWebtoons, new Comparator<RecommenderWebtoonVO>() {
			public int compare(RecommenderWebtoonVO arg1, RecommenderWebtoonVO arg2) {
				return arg1.getRelativeWebtoonScore() > arg2.getRelativeWebtoonScore() ?
						1 : arg1.getRelativeWebtoonScore() < arg2.getRelativeWebtoonScore() ?
								-1 : 0;
			}
		});
	}
	
	// 08.01 키워드 개수 가져오기 추가
	public List<KeywordsCountVO> compareToKeywords(List<UserWebtoonMapsVO> ratedWebtoons, List<MatchingVO> completed) {
		List<Integer> otherWebtoon_keywords = new ArrayList<Integer>();
		List<Integer> myWebtoon_keywords = new ArrayList<Integer>();
		List<KeywordsCountVO> keywordCount_list = new ArrayList<KeywordsCountVO>();
		String myWebtoon_title = null;
		int keywordsCount = 0;
		int topCount = 0;
		
		for (int i = 0; i < completed.size(); i++) {
			otherWebtoon_keywords = this.getKeyWords(completed.get(i).getWebtoon_id());
			topCount = 0;
//			System.out.println("추천웹툰 : " + completed.get(i) + " : "+ otherWebtoon_keywords);
			for (int j = 0; j < ratedWebtoons.size(); j++) {
				myWebtoon_keywords = this.getKeyWords(ratedWebtoons.get(j).getWebtoons_id_fk());
				keywordsCount = 0;
//				System.out.println("내 웹툰 : " + ratedWebtoons.get(j).getWebtoons_title() + " : "+ myWebtoon_keywords);
				for (int k = 0; k < otherWebtoon_keywords.size(); k++) {
					for (int l = 0; l < myWebtoon_keywords.size(); l++) {
						if (otherWebtoon_keywords.get(k).equals(myWebtoon_keywords.get(l))) {
							keywordsCount++;
							break;
						}
					}
				}
				
				if (topCount < keywordsCount) {
					topCount = keywordsCount;
					myWebtoon_title = ratedWebtoons.get(j).getWebtoons_title();
//					System.out.println("내 웹툰 : " + myWebtoon_title + ", 카운트 : " + topCount);
				}
			}
			
			KeywordsCountVO keywordsCountVO = new KeywordsCountVO(myWebtoon_title, completed.get(i), topCount);
			keywordCount_list.add(keywordsCountVO);
		}
		
		return keywordCount_list;
	}
	
	/*public int testKeyword(List<Integer> otherWebtoon_keywords, List<Integer> myWebtoon_keywords) {
		int keywordsCount = 0;
		
		for (int k = 0; k < otherWebtoon_keywords.size(); k++) {
			for (int l = 0; l < myWebtoon_keywords.size(); l++) {
				if (otherWebtoon_keywords.get(k).equals(myWebtoon_keywords.get(l))) {
					keywordsCount++;
					break;
				}
			}
		}
		
		return keywordsCount;
	}*/
	
	public List<RecommendWebtoonVO> getWebtoonInfo_Keyword(List<KeywordsCountVO> webtoons_keywords) {
		List<RecommendWebtoonVO> recommendWebtoons = new ArrayList<RecommendWebtoonVO>();
		
		for (int i = 0; i < webtoons_keywords.size(); i++) {
			String myWebtoon_title = webtoons_keywords.get(i).getMyWebtoon_title();
			int otherWebtoon_id = webtoons_keywords.get(i).getOtherWebtoons().getWebtoon_id();
			int keywordsCount = webtoons_keywords.get(i).getKeywords_count();
			double recommender_matching = webtoons_keywords.get(i).getOtherWebtoons().getRecommender_matching();
			double relative_matching = webtoons_keywords.get(i).getOtherWebtoons().getRelative_matching();
			
//			System.out.println("추천자 : " + recommender_matching);
//			System.out.println("연관성 : " + relative_matching);
//			double average_matching = (recommender_matching * 0.5) + (relative_matching * 0.5);
			
			String recommender_matching_percent = String.format("%.1f", recommender_matching);
			String relative_matching_percent = String.format("%.1f", relative_matching);
			
			recommendWebtoons.add(this.getRecommendWebtoonInfo(myWebtoon_title, otherWebtoon_id, keywordsCount, 
					recommender_matching_percent, relative_matching_percent));
		}
		
		return recommendWebtoons;
	}
	
	public void calculateRecommenderMatching(int myRate, int otherRate) {
		if (isWebtoonHighRated) {
			if (myRate == 5) {
				if (otherRate == 5) {
					recommender_matching += 100.0;
				} else {
					recommender_matching += 87.5;
				}
			} else {
				if (otherRate == 5) {
					recommender_matching += 87.5;
				} else {
					recommender_matching += 100.0;
				}
			}
		} else {
			if (myRate == 1) {
				if (otherRate == 1) {
					recommender_matching += 100.0;
				} else if (otherRate == 2) {
					recommender_matching += 87.5;
				} else {
					recommender_matching += 75.0;
				}
			} else if (myRate == 2) {
				if (otherRate == 1) {
					recommender_matching += 87.5;
				} else if (otherRate == 2) {
					recommender_matching += 100.0;
				} else {
					recommender_matching += 87.5;
				}
			} else {
				if (otherRate == 1) {
					recommender_matching += 75.0;
				} else if (otherRate == 2) {
					recommender_matching += 87.5;
				} else {
					recommender_matching += 100.0;
				}
			}
		}
	}
	
	public void calculateRelativeMatching(int myWebtoonCount, int relativeSumScore) {
		if (isWebtoonHighRated) {
			if (relativeSumScore > (averageRelativeScore * myWebtoonCount)) {
				relative_matching = 100.0;
			} else {
				relative_matching = (relativeSumScore / (averageRelativeScore * myWebtoonCount)) * 100;
			}
		} else {
			if (relativeSumScore > (averageRelativeScore * myWebtoonCount)) {
				relative_matching = 0.0;
			}
			relative_matching = ((averageRelativeScore * myWebtoonCount) - relativeSumScore / (averageRelativeScore * myWebtoonCount)) * 100;
		}
	}
}
