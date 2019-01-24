package io.swagger.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Object representation of an application
 *
 * */
@Entity
public class ApplicationEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String owner;
	private String createDate;
	private String description;
	private String apiToken;
	private String apiSecret;

	private boolean isDeployed;

	private ArrayList<RuleEntity> rules;
	private ArrayList<BadgeEntity> badges;
	private ArrayList<UserEntity> endusers;

	public ApplicationEntity() {}

	public ApplicationEntity(String name, String owner, String createDate, String description, String apiToken, String apiSecret, boolean isDeployed) {
		this.name = name;
		this.owner = owner;
		this.createDate = createDate;
		this.description = description;
		this.apiToken = apiToken;
		this.apiSecret = apiSecret;
		this.isDeployed = isDeployed;

		this.rules = new ArrayList<>();
		this.badges = new ArrayList<>();
		this.endusers = new ArrayList<>();
	}

	public ApplicationEntity(String name, String owner, boolean isDeployed) {
		this.name = name;
		this.owner = owner;
		this.isDeployed = isDeployed;

		this.rules = new ArrayList<>();
		this.badges = new ArrayList<>();
		this.endusers = new ArrayList<>();
	}

	public Long getId() {
		return id;
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

	public ArrayList<RuleEntity> getRules() {
		return rules;
	}

	public void setRules(ArrayList<RuleEntity> rules) {
		this.rules = rules;
	}

	public void addToRules(RuleEntity rule) {
		if (rules == null) {
			rules = new ArrayList<>();
		}
		rules.add(rule);
	}

	public ArrayList<UserEntity> getEndusers() {
		return endusers;
	}

	public void setEndusers(ArrayList<UserEntity> endusers) {
		this.endusers = endusers;
	}

	public void addToEndusers(UserEntity enduser) {
		if (endusers == null) {
			endusers = new ArrayList<>();
		}
		endusers.add(enduser);
	}

	public ArrayList<BadgeEntity> getBadges() {
		return badges;
	}

	public void setBadges(ArrayList<BadgeEntity> badges) {
		this.badges = badges;
	}

	public void addToBadges(BadgeEntity badge) {
		if (badges == null) {
			badges = new ArrayList<>();
		}
		badges.add(badge);
	}
}
