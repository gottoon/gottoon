package mypkg.vo;

public class RecommendWebtoonVO {
	private int webtoons_id_pk;
	private String webtoons_title;
	private String webtoons_completed;
	private boolean webtoons_viewfree;
	private String webtoons_main_image;
	private String webtoons_url;
	private String webtoons_first_update;
	private String authors_name;
	private String myWebtoon_title;
	private int keywordsCount;
	private String matching_percent;
	
	public RecommendWebtoonVO (int otherWebtoon_id, String webtoons_title,
			String authors_name, String webtoons_completed,
			boolean webtoons_viewfree, String webtoons_main_image,
			String webtoons_url, String webtoons_first_update, 
			String myWebtoon_title, int keywordsCount, String matching_percent) {
		this.webtoons_id_pk = otherWebtoon_id;
		this.webtoons_title = webtoons_title;
		this.authors_name = authors_name;
		this.webtoons_completed = webtoons_completed;
		this.webtoons_viewfree = webtoons_viewfree;
		this.webtoons_main_image = webtoons_main_image;
		this.webtoons_url = webtoons_url;
		this.webtoons_first_update = webtoons_first_update;
		this.myWebtoon_title = myWebtoon_title;
		this.keywordsCount = keywordsCount;
		this.matching_percent = matching_percent;
	}

	public int getWebtoons_id_pk() {
		return webtoons_id_pk;
	}

	public void setWebtoons_id_pk(int webtoons_id_pk) {
		this.webtoons_id_pk = webtoons_id_pk;
	}

	public String getWebtoons_title() {
		return webtoons_title;
	}

	public void setWebtoons_title(String webtoons_title) {
		this.webtoons_title = webtoons_title;
	}

	public String getWebtoons_completed() {
		return webtoons_completed;
	}

	public void setWebtoons_completed(String webtoons_completed) {
		this.webtoons_completed = webtoons_completed;
	}

	public boolean isWebtoons_viewfree() {
		return webtoons_viewfree;
	}

	public void setWebtoons_viewfree(boolean webtoons_viewfree) {
		this.webtoons_viewfree = webtoons_viewfree;
	}

	public String getWebtoons_main_image() {
		return webtoons_main_image;
	}

	public void setWebtoons_main_image(String webtoons_main_image) {
		this.webtoons_main_image = webtoons_main_image;
	}

	public String getWebtoons_url() {
		return webtoons_url;
	}

	public void setWebtoons_url(String webtoons_url) {
		this.webtoons_url = webtoons_url;
	}

	public String getWebtoons_first_update() {
		return webtoons_first_update;
	}

	public void setWebtoons_first_update(String webtoons_first_update) {
		this.webtoons_first_update = webtoons_first_update;
	}

	public String getAuthors_name() {
		return authors_name;
	}

	public void setAuthors_name(String authors_name) {
		this.authors_name = authors_name;
	}

	public String getMyWebtoon_title() {
		return myWebtoon_title;
	}

	public void setMyWebtoon_title(String myWebtoon_title) {
		this.myWebtoon_title = myWebtoon_title;
	}

	public int getKeywordsCount() {
		return keywordsCount;
	}

	public void setKeywordsCount(int keywordsCount) {
		this.keywordsCount = keywordsCount;
	}

	public String getMatching_percent() {
		return matching_percent;
	}

	public void setMatching_percent(String matching_percent) {
		this.matching_percent = matching_percent;
	}
}
