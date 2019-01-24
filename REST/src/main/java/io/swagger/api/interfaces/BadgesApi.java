/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.1-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api.interfaces;

import io.swagger.annotations.*;
import io.swagger.model.Badge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-04T12:18:41.464Z")

@Api(value = "badges", description = "the badges API")
public interface BadgesApi {

    @ApiOperation(value = "Add a new badge", nickname = "createBadge", notes = "", tags={ "badges", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "The badge has been created"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/badges",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Object> createBadge(@ApiParam(value = "New badge" ,required=true )  @Valid @RequestBody Badge newBadge);


    @ApiOperation(value = "Delete badge", nickname = "deleteBadge", notes = "Delete badge", tags={ "badges", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "The rule has been deleted"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/badges",
        consumes = { "application/json" },
        method = RequestMethod.DELETE)
    ResponseEntity<Object> deleteBadge(@ApiParam(value = "Badge to be deleted" ,required=true )  @Valid @RequestBody Badge badge);


    @ApiOperation(value = "Get badge", nickname = "getBadge", notes = "Returns particular badge", response = Badge.class, tags={ "badges", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Badge.class),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/badges/{badgeName}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Badge> getBadge(@ApiParam(value = "",required=true) @PathVariable("badgeName") String badgeName,@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "apitoken", required = true) String apiToken);


    @ApiOperation(value = "Get badges", nickname = "getBadges", notes = "Returns list of badges", response = Badge.class, responseContainer = "List", tags={ "badges", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Badge.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/badges",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Badge>> getBadges(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "apitoken", required = true) String apiToken);


    @ApiOperation(value = "Update exisiting badge", nickname = "updateBadge", notes = "", tags={ "badges", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "The badge has been updated"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/badges",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Object> updateBadge(@NotNull @ApiParam(value = "Update badge", required = true) @Valid @RequestParam(value = "badgeName", required = true) String badgeName,@ApiParam(value = "Update badge" ,required=true )  @Valid @RequestBody Badge newBadge);

}