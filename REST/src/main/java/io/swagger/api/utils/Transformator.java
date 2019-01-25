package io.swagger.api.utils;

import io.swagger.entities.BadgeEntity;
import io.swagger.entities.PropertyEntity;
import io.swagger.entities.RuleEntity;
import io.swagger.entities.UserEntity;
import io.swagger.model.Badge;
import io.swagger.model.Property;
import io.swagger.model.Rule;
import io.swagger.model.User;

/**
 * Regroups functions used to transform entities into models
 * */
public class Transformator {

	public static Rule toRule(RuleEntity ruleEntity) {
		Rule rule = new Rule();
		rule.setApitoken(ruleEntity.getApiToken());
		rule.setName(ruleEntity.getName());
		rule.setBadgeId(ruleEntity.getBadgeId());
		return rule;
	}

	public static BadgeEntity toBadgeEntity(Badge badge) {
		BadgeEntity badgeEntity = new BadgeEntity();
		badgeEntity.setName(badge.getName());
		badgeEntity.setApiToken(badge.getApitoken());
		badgeEntity.setIcon(badge.getIcon());
		badgeEntity.setId(badge.getBadgeId());
		return badgeEntity;
	}

	public static Badge toBadge(BadgeEntity badgeEntity) {
		Badge badge = new Badge();
		badge.setApitoken(badgeEntity.getApiToken());
		badge.setName(badgeEntity.getName());
		badge.setIcon(badgeEntity.getIcon());
		badge.setBadgeId(badgeEntity.getId());
		return badge;
	}

	public static PropertyEntity toPropertyEntity(Property property, String apiToken) {
		PropertyEntity propertyEntity = new PropertyEntity();
		propertyEntity.setName(property.getName());
		propertyEntity.setValue(property.getValue());
		propertyEntity.setOperator(property.getOperator());
		propertyEntity.setApiToken(apiToken);
		propertyEntity.setRuleName(property.getRuleName());
		return propertyEntity;
	}

	public static UserEntity toUserEntity(User user) {
		UserEntity userEntity = new UserEntity();
		userEntity.setApiToken(user.getApitoken());
		userEntity.setName(user.getName());
		return userEntity;
	}

	public static User toUser(UserEntity userEntity) {
		User user = new User();
		user.setApitoken(userEntity.getApiToken());
		user.setName(userEntity.getName());
		return user;
	}
}
