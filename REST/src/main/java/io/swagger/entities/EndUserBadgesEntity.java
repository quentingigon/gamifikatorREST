package io.swagger.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EndUserBadgesEntity {

	@Id
	private int id;
	private int userId;
	private int badgeId;

	public EndUserBadgesEntity(int userId, int badgeId) {
		this.userId = userId;
		this.badgeId = badgeId;
	}

	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(int badgeId) {
		this.badgeId = badgeId;
	}
}
