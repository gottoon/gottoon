package mypkg.vo;

public class KeywordsVO {
	private int keywords_id_pk;
	private String keywords_name;

	public KeywordsVO(int keywords_id_pk, String keywords_name) {
		super();
		this.keywords_id_pk = keywords_id_pk;
		this.keywords_name = keywords_name;
	}

	public int getKeywords_id_pk() {
		return keywords_id_pk;
	}

	public void setKeywords_id_pk(int keywords_id_pk) {
		this.keywords_id_pk = keywords_id_pk;
	}

	public String getKeywords_name() {
		return keywords_name;
	}

	public void setKeywords_name(String keywords_name) {
		this.keywords_name = keywords_name;
	}

}
