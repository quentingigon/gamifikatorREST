package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Badge
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-04T12:18:41.464Z")

public class Badge   {

  @JsonProperty("badgeId")
  private Long badgeId = null;

  @JsonProperty("apiToken")
  private String apiToken = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("image")
  private String image = null;

  public Badge apiToken(String apiToken) {
    this.apiToken = apiToken;
    return this;
  }

  /**
   * Get badgeId
   * @return badgeId
   **/
  @ApiModelProperty(value = "")


  public Long getBadgeId() {
    return badgeId;
  }

  public void setBadgeId(Long badgeId) {
    this.badgeId = badgeId;
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

  public Badge badgeName(String badgeName) {
    this.name = badgeName;
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

  /**
   * Get image
   * @return image
   **/
  @ApiModelProperty(value = "")


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Badge image(String image) {
    this.image = image;
    return this;
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
    return Objects.equals(this.apiToken, badge.apiToken) &&
        Objects.equals(this.name, badge.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(apiToken, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Badge {\n");
    
    sb.append("    apitoken: ").append(toIndentedString(apiToken)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

