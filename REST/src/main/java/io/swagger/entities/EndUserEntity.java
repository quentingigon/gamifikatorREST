package io.swagger.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EndUserEntity {

	@Id
	private int id;
	private String apiToken;
	private String name;

	public EndUserEntity(String apiToken, String name) {
		this.apiToken = apiToken;
		this.name = name;
	}

	public EndUserEntity() {
	}

	public int getId() {
		return id;
	}

	public String getApiToken() {
		return apiToken;
	}

	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
