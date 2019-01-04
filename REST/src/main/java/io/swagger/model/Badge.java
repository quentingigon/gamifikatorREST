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
 * Badge
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-04T12:18:41.464Z")

public class Badge   {
  @JsonProperty("apitoken")
  private String apitoken = null;

  @JsonProperty("badgeName")
  private String badgeName = null;

  public Badge apitoken(String apitoken) {
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

  public Badge badgeName(String badgeName) {
    this.badgeName = badgeName;
    return this;
  }

  /**
   * Get badgeName
   * @return badgeName
  **/
  @ApiModelProperty(value = "")


  public String getBadgeName() {
    return badgeName;
  }

  public void setBadgeName(String badgeName) {
    this.badgeName = badgeName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Badge badge = (Badge) o;
    return Objects.equals(this.apitoken, badge.apitoken) &&
        Objects.equals(this.badgeName, badge.badgeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(apitoken, badgeName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Badge {\n");
    
    sb.append("    apitoken: ").append(toIndentedString(apitoken)).append("\n");
    sb.append("    badgeName: ").append(toIndentedString(badgeName)).append("\n");
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

