package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

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
    return Objects.hash(apitoken, ruleName);
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

