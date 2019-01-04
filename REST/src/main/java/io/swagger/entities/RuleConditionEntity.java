package io.swagger.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RuleConditionEntity {

	@Id
	private int id;
	private int ruleId;
	private int conditionId;

	public RuleConditionEntity(int ruleId, int conditionId) {
		this.ruleId = ruleId;
		this.conditionId = conditionId;
	}

	public int getId() {
		return id;
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public int getConditionId() {
		return conditionId;
	}

	public void setConditionId(int conditionId) {
		this.conditionId = conditionId;
	}
}
