package mypkg.vo;

public class UserGenreMapsVO {
	private int user_genre_maps_id_pk;
	private long users_facebookID_fk;
	private int genres_id_fk;
	private int users_id_count;
	private String genres_name;
	
	// 생성자 1번
	public UserGenreMapsVO(int genres_id_fk){
		this.genres_id_fk = genres_id_fk;
	}

	// 생성자 2번
	public UserGenreMapsVO(long users_facebookID_fk, int genres_id_fk) {
		this.users_facebookID_fk = users_facebookID_fk;
		this.genres_id_fk = genres_id_fk;
	}

	// 생성자 3번
	public UserGenreMapsVO(int users_id_count, String genres_name) {
		this.users_id_count = users_id_count;
		this.genres_name = genres_name;
	}
	
	public int getUser_genre_maps_id_pk() {
		return user_genre_maps_id_pk;
	}

	public void setUser_genre_maps_id_pk(int user_genre_maps_id_pk) {
		this.user_genre_maps_id_pk = user_genre_maps_id_pk;
	}

	public long getuUsers_facebookID_fk() {
		return users_facebookID_fk;
	}

	public void setUsers_facebookID_fk(long users_facebookID_fk) {
		this.users_facebookID_fk = users_facebookID_fk;
	}

	public int getGenres_id_fk() {
		return genres_id_fk;
	}

	public void setGenres_id_fk(int genres_id_fk) {
		this.genres_id_fk = genres_id_fk;
	}

	public int getUsers_count() {
		return users_id_count;
	}

	public void setUsers_count(int users_count) {
		this.users_id_count = users_count;
	}

	public String getGenres_name() {
		return genres_name;
	}

	public void setGenres_name(String genres_name) {
		this.genres_name = genres_name;
	}
}
