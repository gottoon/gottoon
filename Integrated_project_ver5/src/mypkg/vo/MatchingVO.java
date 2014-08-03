package mypkg.vo;

public class MatchingVO {
	private int webtoon_id;
	private double recommender_matching;
	private double relative_matching;
	
	public MatchingVO (double recommender_matching, double relative_matching) {
		this.recommender_matching = recommender_matching;
		this.relative_matching = relative_matching;
	}

	public MatchingVO (int webtoon_id, double recommender_matching, double relative_matching) {
		this.webtoon_id = webtoon_id;
		this.recommender_matching = recommender_matching;
		this.relative_matching = relative_matching;
	}

	public int getWebtoon_id() {
		return webtoon_id;
	}

	public void setWebtoon_id(int webtoon_id) {
		this.webtoon_id = webtoon_id;
	}

	public double getRecommender_matching() {
		return recommender_matching;
	}

	public void setRecommender_matching(double recommender_matching) {
		this.recommender_matching = recommender_matching;
	}

	public double getRelative_matching() {
		return relative_matching;
	}

	public void setRelative_matching(double relative_matching) {
		this.relative_matching = relative_matching;
	}
}
