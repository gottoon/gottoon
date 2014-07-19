package mypkg.vo;

public class Webtoon_keyword_mapsVO {
	private int webtoon_keyword_maps_id_pk;
	private int webtoons_id_pk;
	private int keywords_id_pk;
	private String webtoons_title;
	private String keywords_name;

	public Webtoon_keyword_mapsVO(int webtoons_id_pk, int keywords_id_pk,
			String webtoons_title, String keywords_name) {
		super();
		this.webtoons_id_pk = webtoons_id_pk;
		this.keywords_id_pk = keywords_id_pk;
		this.webtoons_title = webtoons_title;
		this.keywords_name = keywords_name;
	}

	public int getWebtoon_keyword_maps_id_pk() {
		return webtoon_keyword_maps_id_pk;
	}

	public void setWebtoon_keyword_maps_id_pk(int webtoon_keyword_maps_id_pk) {
		this.webtoon_keyword_maps_id_pk = webtoon_keyword_maps_id_pk;
	}

	public int getWebtoons_id_pk() {
		return webtoons_id_pk;
	}

	public void setWebtoons_id_fk(int webtoons_id_pk) {
		this.webtoons_id_pk = webtoons_id_pk;
	}

	public int getKeywords_id_pk() {
		return keywords_id_pk;
	}

	public void setKeywords_id_pk(int keywords_id_pk) {
		this.keywords_id_pk = keywords_id_pk;
	}

	public String getWebtoons_title() {
		return webtoons_title;
	}

	public void setWebtoons_title(String webtoons_title) {
		this.webtoons_title = webtoons_title;
	}

	public String getKeywords_name() {
		return keywords_name;
	}

	public void setKeywords_name(String keywords_name) {
		this.keywords_name = keywords_name;
	}

}
