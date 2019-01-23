package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * RuleConditions
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-04T12:18:41.464Z")

public class RuleConditions   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("operator")
  private String operator = null;

  @JsonProperty("levelValues")
  @Valid
  private List<String> levelValues = null;

  @JsonProperty("collection")
  private String collection = null;

  public RuleConditions name(String name) {
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

  public RuleConditions operator(String operator) {
    this.operator = operator;
    return this;
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

  public RuleConditions levelValues(List<String> levelValues) {
    this.levelValues = levelValues;
    return this;
  }

  public RuleConditions addLevelValuesItem(String levelValuesItem) {
    if (this.levelValues == null) {
      this.levelValues = new ArrayList<String>();
    }
    this.levelValues.add(levelValuesItem);
    return this;
  }

  /**
   * Get levelValues
   * @return levelValues
  **/
  @ApiModelProperty(value = "")


  public List<String> getLevelValues() {
    return levelValues;
  }

  public void setLevelValues(List<String> levelValues) {
    this.levelValues = levelValues;
  }

  public RuleConditions collection(String collection) {
    this.collection = collection;
    return this;
  }

  /**
   * Get collection
   * @return collection
  **/
  @ApiModelProperty(value = "")


  public String getCollection() {
    return collection;
  }

  public void setCollection(String collection) {
    this.collection = collection;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RuleConditions ruleConditions = (RuleConditions) o;
    return Objects.equals(this.name, ruleConditions.name) &&
        Objects.equals(this.operator, ruleConditions.operator) &&
        Objects.equals(this.levelValues, ruleConditions.levelValues) &&
        Objects.equals(this.collection, ruleConditions.collection);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, operator, levelValues, collection);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RuleConditions {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    operator: ").append(toIndentedString(operator)).append("\n");
    sb.append("    levelValues: ").append(toIndentedString(levelValues)).append("\n");
    sb.append("    collection: ").append(toIndentedString(collection)).append("\n");
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

