package mypkg.control;

import org.json.JSONObject;

public class CommandResult {
	private String viewURL;
	private String contentType;
	private String content;
	private JSONObject jsonObject;

	public CommandResult(String viewURL) {
		this(viewURL, null, null);
	}

	public CommandResult(String contentType, String content) {
		this(null, contentType, content);
	}

	public CommandResult(String contentType, JSONObject jsonObject) {
		super();
		this.contentType = contentType;
		this.jsonObject = jsonObject;
	}

	private CommandResult(String viewURL, String contentType, String content) {
		this.viewURL = viewURL;
		this.contentType = contentType;
		this.content = content;
	}

	public String getViewURL() {
		return viewURL;
	}

	public void setViewURL(String viewURL) {
		this.viewURL = viewURL;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public boolean hasView() {
		return (viewURL != null ? true : false);
	}
}
