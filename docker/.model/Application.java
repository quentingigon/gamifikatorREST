package gamifikator.model;

public class Application extends MongoDBObject {

	private String name;
	private String creator;
	private String description;
	private String apiKey;
	private String apiSecret;

	public Application() {}

	public Application(String name, String creator, String description, String apiKey, String apiSecret) {
		this.name = name;
		this.creator = creator;
		this.description = description;
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
	}

	public Application(String name, String creator) {
		this.name = name;
		this.creator = creator;
	}

	public String getName() {
		return name;
	}

	public String getCreator() {
		return creator;
	}

	public String getDescription() {
		return description;
	}

	public String getApiKey() {
		return apiKey;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}
}
