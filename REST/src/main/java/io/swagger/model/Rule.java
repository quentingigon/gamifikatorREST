package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Rule
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-04T12:18:41.464Z")

public class Rule   {
  @JsonProperty("apitoken")
  private String apitoken = null;

  @JsonProperty("ruleName")
  private String ruleName = null;

  @JsonProperty("conditionValue")
  private String conditionValue = null;

  // can be: <, >, <=, >=, ==, !, AND, OR
  @JsonProperty("operator")
  private String operator = null;

  @JsonProperty("badge")
  private Badge badge = null;

  public Rule apitoken(String apitoken) {
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
   * Get conditionValue
   * @return conditionValue
   **/
  @ApiModelProperty(value = "")


  public String getConditionValue() {
    return conditionValue;
  }

  public void setConditionValue(String conditionValue) {
    this.conditionValue = conditionValue;
  }

  /**
   * Get operator
   * @return operator
   **/
  @ApiModelProperty(value = "")


  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rule rule = (Rule) o;
    return Objects.equals(this.apitoken, rule.apitoken) &&
        Objects.equals(this.ruleName, rule.ruleName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(apitoken, ruleName, conditionValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Rule {\n");
    
    sb.append("    apitoken: ").append(toIndentedString(apitoken)).append("\n");
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

