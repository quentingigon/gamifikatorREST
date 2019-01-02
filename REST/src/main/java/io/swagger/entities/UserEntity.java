package io.swagger.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * UserEntity
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-02T12:37:14.114Z")

public class UserEntity {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("badges")
  @Valid
  private List<BadgeEntity> badges = null;

  public UserEntity id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserEntity badges(List<BadgeEntity> badges) {
    this.badges = badges;
    return this;
  }

  public UserEntity addBadgesItem(BadgeEntity badgesItem) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserEntity user = (UserEntity) o;
    return Objects.equals(this.id, user.id) &&
        Objects.equals(this.badges, user.badges);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, badges);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserEntity {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

