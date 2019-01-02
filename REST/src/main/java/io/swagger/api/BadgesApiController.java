package io.swagger.api;

import io.swagger.entities.BadgeEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-02T12:37:14.114Z")

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

    public ResponseEntity<BadgeEntity> createBadge(@ApiParam(value = "New badge" ,required=true )  @Valid @RequestBody BadgeEntity body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<BadgeEntity>(objectMapper.readValue("{  \"id\" : 0}", BadgeEntity.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<BadgeEntity>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<BadgeEntity>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<BadgeEntity> deleteBadge(@ApiParam(value = "BadgeEntity to be deleted" ,required=true )  @Valid @RequestBody BadgeEntity body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<BadgeEntity>(objectMapper.readValue("{  \"id\" : 0}", BadgeEntity.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<BadgeEntity>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<BadgeEntity>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<BadgeEntity>> getBadges() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<BadgeEntity>>(objectMapper.readValue("[ {  \"id\" : 0}, {  \"id\" : 0} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<BadgeEntity>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<BadgeEntity>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<BadgeEntity> updateBadge(@ApiParam(value = "Update badge" ,required=true )  @Valid @RequestBody BadgeEntity body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<BadgeEntity>(objectMapper.readValue("{  \"id\" : 0}", BadgeEntity.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<BadgeEntity>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<BadgeEntity>(HttpStatus.NOT_IMPLEMENTED);
    }

}
