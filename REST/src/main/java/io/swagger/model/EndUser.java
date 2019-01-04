package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Badge;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * EndUser
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-04T12:18:41.464Z")

public class EndUser   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("apitoken")
  private String apitoken = null;

  @JsonProperty("badges")
  @Valid
  private List<Badge> badges = null;

  public EndUser name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public EndUser apitoken(String apitoken) {
    this.apitoken = apitoken;
    return this;
  }

  /**
   * Get apitoken
   * @return apitoken
  **/
  @ApiModelProperty(value = "")


  public String getApitoken() {
    return apitoken;
  }

  public void setApitoken(String apitoken) {
    this.apitoken = apitoken;
  }

  public EndUser badges(List<Badge> badges) {
    this.badges = badges;
    return this;
  }

  public EndUser addBadgesItem(Badge badgesItem) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EndUser endUser = (EndUser) o;
    return Objects.equals(this.name, endUser.name) &&
        Objects.equals(this.apitoken, endUser.apitoken) &&
        Objects.equals(this.badges, endUser.badges);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, apitoken, badges);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EndUser {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    apitoken: ").append(toIndentedString(apitoken)).append("\n");
    sb.append("    badges: ").append(toIndentedString(badges)).append("\n");
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

