package io.swagger.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class RuleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String apiToken;
	private Long badgeId;
	private ArrayList<Long> propertiesIds;

	public RuleEntity() {}

	public RuleEntity(String name, String apiToken) {
		this.name = name;
		this.apiToken = apiToken;
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

	public Long getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(Long badgeId) {
		this.badgeId = badgeId;
	}

	public ArrayList<Long> getPropertiesIds() {
		return propertiesIds;
	}

	public void setPropertiesIds(ArrayList<Long> propertiesIds) {
		this.propertiesIds = propertiesIds;
	}

	public void addToProperties(Long propertyId) {
		if (propertiesIds == null) {
			propertiesIds = new ArrayList<>();
		}
		propertiesIds.add(propertyId);
	}
}
