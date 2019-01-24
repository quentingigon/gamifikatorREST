package io.swagger.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.api.interfaces.UsersApi;
import io.swagger.entities.UserEntity;
import io.swagger.model.EndUser;
import io.swagger.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-04T12:18:41.464Z")

@Controller
public class UsersApiController implements UsersApi {

    @Autowired
	UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<EndUser> getUser(@ApiParam(value = "ID of the user to get",required=true) @PathVariable("username") String username) {
		UserEntity userEntity = userRepository.getByName(username);

		if (userEntity != null) {
			return new ResponseEntity<EndUser>(userEntity.toEndUser(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<EndUser>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
