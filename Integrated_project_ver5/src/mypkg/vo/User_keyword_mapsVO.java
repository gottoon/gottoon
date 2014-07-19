package mypkg.vo;

public class User_keyword_mapsVO {
	private int keywords_id_pk;
	private String keywords_name;
	private int count;
	private String users_name;
	private String webtoons_title;

	public User_keyword_mapsVO(int keywords_id_pk, String keywords_name,
			int count, String users_name, String webtoons_title) {
		super();
		this.keywords_id_pk = keywords_id_pk;
		this.keywords_name = keywords_name;
		this.count = count;
		this.users_name = users_name;
		this.webtoons_title = webtoons_title;
	}

	public int getKeywords_id_pk() {
		return keywords_id_pk;
	}

	public void setKeywords_id_pk(int keywords_id_pk) {
		this.keywords_id_pk = keywords_id_pk;
	}

	public String getKeywords_name() {
		return keywords_name;
	}

	public void setKeywords_name(String keywords_name) {
		this.keywords_name = keywords_name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getUsers_name() {
		return users_name;
	}

	public void setUsers_name(String users_name) {
		this.users_name = users_name;
	}

	public String getWebtoons_title() {
		return webtoons_title;
	}

	public void setWebtoons_title(String webtoons_title) {
		this.webtoons_title = webtoons_title;
	}

}
