package mypkg.vo;

public class GenreVO {
	private String genres_name;
	private int genres_id;
	private String genres_image;
	
	// 생성자 1번
	public GenreVO(String genres_name) {
		this.genres_name = genres_name;
	}
	
	// 생성자 2번
	public GenreVO(int genres_id, String genres_name) {
		this.genres_id = genres_id;
		this.genres_name = genres_name;
	}
	
	// 생성자 3번 희철 사용
	public GenreVO(int genres_id, String genres_name, String genres_image) {
		this.genres_id = genres_id;
		this.genres_name = genres_name;
		this.genres_image = genres_image;
	}
	

	public String getGenres_name() {
		return genres_name;
	}

	public void setGenres_name(String genres_name) {
		this.genres_name = genres_name;
	}

	public int getGenres_id_pk() {
		return genres_id;
	}

	public void setGenres_id_pk(int genres_id) {
		this.genres_id = genres_id;
	}

	public int getGenres_id() {
		return genres_id;
	}

	public String getGenres_image() {
		return genres_image;
	}

	public void setGenres_id(int genres_id) {
		this.genres_id = genres_id;
	}

	public void setGenres_image(String genres_image) {
		this.genres_image = genres_image;
	}
}
