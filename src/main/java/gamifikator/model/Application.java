package gamifikator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Application implements Serializable {

	@Id
	private String name;
	@ManyToOne
	private User creator;
	private String description;
	private String apiKey;
	private String apiSecret;

	public Application() {}

	public Application(String name, User creator, String description, String apiKey, String apiSecret) {
		this.name = name;
		this.creator = creator;
		this.description = description;
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
	}

	public Application(String name, User creator) {
		this.name = name;
		this.creator = creator;
	}

	public String getName() {
		return name;
	}

	public User getCreator() {
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

	public void setCreator(User creator) {
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
