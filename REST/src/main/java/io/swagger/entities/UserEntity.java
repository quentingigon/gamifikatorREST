package io.swagger.entities;

import io.swagger.model.EndUser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String apiToken;
	private String name;

	public UserEntity(String apiToken, String name) {
		this.apiToken = apiToken;
		this.name = name;
	}

	public UserEntity() {
	}

	public Long getId() {
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

	public EndUser toEndUser() {
		EndUser endUser = new EndUser();
		endUser.setApitoken(apiToken);
		endUser.setBadges(new ArrayList<>());
		endUser.setName(name);
		return endUser;
	}
}
