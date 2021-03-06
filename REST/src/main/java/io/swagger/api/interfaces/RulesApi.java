/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.1-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api.interfaces;

import io.swagger.annotations.*;
import io.swagger.model.Rule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-04T12:18:41.464Z")

@Api(value = "rules", description = "the rules API")
public interface RulesApi {

    @ApiOperation(value = "Add a new rule", nickname = "createRule", notes = "", tags={ "rules", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "The rule has been created"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/rules",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Object> createRule(@ApiParam(value = "New rule" ,required=true )  @Valid @RequestBody Rule newRule);


    @ApiOperation(value = "Delete rule", nickname = "deleteRule", notes = "Delete rule", tags={ "rules", })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "The rule has been deleted"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/rules/{ruleName}",
        consumes = { "application/json" },
        method = RequestMethod.DELETE)
    ResponseEntity<Object> deleteRule(@PathVariable("ruleName") String ruleName, @Valid @RequestParam(value = "apiToken", required = true) String apiToken);


    @ApiOperation(value = "Get rule", nickname = "getRule", notes = "Returns particular rule", response = Rule.class, tags={ "rules", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Rule.class),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/rules/{ruleName}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Rule> getRule(@ApiParam(value = "",required=true) @PathVariable("ruleName") String ruleName,@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "apiToken", required = true) String apiToken);


    @ApiOperation(value = "Get rules", nickname = "getRules", notes = "Returns list of rules", response = Rule.class, responseContainer = "List", tags={ "rules", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Rule.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/rules",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Rule>> getRules(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "apiToken", required = true) String apiToken);


    @ApiOperation(value = "Update exisiting rule", nickname = "updateRule", notes = "", tags={ "rules", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "The rule has been updated"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/rules",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Object> updateRule(@NotNull @ApiParam(value = "Old Rule name", required = true) @Valid @RequestParam(value = "oldRuleName", required = true) String oldRuleName,@ApiParam(value = "New rule" ,required=true )  @Valid @RequestBody Rule newRule);

}
