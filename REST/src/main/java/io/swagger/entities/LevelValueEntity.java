package io.swagger.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LevelValueEntity {

	@Id
	private int id;
	private int value;

	public LevelValueEntity(int value) {
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
