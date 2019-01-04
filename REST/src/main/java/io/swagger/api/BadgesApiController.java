package io.swagger.api;

import io.swagger.model.Badge;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-04T12:18:41.464Z")

@Controller
public class BadgesApiController implements BadgesApi {

    private static final Logger log = LoggerFactory.getLogger(BadgesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public BadgesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> createBadge(@ApiParam(value = "New badge" ,required=true )  @Valid @RequestBody Badge badge) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteBadge(@ApiParam(value = "Badge to be deleted" ,required=true )  @Valid @RequestBody Badge badge) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Badge> getBadge(@ApiParam(value = "",required=true) @PathVariable("badgeName") String badgeName,@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "apitoken", required = true) String apitoken) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Badge>(objectMapper.readValue("{  \"apiToken\" : \"apiToken\",  \"level\" : 0,  \"name\" : \"name\",  \"iconUrl\" : \"iconUrl\"}", Badge.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Badge>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Badge>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Badge>> getBadges(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "apitoken", required = true) String apitoken) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Badge>>(objectMapper.readValue("[ {  \"apiToken\" : \"apiToken\",  \"level\" : 0,  \"name\" : \"name\",  \"iconUrl\" : \"iconUrl\"}, {  \"apiToken\" : \"apiToken\",  \"level\" : 0,  \"name\" : \"name\",  \"iconUrl\" : \"iconUrl\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Badge>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Badge>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateBadge(@NotNull @ApiParam(value = "Update badge", required = true) @Valid @RequestParam(value = "badgeName", required = true) String badgeName,@ApiParam(value = "Update badge" ,required=true )  @Valid @RequestBody Badge newBadge) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
