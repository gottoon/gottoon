package mypkg.vo;

public class PublisherVO {
	private int publishers_id_pk;
	private String publishers_name;

	public PublisherVO(int publishers_id_pk, String publishers_name) {
		super();
		this.publishers_id_pk = publishers_id_pk;
		this.publishers_name = publishers_name;
	}

	public int getPublishers_id_pk() {
		return publishers_id_pk;
	}

	public void setPublishers_id_pk(int publishers_id_pk) {
		this.publishers_id_pk = publishers_id_pk;
	}

	public String getPublishers_name() {
		return publishers_name;
	}

	public void setPublishers_name(String publishers_name) {
		this.publishers_name = publishers_name;
	}

}
