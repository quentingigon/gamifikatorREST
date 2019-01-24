package io.swagger.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class UserBadgesEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private Long badgeId;

	public UserBadgesEntity() {
	}

	public UserBadgesEntity(Long userId, Long badgeId) {
		this.userId = userId;
		this.badgeId = badgeId;
	}

	public Long getId() {
		return id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(Long badgeId) {
		this.badgeId = badgeId;
	}
}
