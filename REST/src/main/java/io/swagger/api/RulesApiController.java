package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-04T12:18:41.464Z")

@Controller
public class RulesApiController implements RulesApi {

    private static final Logger log = LoggerFactory.getLogger(RulesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public RulesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> createRule(@ApiParam(value = "New rule" ,required=true )  @Valid @RequestBody Rule rule) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteRule(@ApiParam(value = "Rule to be deleted" ,required=true )  @Valid @RequestBody Rule rule) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Rule> getRule(@ApiParam(value = "",required=true) @PathVariable("ruleName") String ruleName,@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "apitoken", required = true) String apitoken) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Rule>(objectMapper.readValue("{  \"apiToken\" : \"apiToken\",  \"triggerEvents\" : [ {    \"apiToken\" : \"apiToken\",    \"name\" : \"name\",    \"userId\" : \"userId\",    \"properties\" : [ {      \"name\" : \"name\",      \"value\" : 0    }, {      \"name\" : \"name\",      \"value\" : 0    } ],    \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"  }, {    \"apiToken\" : \"apiToken\",    \"name\" : \"name\",    \"userId\" : \"userId\",    \"properties\" : [ {      \"name\" : \"name\",      \"value\" : 0    }, {      \"name\" : \"name\",      \"value\" : 0    } ],    \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"  } ],  \"name\" : \"name\",  \"conditions\" : {    \"levelValues\" : [ \"levelValues\", \"levelValues\" ],    \"name\" : \"name\",    \"collection\" : \"collection\",    \"operator\" : \"operator\"  }}", Rule.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Rule>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Rule>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Rule>> getRules(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "apitoken", required = true) String apitoken) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Rule>>(objectMapper.readValue("[ {  \"apiToken\" : \"apiToken\",  \"triggerEvents\" : [ {    \"apiToken\" : \"apiToken\",    \"name\" : \"name\",    \"userId\" : \"userId\",    \"properties\" : [ {      \"name\" : \"name\",      \"value\" : 0    }, {      \"name\" : \"name\",      \"value\" : 0    } ],    \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"  }, {    \"apiToken\" : \"apiToken\",    \"name\" : \"name\",    \"userId\" : \"userId\",    \"properties\" : [ {      \"name\" : \"name\",      \"value\" : 0    }, {      \"name\" : \"name\",      \"value\" : 0    } ],    \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"  } ],  \"name\" : \"name\",  \"conditions\" : {    \"levelValues\" : [ \"levelValues\", \"levelValues\" ],    \"name\" : \"name\",    \"collection\" : \"collection\",    \"operator\" : \"operator\"  }}, {  \"apiToken\" : \"apiToken\",  \"triggerEvents\" : [ {    \"apiToken\" : \"apiToken\",    \"name\" : \"name\",    \"userId\" : \"userId\",    \"properties\" : [ {      \"name\" : \"name\",      \"value\" : 0    }, {      \"name\" : \"name\",      \"value\" : 0    } ],    \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"  }, {    \"apiToken\" : \"apiToken\",    \"name\" : \"name\",    \"userId\" : \"userId\",    \"properties\" : [ {      \"name\" : \"name\",      \"value\" : 0    }, {      \"name\" : \"name\",      \"value\" : 0    } ],    \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"  } ],  \"name\" : \"name\",  \"conditions\" : {    \"levelValues\" : [ \"levelValues\", \"levelValues\" ],    \"name\" : \"name\",    \"collection\" : \"collection\",    \"operator\" : \"operator\"  }} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Rule>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Rule>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateRule(@NotNull @ApiParam(value = "Old Rule name", required = true) @Valid @RequestParam(value = "oldRuleName", required = true) String oldRuleName,@ApiParam(value = "New rule" ,required=true )  @Valid @RequestBody Rule rule) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
