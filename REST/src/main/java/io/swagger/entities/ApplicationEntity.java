package io.swagger.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Object representation of an application
 *
 * */
@Entity
public class ApplicationEntity implements Serializable {

	@Id
	private String name;
	private String owner;
	private String createDate;
	private String description;
	private String apiToken;
	private String apiSecret;
	private boolean isDeployed;

	public ApplicationEntity() {}

	public ApplicationEntity(String name, String owner, String createDate, String description, String apiToken, String apiSecret, boolean isDeployed) {
		this.name = name;
		this.owner = owner;
		this.createDate = createDate;
		this.description = description;
		this.apiToken = apiToken;
		this.apiSecret = apiSecret;
		this.isDeployed = isDeployed;
	}

	public ApplicationEntity(String name, String owner, boolean isDeployed) {
		this.name = name;
		this.owner = owner;
		this.isDeployed = isDeployed;
	}

	public String getName() {
		return name;
	}

	public String getOwner() {
		return owner;
	}

	public String getCreateDate() {
		return createDate;
	}

	public String getDescription() {
		return description;
	}

	public String getApiToken() {
		return apiToken;
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

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	public void setDeployed(boolean deployed) {
		isDeployed = deployed;
	}
}
