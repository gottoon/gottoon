package mypkg.vo;

// soo 웹툰 추천
public class RecommenderWebtoonVO {
	private int webtoonId;
	private int relativeWebtoonScore;
	
	// 생성자 1번
	public RecommenderWebtoonVO(int webtoonId, int relativeWebtoonScore) {
		this.webtoonId = webtoonId;
		this.relativeWebtoonScore = relativeWebtoonScore;
	}

	public int getWebtoonId() {
		return webtoonId;
	}

	public void setWebtoonId(int webtoonId) {
		this.webtoonId = webtoonId;
	}

	public int getRelativeWebtoonScore() {
		return relativeWebtoonScore;
	}

	public void setRelativeWebtoonScore(int relativeWebtoonScore) {
		this.relativeWebtoonScore = relativeWebtoonScore;
	}
}
