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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-04T12:18:41.464Z")

@Controller
public class EventsApiController implements EventsApi {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
	UserBadgesRepository userBadgesRepository;

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

    public ResponseEntity<Object> addEvent(@ApiParam(value = "New event" ,required=true ) @RequestBody Event body) {

        ApplicationEntity app = applicationRepository.findByApiToken(body.getApiToken());

        // if app associated with event exists
        if (app != null) {

        	// we get the rule triggered by the event
			RuleEntity ruleEntity = null;
			for (RuleEntity rule : ruleRepository.getRuleEntitiesByApiToken(body.getApiToken())) {
				if (rule.getName().equals(body.getRuleName())) {
					ruleEntity = rule;
					break;
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
			// if no user was found
			if (endUser == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			// get corresponding property
			PropertyEntity propertyEntity = null;
			if (propertyRepository.getByApiTokenAndRuleNameAndName(body.getApiToken(), body.getRuleName(), body.getProperty().getName()) != null) {
				propertyEntity = propertyRepository.getByApiTokenAndRuleNameAndName(body.getApiToken(), body.getRuleName(), body.getProperty().getName());
			}
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			// check if property of rule is validated
			if (isPropertyValidated(propertyEntity, body.getValue())) {
				// we save the fact that the user won a badge
				if (ruleEntity.getBadgeId() != null) {
					UserBadgesEntity awardedBadge = new UserBadgesEntity(Long.valueOf(body.getUserId()), ruleEntity.getBadgeId());
					userBadgesRepository.save(awardedBadge);
				}
			}

			return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }

    // return true if property conditon is validated
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
