package io.swagger.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.api.interfaces.UsersApi;
import io.swagger.entities.UserBadgesEntity;
import io.swagger.entities.UserEntity;
import io.swagger.model.User;
import io.swagger.repositories.BadgeRepository;
import io.swagger.repositories.UserBadgesRepository;
import io.swagger.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static io.swagger.api.utils.Transformator.*;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-04T12:18:41.464Z")

@Controller
public class UsersApiController implements UsersApi {

    @Autowired
	UserRepository userRepository;

	@Autowired
	UserBadgesRepository userBadgesRepository;

	@Autowired
	BadgeRepository badgeRepository;

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<User> getUser(@ApiParam(value = "Name of the user to get",required=true) @PathVariable("username") String username) {
		UserEntity userEntity = userRepository.getByName(username);

		if (userEntity != null) {
			User user = toUser(userEntity);

			// fill badge list by looking for userBadges items, that represent a relation for user/badge
			if (userBadgesRepository.findUserBadgesEntitiesByUserId(userEntity.getId()) != null) {
				for (UserBadgesEntity userBadges : userBadgesRepository.findUserBadgesEntitiesByUserId(userEntity.getId())) {
					user.addBadgesItem(toBadge(badgeRepository.getById(userBadges.getBadgeId())));
				}
			}
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    // used for testing purposes
	@Override
	public ResponseEntity<User> addUser(@Valid @RequestBody User newUser) {
		UserEntity userEntity = toUserEntity(newUser);

		userRepository.save(userEntity);

		return new ResponseEntity<User>(toUser(userEntity), HttpStatus.OK);
	}
}
