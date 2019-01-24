package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Application
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-04T12:18:41.464Z")

public class Application   {
  @JsonProperty("apiToken")
  private Long apiToken = null;

  @JsonProperty("badges")
  @Valid
  private List<Badge> badges = null;

  @JsonProperty("rules")
  @Valid
  private List<Rule> rules = null;

  @JsonProperty("endusers")
  @Valid
  private List<User> endusers = null;

  public Application apiToken(Long apiToken) {
    this.apiToken = apiToken;
    return this;
  }

  /**
   * Get apiToken
   * @return apiToken
  **/
  @ApiModelProperty(value = "")


  public Long getApiToken() {
    return apiToken;
  }

  public void setApiToken(Long apiToken) {
    this.apiToken = apiToken;
  }

  public Application badges(List<Badge> badges) {
    this.badges = badges;
    return this;
  }

  public Application addBadgesItem(Badge badgesItem) {
    if (this.badges == null) {
      this.badges = new ArrayList<Badge>();
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

  public List<Badge> getBadges() {
    return badges;
  }

  public void setBadges(List<Badge> badges) {
    this.badges = badges;
  }

  public Application rules(List<Rule> rules) {
    this.rules = rules;
    return this;
  }

  public Application addRulesItem(Rule rulesItem) {
    if (this.rules == null) {
      this.rules = new ArrayList<Rule>();
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

  public List<Rule> getRules() {
    return rules;
  }

  public void setRules(List<Rule> rules) {
    this.rules = rules;
  }

  public Application endusers(List<User> endusers) {
    this.endusers = endusers;
    return this;
  }

  public Application addEndusersItem(User endusersItem) {
    if (this.endusers == null) {
      this.endusers = new ArrayList<User>();
    }
    this.endusers.add(endusersItem);
    return this;
  }

  /**
   * Get endusers
   * @return endusers
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<User> getEndusers() {
    return endusers;
  }

  public void setEndusers(List<User> endusers) {
    this.endusers = endusers;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Application application = (Application) o;
    return Objects.equals(this.apiToken, application.apiToken) &&
        Objects.equals(this.badges, application.badges) &&
        Objects.equals(this.rules, application.rules) &&
        Objects.equals(this.endusers, application.endusers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(apiToken, badges, rules, endusers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Application {\n");
    
    sb.append("    apitoken: ").append(toIndentedString(apiToken)).append("\n");
    sb.append("    badges: ").append(toIndentedString(badges)).append("\n");
    sb.append("    rules: ").append(toIndentedString(rules)).append("\n");
    sb.append("    endusers: ").append(toIndentedString(endusers)).append("\n");
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

