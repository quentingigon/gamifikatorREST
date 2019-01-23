package io.swagger.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.api.interfaces.EventsApi;
import io.swagger.entities.ApplicationEntity;
import io.swagger.entities.EndUserBadgeEntity;
import io.swagger.entities.EndUserEntity;
import io.swagger.entities.RuleEntity;
import io.swagger.model.Event;
import io.swagger.repositories.ApplicationRepository;
import io.swagger.repositories.EndUserBadgeRepository;
import io.swagger.repositories.EndUserRepository;
import io.swagger.repositories.RuleRepository;
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
	EndUserBadgeRepository endUserBadgeRepository;

    @Autowired
	EndUserRepository endUserRepository;

    @Autowired
	RuleRepository ruleRepository;

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
			EndUserEntity endUser = null;
			for (EndUserEntity user : endUserRepository.getEndUserEntitiesByApiToken(body.getApiToken())) {
				if (user.getId() == Long.valueOf(body.getUserId())) {
					endUser = user;
					break;
				}
			}
			if (endUser == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			// we save the fact that the user won a badge
			if (ruleEntity.getBadgeId() != null) {
				EndUserBadgeEntity awardedBadge = new EndUserBadgeEntity(Long.valueOf(body.getUserId()), ruleEntity.getBadgeId());
				endUserBadgeRepository.save(awardedBadge);
			}

			return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
}
