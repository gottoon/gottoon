package mypkg.vo;

public class UserVO {
	private long users_facebookID_pk;
	private String users_photo;
	private String users_name;
	private String users_email;
	private String users_grade;
	private boolean users_blacklist;

	// 생성자 1번
	public UserVO(long users_facebookID_pk) {
		this.users_facebookID_pk = users_facebookID_pk;
	}

	// 생성자 2번
	public UserVO(String users_grade) {
		this.users_grade = users_grade;
	}

	// 생성자 3번
	public UserVO(long users_facebookID_pk, String users_name) {
		this.users_facebookID_pk = users_facebookID_pk;
		this.users_name = users_name;
	}

	// 생성자 4번
	public UserVO(String users_name, String users_email) {
		this.users_name = users_name;
		this.users_email = users_email;
	}

	// 생성자 5번
	public UserVO(long users_facebookID_pk, String users_name,
			String users_email, String users_grade, boolean users_blacklist) {
		this.users_facebookID_pk = users_facebookID_pk;
		this.users_name = users_name;
		this.users_email = users_email;
		this.users_grade = users_grade;
		this.users_blacklist = users_blacklist;
	}

	public UserVO(long users_facebookID_pk, String users_photo,
			String users_name, String users_email, String users_grade,
			boolean users_blacklist) {
		this.users_facebookID_pk = users_facebookID_pk;
		this.users_photo = users_photo;
		this.users_name = users_name;
		this.users_email = users_email;
		this.users_grade = users_grade;
		this.users_blacklist = users_blacklist;
	}

	public long getUsers_facebookID_pk() {
		return users_facebookID_pk;
	}

	public void setUsers_facebookID_pk(long users_facebookID_pk) {
		this.users_facebookID_pk = users_facebookID_pk;
	}

	public String getUsers_name() {
		return users_name;
	}

	public void setUsers_name(String users_name) {
		this.users_name = users_name;
	}

	public String getUsers_email() {
		return users_email;
	}

	public void setUsers_email(String users_email) {
		this.users_email = users_email;
	}

	public String getUsers_grade() {
		return users_grade;
	}

	public void setUsers_grade(String users_grade) {
		this.users_grade = users_grade;
	}

	public boolean isUsers_blacklist() {
		return users_blacklist;
	}

	public void setUsers_blacklist(boolean users_blacklist) {
		this.users_blacklist = users_blacklist;
	}

	public String getUsers_photo() {
		return users_photo;
	}

	public void setUsers_photo(String users_photo) {
		this.users_photo = users_photo;
	}
}
