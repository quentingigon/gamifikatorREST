package io.swagger.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ApplicationEntity
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-02T12:50:36.574Z")

public class ApplicationEntity {
	@JsonProperty("apiToken")
	private String apiToken = null;

	@JsonProperty("badges")
	@Valid
	private List<BadgeEntity> badges = null;

	@JsonProperty("rules")
	@Valid
	private List<RuleEntity> rules = null;

	@JsonProperty("users")
	@Valid
	private List<UserEntity> users = null;


	public ApplicationEntity(String apiToken) {
		this.apiToken = apiToken;
	}

	public ApplicationEntity apiToken(String apiToken) {
		this.apiToken = apiToken;
		return this;
	}

	/**
	 * Get apiToken
	 * @return apiToken
	 **/
	@ApiModelProperty(value = "")


	public String getApiToken() {
		return apiToken;
	}

	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}

	public ApplicationEntity badges(List<BadgeEntity> badges) {
		this.badges = badges;
		return this;
	}

	public ApplicationEntity addBadgesItem(BadgeEntity badgesItem) {
		if (this.badges == null) {
			this.badges = new ArrayList<BadgeEntity>();
		}
		this.badges.add(badgesItem);
		return this;
	}

	/**
	 * Get badges
	 * @return badges
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public List<BadgeEntity> getBadges() {
		return badges;
	}

	public void setBadges(List<BadgeEntity> badges) {
		this.badges = badges;
	}

	public ApplicationEntity rules(List<RuleEntity> rules) {
		this.rules = rules;
		return this;
	}

	public ApplicationEntity addRulesItem(RuleEntity rulesItem) {
		if (this.rules == null) {
			this.rules = new ArrayList<RuleEntity>();
		}
		this.rules.add(rulesItem);
		return this;
	}

	/**
	 * Get rules
	 * @return rules
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public List<RuleEntity> getRules() {
		return rules;
	}

	public void setRules(List<RuleEntity> rules) {
		this.rules = rules;
	}

	public ApplicationEntity users(List<UserEntity> users) {
		this.users = users;
		return this;
	}

	public ApplicationEntity addUsersItem(UserEntity usersItem) {
		if (this.users == null) {
			this.users = new ArrayList<UserEntity>();
		}
		this.users.add(usersItem);
		return this;
	}

	/**
	 * Get users
	 * @return users
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ApplicationEntity applicationEntity = (ApplicationEntity) o;
		return Objects.equals(this.apiToken, applicationEntity.apiToken) &&
			Objects.equals(this.badges, applicationEntity.badges) &&
			Objects.equals(this.rules, applicationEntity.rules) &&
			Objects.equals(this.users, applicationEntity.users);
	}

	@Override
	public int hashCode() {
		return Objects.hash(apiToken, badges, rules, users);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ApplicationEntity {\n");

		sb.append("    apiToken: ").append(toIndentedString(apiToken)).append("\n");
		sb.append("    badges: ").append(toIndentedString(badges)).append("\n");
		sb.append("    rules: ").append(toIndentedString(rules)).append("\n");
		sb.append("    users: ").append(toIndentedString(users)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}

