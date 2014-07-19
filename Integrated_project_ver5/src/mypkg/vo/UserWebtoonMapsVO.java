package mypkg.vo;

public class UserWebtoonMapsVO {
	private long users_facebookID_fk;
	private int webtoons_id_fk;
	private int genres_id_fk;
	private int push_alarms_id_fk;
	private int comment_id_fk;
	private boolean user_webtoon_isRead;
	private int user_webtoon_rate;
	private String webtoons_title; // 2014.07.17 soo 추가
	private String authors_name; // ''
	
	
	
	
	// 2014.07.17 soo 생성자 추가
	// 생성자 1번
	public UserWebtoonMapsVO(long users_facebookID_fk, String webtoons_title,	String authors_name) {
		this.users_facebookID_fk = users_facebookID_fk;
		this.webtoons_title = webtoons_title;
		this.authors_name = authors_name;
	}
	
	// 생성자 2번
	public UserWebtoonMapsVO(long users_facebookID_fk, int webtoons_id_fk, int genres_id_fk,
			boolean user_webtoon_isRead, int user_webtoon_rate) {
		this.users_facebookID_fk = users_facebookID_fk;
		this.webtoons_id_fk = webtoons_id_fk;
		this.genres_id_fk = genres_id_fk;
		this.user_webtoon_isRead = user_webtoon_isRead;
		this.user_webtoon_rate = user_webtoon_rate;
	}

	// 생성자 3번
	public UserWebtoonMapsVO(long users_facebookID_fk, int webtoons_id_fk, int push_alarms_id_fk,
			int comment_id_fk, boolean user_webtoon_isRead, int user_webtoon_rate) {
		this.users_facebookID_fk = users_facebookID_fk;
		this.webtoons_id_fk = webtoons_id_fk;
		this.push_alarms_id_fk = push_alarms_id_fk;
		this.comment_id_fk = comment_id_fk;
		this.user_webtoon_isRead = user_webtoon_isRead;
		this.user_webtoon_rate = user_webtoon_rate;
	}

	public long getUsers_facebookID_fk() {
		return users_facebookID_fk;
	}

	public void setUsers_facebookID_fk(long users_facebookID_fk) {
		this.users_facebookID_fk = users_facebookID_fk;
	}

	public int getWebtoons_id_fk() {
		return webtoons_id_fk;
	}

	public void setWebtoons_id_fk(int webtoons_id_fk) {
		this.webtoons_id_fk = webtoons_id_fk;
	}

	public int getGenres_id_fk() {
		return genres_id_fk;
	}

	public void setGenres_id_fk(int genres_id_fk) {
		this.genres_id_fk = genres_id_fk;
	}

	public int getPush_alarms_id_fk() {
		return push_alarms_id_fk;
	}

	public void setPush_alarms_id_fk(int push_alarms_id_fk) {
		this.push_alarms_id_fk = push_alarms_id_fk;
	}

	public int getComment_id_fk() {
		return comment_id_fk;
	}

	public void setComment_id_fk(int comment_id_fk) {
		this.comment_id_fk = comment_id_fk;
	}

	public boolean isUser_webtoon_isRead() {
		return user_webtoon_isRead;
	}

	public void setUser_webtoon_isRead(boolean user_webtoon_isRead) {
		this.user_webtoon_isRead = user_webtoon_isRead;
	}

	public int getUser_webtoon_rate() {
		return user_webtoon_rate;
	}

	public void setUser_webtoon_rate(int user_webtoon_rate) {
		this.user_webtoon_rate = user_webtoon_rate;
	}

	public String getWebtoons_title() {
		return webtoons_title;
	}

	public void setWebtoons_title(String webtoons_title) {
		this.webtoons_title = webtoons_title;
	}

	public String getAuthors_name() {
		return authors_name;
	}

	public void setAuthors_name(String authors_name) {
		this.authors_name = authors_name;
	}
}
