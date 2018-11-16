package gamifikator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Object representation of an application
 *
 * */
@Entity
public class Application implements Serializable {

	@Id
	private int id;
	private String name;
	@ManyToOne
	private User owner;
	private String creator;
	private String description;
	private String apiKey;
	private String apiSecret;
	private boolean isDeployed;

	public Application() {}

	public Application(String name, User owner, String creator, String description, String apiKey, String apiSecret, boolean isDeployed) {
		this.name = name;
		this.owner = owner;
		this.creator = creator;
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

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public User getOwner() {
		return owner;
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

	public boolean isDeployed() {
		return isDeployed;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOwner(User owner) {
		this.owner = owner;
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

	public void setDeployed(boolean deployed) {
		isDeployed = deployed;
	}
}
