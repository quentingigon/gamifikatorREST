package io.swagger.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AppEndUserEntity {

	@Id
	private int id;
	private int appId;
	private int endUserId;

	public AppEndUserEntity(int appId, int endUserId) {
		this.appId = appId;
		this.endUserId = endUserId;
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

	public int getEndUserId() {
		return endUserId;
	}

	public void setEndUserId(int endUserId) {
		this.endUserId = endUserId;
	}
}
