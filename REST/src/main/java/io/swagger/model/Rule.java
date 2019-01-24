package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Objects;

/**
 * Rule
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-04T12:18:41.464Z")

public class Rule   {
  @JsonProperty("apitoken")
  private String apiToken = null;

  @JsonProperty("ruleName")
  private String ruleName = null;

  @JsonProperty("badge")
  private Badge badge = null;

  @JsonProperty("property")
  @Valid
  private Property property = null;

  public Rule apiToken(String apiToken) {
    this.apiToken = apiToken;
    return this;
  }

  /**
   * Get apitoken
   * @return apitoken
  **/
  @ApiModelProperty(value = "")


  public String getApitoken() {
    return apiToken;
  }

  public void setApitoken(String apiToken) {
    this.apiToken = apiToken;
  }

  public Rule ruleName(String ruleName) {
    this.ruleName = ruleName;
    return this;
  }

  /**
   * Get ruleName
   * @return ruleName
  **/
  @ApiModelProperty(value = "")


  public String getRuleName() {
    return ruleName;
  }

  public void setRuleName(String ruleName) {
    this.ruleName = ruleName;
  }

  /**
   * Get badge
   * @return badge
   **/
  @ApiModelProperty(value = "")


  public Badge getBadge() {
    return badge;
  }

  public void setBadge(Badge badge) {
    this.badge = badge;
  }

  /**
   * Get property
   * @return property
   **/
  @ApiModelProperty(value = "")


  public Property getProperty() {
    return property;
  }

  public void setProperty(Property property) {
    this.property = property;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rule rule = (Rule) o;
    return Objects.equals(this.apiToken, rule.apiToken) &&
        Objects.equals(this.ruleName, rule.ruleName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(apiToken, ruleName, badge, property);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Rule {\n");
    
    sb.append("    apitoken: ").append(toIndentedString(apiToken)).append("\n");
    sb.append("    ruleName: ").append(toIndentedString(ruleName)).append("\n");
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
