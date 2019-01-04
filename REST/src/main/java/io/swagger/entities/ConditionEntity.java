package io.swagger.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConditionEntity {

	@Id
	private int id;
	private String name;
	private String collection;
	private String operator;

	public ConditionEntity(String name, String collection, String operator) {
		this.name = name;
		this.collection = collection;
		this.operator = operator;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
}
