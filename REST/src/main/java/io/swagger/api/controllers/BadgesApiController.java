package io.swagger.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.api.interfaces.BadgesApi;
import io.swagger.entities.ApplicationEntity;
import io.swagger.entities.BadgeEntity;
import io.swagger.entities.RuleEntity;
import io.swagger.model.Badge;
import io.swagger.repositories.ApplicationRepository;
import io.swagger.repositories.BadgeRepository;
import io.swagger.repositories.RuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-04T12:18:41.464Z")

@Controller
public class BadgesApiController implements BadgesApi {

    @Autowired
	BadgeRepository badgeRepository;

    @Autowired
	ApplicationRepository applicationRepository;

    @Autowired
	RuleRepository ruleRepository;

    private static final Logger log = LoggerFactory.getLogger(BadgesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public BadgesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Object> createBadge(@ApiParam(value = "New badge" ,required=true )  @Valid @RequestBody Badge newBadge) {

		ApplicationEntity appEntity = applicationRepository.getByApiToken(newBadge.getApitoken());

		if (appEntity != null) {

			for (BadgeEntity b : badgeRepository.getBadgeEntitiesByApiToken(newBadge.getApitoken())) {
				if (b.getName().equals(newBadge.getBadgeName())) {
					// already a badge with this name in the app
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

			BadgeEntity badge = toBadgeEntity(newBadge);
			// add badge
			Long id = badgeRepository.save(badge).getId();

			URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(id)
				.toUri();

			return ResponseEntity.created(location).body(toBadge(badge));
		}
		else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    public ResponseEntity<Object> deleteBadge(@ApiParam(value = "Badge to be deleted" ,required=true )  @Valid @RequestBody Badge badge) {
		String apiToken = badge.getApitoken();
    	BadgeEntity badgeEntity = badgeRepository.getByApiTokenAndName(badge.getBadgeName(), apiToken);

		if (badgeEntity != null) {
			// remove badge from rule
			RuleEntity ruleEntity = ruleRepository.getByApiTokenAndBadgeId(apiToken, badgeEntity.getId());
			ruleEntity.setBadgeId(new Long(-1));
			badgeRepository.delete(badgeEntity);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    public ResponseEntity<Badge> getBadge(@ApiParam(value = "",required=true) @PathVariable("badgeName") String badgeName,@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "apitoken", required = true) String apitoken) {
		ApplicationEntity appEntity = applicationRepository.getByApiToken(apitoken);

		if (appEntity != null) {

			// get badge by apiToken and name (badge names are unique in app)
			Badge badge = toBadge(badgeRepository.getByApiTokenAndName(apitoken, badgeName));

			return new ResponseEntity<Badge>(badge, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Badge>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    public ResponseEntity<List<Badge>> getBadges(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "apitoken", required = true) String apitoken) {
		ApplicationEntity appEntity = applicationRepository.getByApiToken(apitoken);

		if (appEntity != null) {

			List<Badge> badges = new ArrayList<>();

			// get all badges of the app
			for (BadgeEntity badgeEntity : badgeRepository.getBadgeEntitiesByApiToken(apitoken)) {
				if (badgeEntity != null) {
					badges.add(toBadge(badgeEntity));
				}
			}
			return new ResponseEntity<List<Badge>>(badges, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Badge>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    public ResponseEntity<Object> updateBadge(@NotNull @ApiParam(value = "Update badge", required = true) @Valid @RequestParam(value = "badgeName", required = true) String badgeName,@ApiParam(value = "Update badge" ,required=true )  @Valid @RequestBody Badge newBadge) {

    	BadgeEntity badgeEntity = badgeRepository.getByName(badgeName);

    	if (badgeEntity != null) {
			badgeEntity.setIcon(newBadge.getImage());
    		badgeEntity.setName(newBadge.getBadgeName());
    		badgeRepository.save(badgeEntity);
    		return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	private BadgeEntity toBadgeEntity(Badge badge) {
		BadgeEntity badgeEntity = new BadgeEntity();
		badgeEntity.setName(badge.getBadgeName());
		badgeEntity.setApiToken(badge.getApitoken());
		badgeEntity.setIcon(badge.getImage());
		return badgeEntity;
	}

	private Badge toBadge(BadgeEntity badgeEntity) {
		Badge badge = new Badge();
		badge.setApitoken(badgeEntity.getApiToken());
		badge.setBadgeName(badgeEntity.getName());
		return badge;
	}
}
