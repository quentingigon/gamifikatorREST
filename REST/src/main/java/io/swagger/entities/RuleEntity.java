package io.swagger.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RuleEntity {

	@Id
	private int id;
	private String name;
	private String apiToken;

	public RuleEntity(String name, String apiToken) {
		this.name = name;
		this.apiToken = apiToken;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApiToken() {
		return apiToken;
	}

	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}
}
