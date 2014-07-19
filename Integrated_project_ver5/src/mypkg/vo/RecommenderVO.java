package mypkg.vo;

import java.util.List;

// soo 웹툰 추천
public class RecommenderVO /*implements Comparable*/ {
	private long userId;
	private int countSameWebtoons;
	private int sameWebtoonsScore;
	private List<UserWebtoonMapsVO> readWebtoons;
	
	// 생성자 1번
	public RecommenderVO (long userId, int countSameWebtoons, int sameWebtoonsScore, 
			List<UserWebtoonMapsVO> readWebtoons) {
		this.userId = userId;
		this.countSameWebtoons = countSameWebtoons;
		this.sameWebtoonsScore = sameWebtoonsScore;
		this.readWebtoons = readWebtoons;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getCountSameWebtoons() {
		return countSameWebtoons;
	}

	public void setCountSameWebtoons(int countSameWebtoons) {
		this.countSameWebtoons = countSameWebtoons;
	}

	public int getSameWebtoonsScore() {
		return sameWebtoonsScore;
	}

	public void setSameWebtoonsScore(int sameWebtoonsScore) {
		this.sameWebtoonsScore = sameWebtoonsScore;
	}

	public List<UserWebtoonMapsVO> getReadWebtoons() {
		return readWebtoons;
	}

	public void setReadWebtoons(List<UserWebtoonMapsVO> readWebtoons) {
		this.readWebtoons = readWebtoons;
	}
}
