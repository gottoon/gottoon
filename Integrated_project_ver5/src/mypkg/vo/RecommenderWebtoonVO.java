package mypkg.vo;

// soo 웹툰 추천
public class RecommenderWebtoonVO {
	private int webtoonId;
	private int relativeWebtoonScore;
	private double relative_matching;
	
	// 생성자 1번
	public RecommenderWebtoonVO(int webtoonId, int relativeWebtoonScore, double relative_matching) {
		this.webtoonId = webtoonId;
		this.relativeWebtoonScore = relativeWebtoonScore;
		this.relative_matching = relative_matching;
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

	public double getRelative_matching() {
		return relative_matching;
	}

	public void setRelative_matching(double relative_matching) {
		this.relative_matching = relative_matching;
	}
}
