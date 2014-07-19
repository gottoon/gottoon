package mypkg.vo;

// 다 내꺼 soo 웹툰 추천
public class AverageRateVO {
	private int countUsers;
	private int sumRate;
	
	public AverageRateVO (int countUsers, int sumRate) {
		this.countUsers = countUsers;
		this.sumRate = sumRate;
	}

	public int getCountUsers() {
		return countUsers;
	}

	public void setCountUsers(int countUsers) {
		this.countUsers = countUsers;
	}

	public int getSumRate() {
		return sumRate;
	}

	public void setSumRate(int sumRate) {
		this.sumRate = sumRate;
	}
}
