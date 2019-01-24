package io.swagger.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.api.interfaces.EventsApi;
import io.swagger.entities.*;
import io.swagger.model.Event;
import io.swagger.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-04T12:18:41.464Z")

@Controller
public class EventsApiController implements EventsApi {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
	UserBadgeRepository userBadgeRepository;

    @Autowired
	UserRepository userRepository;

    @Autowired
	RuleRepository ruleRepository;

	@Autowired
	PropertyRepository propertyRepository;

    private static final Logger log = LoggerFactory.getLogger(EventsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public EventsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Object> addEvent(@ApiParam(value = "New event" ,required=true )  @Valid @RequestBody Event body) {

        ApplicationEntity app = applicationRepository.getByApiToken(body.getApiToken());

        // if app associated with event exists
        if (app != null) {

        	// we get the rule triggered by the event
			RuleEntity ruleEntity = null;
			for (RuleEntity rule : ruleRepository.getRuleEntitiesByApiToken(body.getApiToken())) {
				if (rule.getName().equals(body.getRuleName())) {
					ruleEntity = rule;
				}
			}
			// if no rule was found
			if (ruleEntity == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			// get user concerned by the event
			UserEntity endUser = null;
			for (UserEntity user : userRepository.getEndUserEntitiesByApiToken(body.getApiToken())) {
				if (user.getId() == Long.valueOf(body.getUserId())) {
					endUser = user;
					break;
				}
			}
			if (endUser == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			// get corresponding property
			PropertyEntity propertyEntity = null;
			for (PropertyEntity p : propertyRepository.getPropertyEntitiesByRuleId(ruleEntity.getId())) {
				if (p.getName().equals(body.getPropertyName())) {
					propertyEntity = p;
				}
			}
			if (propertyEntity == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			// check if property of rule is validated
			if (isPropertyValidated(propertyEntity, body.getValue())) {
				// we save the fact that the user won a badge
				if (ruleEntity.getBadgeId() != null) {
					UserBadgeEntity awardedBadge = new UserBadgeEntity(Long.valueOf(body.getUserId()), ruleEntity.getBadgeId());
					userBadgeRepository.save(awardedBadge);
				}
			}

			return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }

    private boolean isPropertyValidated(PropertyEntity propertyEntity, Long eventValue) {

    	// get operator and base value of property
    	String operator = propertyEntity.getOperator();
    	Long baseValue = propertyEntity.getValue();

    	boolean result = false;

    	switch (operator) {
			case "<": if (eventValue < baseValue) {
				result = true;
				break;
			}
			case ">": if (eventValue > baseValue) {
				result = true;
				break;
			}
			case "<=": if (eventValue <= baseValue) {
				result = true;
				break;
			}
			case ">=": if (eventValue >= baseValue) {
				result = true;
				break;
			}
			case "==": if (eventValue == baseValue) {
				result = true;
				break;
			}
			case "!=": if (eventValue != baseValue) {
				result = true;
				break;
			}
			default: break;
		}
		return result;
	}
}
