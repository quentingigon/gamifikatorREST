package io.swagger.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AppRuleEntity {

	@Id
	private int id;
	private int appId;
	private int ruleid;

	public AppRuleEntity(int appId, int ruleid) {
		this.appId = appId;
		this.ruleid = ruleid;
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

	public int getRuleid() {
		return ruleid;
	}

	public void setRuleid(int ruleid) {
		this.ruleid = ruleid;
	}
}
