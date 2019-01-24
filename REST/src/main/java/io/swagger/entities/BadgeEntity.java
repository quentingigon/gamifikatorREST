package io.swagger.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="BADGE_ENTITY")
public class BadgeEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String apiToken;
	private int level;
	private String icon;

	public BadgeEntity() {}

	public BadgeEntity(String name, String apiToken, int level, String icon) {
		this.name = name;
		this.apiToken = apiToken;
		this.level = level;
		this.icon = icon;
	}

	public Long getId() {
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
