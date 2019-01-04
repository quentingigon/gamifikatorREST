package io.swagger.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConditionLevelValueEntity {

	@Id
	private int id;
	private int conditionId;
	private int levelValueId;

	public ConditionLevelValueEntity(int conditionId, int levelValueId) {
		this.conditionId = conditionId;
		this.levelValueId = levelValueId;
	}

	public int getId() {
		return id;
	}

	public int getConditionId() {
		return conditionId;
	}

	public void setConditionId(int conditionId) {
		this.conditionId = conditionId;
	}

	public int getLevelValueId() {
		return levelValueId;
	}

	public void setLevelValueId(int levelValueId) {
		this.levelValueId = levelValueId;
	}
}
