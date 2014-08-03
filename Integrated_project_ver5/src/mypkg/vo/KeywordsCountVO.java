package mypkg.vo;

public class KeywordsCountVO {
	private String myWebtoon_title;
	private MatchingVO otherWebtoons;
	private int keywords_count;
	
	public KeywordsCountVO (String myWebtoon_title, MatchingVO otherWebtoons, int keywords_count) {
		this.myWebtoon_title = myWebtoon_title;
		this.otherWebtoons = otherWebtoons;
		this.keywords_count = keywords_count;
	}

	public String getMyWebtoon_title() {
		return myWebtoon_title;
	}

	public void setMyWebtoon_title(String myWebtoon_title) {
		this.myWebtoon_title = myWebtoon_title;
	}

	public MatchingVO getOtherWebtoons() {
		return otherWebtoons;
	}

	public void setOtherWebtoons(MatchingVO otherWebtoons) {
		this.otherWebtoons = otherWebtoons;
	}

	public int getKeywords_count() {
		return keywords_count;
	}

	public void setKeywords_count(int keywords_count) {
		this.keywords_count = keywords_count;
	}
}
