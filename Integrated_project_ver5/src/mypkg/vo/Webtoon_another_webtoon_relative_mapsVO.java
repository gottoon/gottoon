package mypkg.vo;

public class Webtoon_another_webtoon_relative_mapsVO {
	private int webtoons_id_fk;
	private int another_webtoons_id_fk;
	private String webtoons_title;
	private String another_webtoons_title;
	private long relative_rate;

	public Webtoon_another_webtoon_relative_mapsVO(int webtoons_id_fk,
			int another_webtoons_id_fk, String webtoons_title,
			String another_webtoons_name, long relative_rate) {
		super();
		this.webtoons_id_fk = webtoons_id_fk;
		this.another_webtoons_id_fk = another_webtoons_id_fk;
		this.webtoons_title = webtoons_title;
		this.another_webtoons_title = another_webtoons_name;
		this.relative_rate = relative_rate;
	}

	public int getWebtoons_id_fk() {
		return webtoons_id_fk;
	}

	public void setWebtoons_id_fk(int webtoons_id_fk) {
		this.webtoons_id_fk = webtoons_id_fk;
	}

	public int getAnother_webtoons_id_fk() {
		return another_webtoons_id_fk;
	}

	public void setAnother_webtoons_id_fk(int another_webtoons_id_fk) {
		this.another_webtoons_id_fk = another_webtoons_id_fk;
	}

	public String getWebtoons_title() {
		return webtoons_title;
	}

	public void setWebtoons_name(String webtoons_title) {
		this.webtoons_title = webtoons_title;
	}

	public String getAnother_webtoons_title() {
		return another_webtoons_title;
	}

	public void setAnother_webtoons_title(String another_webtoons_title) {
		this.another_webtoons_title = another_webtoons_title;
	}

	public long getRelative_rate() {
		return relative_rate;
	}

	public void setRelative_rate(long relative_rate) {
		this.relative_rate = relative_rate;
	}
}
