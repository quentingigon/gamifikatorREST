package io.swagger.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AppBadgeEntity {

	@Id
	private int id;
	private int appId;
	private int badgeId;

	public AppBadgeEntity(int appId, int badgeId) {
		this.appId = appId;
		this.badgeId = badgeId;
	}

	public int getId() {
		return id;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(int badgeId) {
		this.badgeId = badgeId;
	}
}
