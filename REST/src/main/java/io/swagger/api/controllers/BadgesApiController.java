package io.swagger.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.api.interfaces.BadgesApi;
import io.swagger.entities.BadgeEntity;
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

import static io.swagger.api.utils.Transformator.toBadge;
import static io.swagger.api.utils.Transformator.toBadgeEntity;

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

		BadgeEntity badgeEntity = badgeRepository.findByApiTokenAndName(newBadge.getApitoken(), newBadge.getName());

		// if app exists and badge not
		if (badgeEntity == null) {

			// check if app already contains a badge with this name
			for (BadgeEntity b : badgeRepository.findBadgeEntitiesByApiToken(newBadge.getApitoken())) {
					if (b.getName().equals(newBadge.getName())) {
					// already a badge with this name in the app
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

			BadgeEntity newBadgeEntity = toBadgeEntity(newBadge);
			// add badge
			Long id = badgeRepository.save(newBadgeEntity).getId();
			newBadgeEntity.setId(id);

			URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(id)
				.toUri();

			return ResponseEntity.created(location).body(toBadge(newBadgeEntity));
		}
		else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    public ResponseEntity<Badge> getBadge(@ApiParam(value = "",required=true) @PathVariable("badgeName") String badgeName,@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "apiToken", required = true) String apiToken) {

    	BadgeEntity badgeEntity = badgeRepository.findByApiTokenAndName(apiToken, badgeName);

		if (badgeEntity != null) {

			// get badge by apiToken and name (badge names are unique in app)
			Badge badge = toBadge(badgeRepository.findByApiTokenAndName(apiToken, badgeName));

			return new ResponseEntity<Badge>(badge, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Badge>(HttpStatus.NOT_FOUND);
		}
    }

    public ResponseEntity<List<Badge>> getBadges(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "apiToken", required = true) String apiToken) {
		// ApplicationEntity appEntity = applicationRepository.findByApiToken(apiToken);
		List<BadgeEntity> badgeEntities = badgeRepository.findBadgeEntitiesByApiToken(apiToken);

		// if app exists and has badges
		if (badgeEntities != null && !badgeEntities.isEmpty()) {

			List<Badge> badges = new ArrayList<>();

			// get all badges of the app
			for (BadgeEntity badgeEntity : badgeEntities) {
				if (badgeEntity != null) {
					badges.add(toBadge(badgeEntity));
				}
			}
			return new ResponseEntity<List<Badge>>(badges, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Badge>>(HttpStatus.NOT_FOUND);
		}
    }

    public ResponseEntity<Object> updateBadge(@NotNull @ApiParam(value = "Update badge", required = true) @Valid @RequestParam(value = "badgeName", required = true) String badgeName,@ApiParam(value = "Update badge" ,required=true )  @Valid @RequestBody Badge newBadge) {

    	BadgeEntity badgeEntity = badgeRepository.getByName(badgeName);

    	if (badgeEntity != null) {
			badgeEntity.setIcon(newBadge.getIcon());
    		badgeEntity.setName(newBadge.getName());
    		badgeRepository.save(badgeEntity);
    		return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
