package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Objects;

/**
 * Event
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-04T12:18:41.464Z")

public class Event   {
  @JsonProperty("timestamp")
  private String timestamp = null;

  @JsonProperty("userId")
  private Long userId = null;

  @JsonProperty("apiToken")
  private String apiToken = null;

  @JsonProperty("ruleName")
  private String ruleName = null;

  @JsonProperty("value")
  private Long value = null;

  @JsonProperty("property")
  @Valid
  private Property property = null;

  public Event timestamp(String timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * date of event
   * @return timestamp
  **/
  @ApiModelProperty(value = "date of event")

  @Valid

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public Event userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(value = "")


  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Event apiToken(String apiToken) {
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

  public Event name(String name) {
    this.ruleName = name;
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

  public Event property(Property properties) {
    this.property = property;
    return this;
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

  public Long getValue() {
    return value;
  }

  public void setValue(Long value) {
    this.value = value;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Event event = (Event) o;
    return Objects.equals(this.timestamp, event.timestamp) &&
        Objects.equals(this.userId, event.userId) &&
        Objects.equals(this.apiToken, event.apiToken) &&
        Objects.equals(this.ruleName, event.ruleName) &&
        Objects.equals(this.property, event.property);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestamp, userId, apiToken, ruleName, property);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Event {\n");
    
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    apiToken: ").append(toIndentedString(apiToken)).append("\n");
    sb.append("    ruleName: ").append(toIndentedString(ruleName)).append("\n");
    sb.append("    property: ").append(toIndentedString(property)).append("\n");
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

