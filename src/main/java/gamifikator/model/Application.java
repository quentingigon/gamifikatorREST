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
	private User owner;
	private String description;
	private String apiKey;
	private String apiSecret;
	private boolean isDeployed;

	public Application() {}

	public Application(String name, User owner, String description, String apiKey, String apiSecret, boolean isDeployed) {
		this.name = name;
		this.owner = owner;
		this.description = description;
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
		this.isDeployed = isDeployed;
	}

	public Application(String name, User owner, String description, boolean isDeployed) {
		this.name = name;
		this.owner = owner;
		this.description = description;
		this.isDeployed = isDeployed;
	}
	
	public String getName() {
		return name;
	}

	public User getOwner() {
		return owner;
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

	public boolean isDeployed() {
		return isDeployed;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOwner(User owner) {
		this.owner = owner;
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

	public void setDeployed(boolean deployed) {
		isDeployed = deployed;
	}
}
