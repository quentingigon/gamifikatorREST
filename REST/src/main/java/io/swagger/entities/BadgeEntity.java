package io.swagger.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BadgeEntity {

	@Id
	private int id;
	private String name;
	private String apiToken;
	private int level;
	private String iconName;

	public BadgeEntity(String name, String apiToken, int level, String iconName) {
		this.name = name;
		this.apiToken = apiToken;
		this.level = level;
		this.iconName = iconName;
	}

	public BadgeEntity() {}

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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
}
