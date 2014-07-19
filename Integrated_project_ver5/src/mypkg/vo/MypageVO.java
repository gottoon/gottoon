package mypkg.vo;

public class MypageVO {
	private int mypage_id_pk;
	private long users_facebookID_fk;
	private int mypage_onoff;
	private int mypage_recommend_new_onoff;
	
	// 생성자 1번
	public MypageVO(int mypage_onoff) {
		this.mypage_onoff = mypage_onoff;
	}

	public int getMypage_onoff() {
		return mypage_onoff;
	}

	public void setMypage_onoff(int mypage_onoff) {
		this.mypage_onoff = mypage_onoff;
	}
	
	public int getMypage_id_pk() {
		return mypage_id_pk;
	}
	
	public long getUsers_facebookID_fk() {
		return users_facebookID_fk;
	}
	
	public void setMypage_id_pk(int mypage_id_pk) {
		this.mypage_id_pk = mypage_id_pk;
	}
	
	public void setUsers_facebookID_fk(long users_facebookID_fk) {
		this.users_facebookID_fk = users_facebookID_fk;
	}

	public int getMypage_recommend_new_onoff() {
		return mypage_recommend_new_onoff;
	}

	public void setMypage_recommend_new_onoff(int mypage_recommend_new_onoff) {
		this.mypage_recommend_new_onoff = mypage_recommend_new_onoff;
	}
}
