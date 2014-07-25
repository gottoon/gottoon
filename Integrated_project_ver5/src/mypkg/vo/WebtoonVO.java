package mypkg.vo;

import java.io.Serializable;

public class WebtoonVO implements Serializable {
	private static final long serialVersionUID = -1230958599451962841L;

	private int webtoons_id_pk;
	private int genre_id_fk;
	private String webtoons_title;
	private String webtoons_summary;
	private String webtoons_update_days;
	private String webtoons_completed;
	private boolean webtoons_viewfree;
	private boolean webtoons_professional;
	private String webtoons_pgrating;
	private String webtoons_publisher;
	private Double webtoons_average_rate;
	private String webtoons_main_image;
	private String webtoons_thumbnail;
	private String webtoons_url;
	private String webtoons_first_update; // 2014.07.11 soo 여기부터
	private String genres_name; 
	private String webtoon_viewfree;
	private String webtoon_professional; 
	private String authors_name; // 여기까지 추가
	private int webtoon_rate;
	
	// 생성자 1번
	public WebtoonVO(int webtoons_id_pk) {
		this.webtoons_id_pk = webtoons_id_pk;
	}
	
	// 생성자 2번
	public WebtoonVO(String webtoons_title,	String webtoons_url) {
		this.webtoons_title = webtoons_title;
		this.webtoons_url = webtoons_url;
	}

	// 생성자 3번
	public WebtoonVO(String webtoons_title, String webtoons_publisher, int webtoons_id_pk) {
		this.webtoons_title = webtoons_title;
		this.webtoons_publisher = webtoons_publisher;
		this.webtoons_id_pk = webtoons_id_pk;
	}
	
	// 7.18 영규꺼 7.22 id 추가
	// 생성자 4번
	public WebtoonVO(int webtoons_id_pk, String webtoons_title, String webtoons_thumbnail,
			String webtoons_url ) {
		this.webtoons_id_pk = webtoons_id_pk;
		this.webtoons_title = webtoons_title;
		this.webtoons_thumbnail = webtoons_thumbnail;
		this.webtoons_url = webtoons_url;
	}
	
	public WebtoonVO(int webtoons_id_pk, String webtoons_title, String webtoons_thumbnail,
			String webtoons_url ,int webtoon_rate ) {
		this.webtoons_id_pk = webtoons_id_pk;
		this.webtoons_title = webtoons_title;
		this.webtoons_thumbnail = webtoons_thumbnail;
		this.webtoons_url = webtoons_url;
		this.webtoon_rate = webtoon_rate;
	}

	// 영규꺼 7.22 id 추가  
	// 생성자 5번 
	public WebtoonVO(int webtoons_id_pk, String webtoons_title, String webtoons_update_days,
			String webtoons_summary, String webtoons_publisher,
			String webtoons_url) {
		this.webtoons_id_pk = webtoons_id_pk;
		this.webtoons_title = webtoons_title;
		this.webtoons_update_days = webtoons_update_days;
		this.webtoons_summary = webtoons_summary;
		this.webtoons_publisher = webtoons_publisher;
		this.webtoons_url = webtoons_url;
	}

	// 2014.07.15 soo 생성자 변경 (아래 삭제한거)
	// 생성자 6번
	public WebtoonVO(int webtoons_id_pk, String webtoons_title, String authors_name, 
			String webtoons_completed, boolean webtoons_viewfree, String webtoons_main_image,
			String webtoons_url, String webtoons_first_update) {
		this.webtoons_id_pk = webtoons_id_pk;
		this.webtoons_title = webtoons_title;
		this.authors_name = authors_name;
		this.webtoons_completed = webtoons_completed;
		this.webtoons_viewfree = webtoons_viewfree;
		this.webtoons_main_image = webtoons_main_image;
		this.webtoons_url = webtoons_url;
		this.webtoons_first_update = webtoons_first_update;
	}
	
	// 생성자 7번
	public WebtoonVO(int genre_id_fk, String webtoons_title,
			String webtoons_summary, String webtoons_update_days,
			String webtoons_completed, boolean webtoons_view_free,
			boolean webtoons_professional, String webtoons_pgrating,
			String webtoons_publisher, Double webtoons_average_rate,
			String webtoons_main_image, String webtoons_thumbnail,
			String webtoons_url) {
		this.genre_id_fk = genre_id_fk;
		this.webtoons_title = webtoons_title;
		this.webtoons_summary = webtoons_summary;
		this.webtoons_update_days = webtoons_update_days;
		this.webtoons_completed = webtoons_completed;
		this.webtoons_viewfree = webtoons_view_free;
		this.webtoons_professional = webtoons_professional;
		this.webtoons_pgrating = webtoons_pgrating;
		this.webtoons_publisher = webtoons_publisher;
		this.webtoons_average_rate = webtoons_average_rate;
		this.webtoons_main_image = webtoons_main_image;
		this.webtoons_thumbnail = webtoons_thumbnail;
		this.webtoons_url = webtoons_url;
	}

	// 2014.07.11 soo 생성자 추가 (웹툰 상세보기) - 내가 본거&찜한거
	// 생성자 8번
	public WebtoonVO(int webtoon_id_pk, String genres_name, String webtoons_title,
			String webtoons_summary, String webtoons_update_days,
			String webtoons_completed, String webtoon_viewfree, String webtoon_professional,
			String webtoons_pgrating, String webtoons_publisher, double webtoons_average_rate,
			String webtoons_main_image, String webtoons_url, String webtoons_first_update, 
			int user_webtoon_rate) {
		this.webtoons_id_pk = webtoon_id_pk;
		this.genres_name = genres_name;
		this.webtoons_title = webtoons_title;
		this.webtoons_summary = webtoons_summary;
		this.webtoons_update_days = webtoons_update_days;
		this.webtoons_completed = webtoons_completed;
		this.webtoon_viewfree = webtoon_viewfree;
		this.webtoon_professional = webtoon_professional;
		this.webtoons_pgrating = webtoons_pgrating;
		this.webtoons_publisher = webtoons_publisher;
		this.webtoons_average_rate = webtoons_average_rate;
		this.webtoons_main_image = webtoons_main_image;
		this.webtoons_url = webtoons_url;
		this.webtoons_first_update = webtoons_first_update;
		this.webtoon_rate = user_webtoon_rate;
	}
	
	// 2014.07.11 soo 생성자 추가 (웹툰 상세보기) - 내가 안본거
	// 생성자 9번
	public WebtoonVO(int webtoon_id_pk, String genres_name, String webtoons_title,
			String webtoons_summary, String webtoons_update_days,
			String webtoons_completed, String webtoon_viewfree, String webtoon_professional,
			String webtoons_pgrating, String webtoons_publisher, double webtoons_average_rate,
			String webtoons_main_image, String webtoons_url, String webtoons_first_update) {
		this.webtoons_id_pk = webtoon_id_pk;
		this.genres_name = genres_name;
		this.webtoons_title = webtoons_title;
		this.webtoons_summary = webtoons_summary;
		this.webtoons_update_days = webtoons_update_days;
		this.webtoons_completed = webtoons_completed;
		this.webtoon_viewfree = webtoon_viewfree;
		this.webtoon_professional = webtoon_professional;
		this.webtoons_pgrating = webtoons_pgrating;
		this.webtoons_publisher = webtoons_publisher;
		this.webtoons_average_rate = webtoons_average_rate;
		this.webtoons_main_image = webtoons_main_image;
		this.webtoons_url = webtoons_url;
		this.webtoons_first_update = webtoons_first_update;
	}
	
	//생성자 10번 - bj 7.18
	public WebtoonVO(int webtoon_id_pk, int genre_id_fk, String webtoons_title,
			String webtoons_summary, String webtoons_update_days,
			String webtoons_completed, boolean webtoons_viewfree,
			boolean webtoons_professional, String webtoons_pgrating,
			String webtoons_publisher, double webtoons_average_rate) {
		this.webtoons_id_pk = webtoon_id_pk;
		this.genre_id_fk = genre_id_fk;
		this.webtoons_title = webtoons_title;
		this.webtoons_summary = webtoons_summary;
		this.webtoons_update_days = webtoons_update_days;
		this.webtoons_completed = webtoons_completed;
		this.webtoons_viewfree = webtoons_viewfree;
		this.webtoons_professional = webtoons_professional;
		this.webtoons_pgrating = webtoons_pgrating;
		this.webtoons_publisher = webtoons_publisher;
		this.webtoons_average_rate = webtoons_average_rate;
	}
	//생성자 11번 - bj 7.18
	public WebtoonVO(int webtoons_id_pk, int genre_id_fk,
			String webtoons_title, String webtoons_summary,
			String webtoons_update_days, String webtoons_completed,
			boolean webtoons_viewfree, boolean webtoons_professional,
			String webtoons_pgrating, String webtoons_publisher,
			Double webtoons_average_rate, String webtoons_thumbnail,
			String webtoons_url, String webtoons_first_update,
			String genres_name) {
		super();
		this.webtoons_id_pk = webtoons_id_pk;
		this.genre_id_fk = genre_id_fk;
		this.webtoons_title = webtoons_title;
		this.webtoons_summary = webtoons_summary;
		this.webtoons_update_days = webtoons_update_days;
		this.webtoons_completed = webtoons_completed;
		this.webtoons_viewfree = webtoons_viewfree;
		this.webtoons_professional = webtoons_professional;
		this.webtoons_pgrating = webtoons_pgrating;
		this.webtoons_publisher = webtoons_publisher;
		this.webtoons_average_rate = webtoons_average_rate;
		this.webtoons_thumbnail = webtoons_thumbnail;
		this.webtoons_url = webtoons_url;
		this.webtoons_first_update = webtoons_first_update;
		this.genres_name = genres_name;
	}


	public int getWebtoon_rate() {
		return webtoon_rate;
	}

	public void setWebtoon_rate(int webtoon_rate) {
		this.webtoon_rate = webtoon_rate;
	}

	public int getWebtoons_id_pk() {
		return webtoons_id_pk;
	}

	public void setWebtoons_id_pk(int webtoons_id_pk) {
		this.webtoons_id_pk = webtoons_id_pk;
	}

	public int getGenre_id_fk() {
		return genre_id_fk;
	}

	public void setGenre_id_fk(int genre_id_fk) {
		this.genre_id_fk = genre_id_fk;
	}

	public String getWebtoons_title() {
		return webtoons_title;
	}

	public void setWebtoons_title(String webtoons_title) {
		this.webtoons_title = webtoons_title;
	}

	public String getWebtoons_summary() {
		return webtoons_summary;
	}

	public void setWebtoons_summary(String webtoons_summary) {
		this.webtoons_summary = webtoons_summary;
	}

	public String getWebtoons_update_days() {
		return webtoons_update_days;
	}

	public void setWebtoons_update_days(String webtoons_update_days) {
		this.webtoons_update_days = webtoons_update_days;
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

	public boolean isWebtoons_professional() {
		return webtoons_professional;
	}

	public void setWebtoons_professional(boolean webtoons_professional) {
		this.webtoons_professional = webtoons_professional;
	}

	public String getWebtoons_pgrating() {
		return webtoons_pgrating;
	}

	public void setWebtoons_pgrating(String webtoons_pgrating) {
		this.webtoons_pgrating = webtoons_pgrating;
	}

	public String getWebtoons_publisher() {
		return webtoons_publisher;
	}

	public void setWebtoons_publisher(String webtoons_publisher) {
		this.webtoons_publisher = webtoons_publisher;
	}

	public Double getWebtoons_average_rate() {
		return webtoons_average_rate;
	}

	public void setWebtoons_average_rate(Double webtoons_average_rate) {
		this.webtoons_average_rate = webtoons_average_rate;
	}

	public String getWebtoons_main_image() {
		return webtoons_main_image;
	}

	public void setWebtoons_mail_image(String webtoons_main_image) {
		this.webtoons_main_image = webtoons_main_image;
	}

	public String getWebtoons_thumbnail() {
		return webtoons_thumbnail;
	}

	public void setWebtoons_thumbnail(String webtoons_thumbnail) {
		this.webtoons_thumbnail = webtoons_thumbnail;
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

	public String getGenres_name() {
		return genres_name;
	}

	public void setGenres_name(String genres_name) {
		this.genres_name = genres_name;
	}

	public String getWebtoon_viewfree() {
		return webtoon_viewfree;
	}

	public void setWebtoon_viewfree(String webtoon_viewfree) {
		this.webtoon_viewfree = webtoon_viewfree;
	}

	public String getWebtoon_professional() {
		return webtoon_professional;
	}

	public void setWebtoon_professional(String webtoon_professional) {
		this.webtoon_professional = webtoon_professional;
	}

	public String getAuthors_name() {
		return authors_name;
	}

	public void setAuthors_name(String authors_name) {
		this.authors_name = authors_name;
	}
}
