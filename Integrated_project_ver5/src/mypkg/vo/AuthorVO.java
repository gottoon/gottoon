package mypkg.vo;

public class AuthorVO {
	private int authors_id_pk;
	private String authors_name;
	private String webtoons_title;
	
	// 2014.07.11 soo 생성자 추가
	// 생성자 1번
	public AuthorVO(String authors_name) {
		this.authors_name = authors_name;
	}
	
	// 생성자 2번
	public AuthorVO(int authors_id_pk, String authors_name) {
		this.authors_id_pk = authors_id_pk;
		this.authors_name = authors_name;
	}

	public int getAuthors_id_pk() {
		return authors_id_pk;
	}

	public String getAuthors_name() {
		return authors_name;
	}

	public void setAuthors_id_pk(int authors_id_pk) {
		this.authors_id_pk = authors_id_pk;
	}

	public void setAuthors_name(String authors_name) {
		this.authors_name = authors_name;
	}

	public String getWebtoons_title() {
		return webtoons_title;
	}

	public void setWebtoons_title(String webtoons_title) {
		this.webtoons_title = webtoons_title;
	}
}
