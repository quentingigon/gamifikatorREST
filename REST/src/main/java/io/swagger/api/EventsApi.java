/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.1-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.entities.EventEntity;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-02T12:37:14.114Z")

@Api(value = "events", description = "the events API")
public interface EventsApi {

    @ApiOperation(value = "Add a new event", nickname = "addEvent", notes = "", response = EventEntity.class, tags={ "events", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "The event has been created", response = EventEntity.class),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/events",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<EventEntity> addEvent(@ApiParam(value = "New event" ,required=true )  @Valid @RequestBody EventEntity body);

}