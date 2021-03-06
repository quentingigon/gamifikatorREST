package io.swagger.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.api.interfaces.RulesApi;
import io.swagger.entities.PropertyEntity;
import io.swagger.entities.RuleEntity;
import io.swagger.model.Property;
import io.swagger.model.Rule;
import io.swagger.repositories.ApplicationRepository;
import io.swagger.repositories.BadgeRepository;
import io.swagger.repositories.PropertyRepository;
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

import static io.swagger.api.utils.Transformator.toPropertyEntity;
import static io.swagger.api.utils.Transformator.toRule;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-04T12:18:41.464Z")

@Controller
public class RulesApiController implements RulesApi {

    @Autowired
    RuleRepository ruleRepository;

    @Autowired
	ApplicationRepository applicationRepository;

    @Autowired
	PropertyRepository propertyRepository;

    @Autowired
	BadgeRepository badgeRepository;

    private static final Logger log = LoggerFactory.getLogger(RulesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public RulesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Object> createRule(@ApiParam(value = "New rule" ,required=true )  @Valid @RequestBody Rule newRule) {

		RuleEntity rule = ruleRepository.getByNameAndApiToken(newRule.getName(), newRule.getApitoken());

		// the app doesn't have a rule of this name
		if (rule == null) {
			RuleEntity newRuleEntity = new RuleEntity(newRule.getName(), newRule.getApitoken());

			// if new rule contains a property and property doesnt already exists in app/ruleName
			if (newRule.getProperty() != null
				&& propertyRepository.getByApiTokenAndRuleNameAndName(newRule.getApitoken(),
																	  newRule.getName(),
																	  newRule.getProperty().getName()) == null
				&& checkOperator(newRule.getProperty().getOperator())) {
				// save rule property and set its id to the rule
				Long id = propertyRepository.save(toPropertyEntity(newRule.getProperty(), newRule.getApitoken())).getId();
				newRuleEntity.setPropertyId(id);
			}
			// no properties in the new rule
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			// verifiy that rule's associated badge exists
			if (badgeRepository.findOne(newRule.getBadgeId()) == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			// set badge id to rule
			newRuleEntity.setBadgeId(newRule.getBadgeId());
			Long ruleId = ruleRepository.save(newRuleEntity).getId();

			URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newRuleEntity.getId())
				.toUri();

			return ResponseEntity.created(location).body(toRule(newRuleEntity));
		}
		else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	public ResponseEntity<Object> deleteRule(@PathVariable("ruleName") String ruleName, @Valid @RequestParam(value = "apiToken", required = true) String apiToken) {

    	RuleEntity ruleEntity = ruleRepository.getByNameAndApiToken(ruleName, apiToken);

		if (ruleEntity != null) {

			// delete properties associated with the rule
			if (propertyRepository.getByApiTokenAndRuleName(apiToken, ruleName) != null) {
				for (PropertyEntity property : propertyRepository.getByApiTokenAndRuleName(apiToken, ruleName)) {
					propertyRepository.delete(property);
				}
			}

			ruleRepository.delete(ruleEntity);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}
    }

    public ResponseEntity<Rule> getRule(@ApiParam(value = "", required=true) @PathVariable("ruleName") String ruleName,@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "apiToken", required = true) String apiToken) {

    	// find a rule by api token and rule name (both must exists)
    	RuleEntity ruleEntity = ruleRepository.getByNameAndApiToken(ruleName, apiToken);

        if (ruleEntity != null) {
			return new ResponseEntity<Rule>(toRule(ruleEntity), HttpStatus.OK);
		} else {
			return new ResponseEntity<Rule>(HttpStatus.NOT_FOUND);
		}
    }

    public ResponseEntity<List<Rule>> getRules(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "apiToken", required = true) String apiToken) {

		//ApplicationEntity appEntity = applicationRepository.findByApiToken(apiToken);
		List<RuleEntity> ruleEntities = ruleRepository.getRuleEntitiesByApiToken(apiToken);

		// if app exists and has rules
		if (ruleEntities != null && !ruleEntities.isEmpty()) {

			List<Rule> rules = new ArrayList<>();

			// get rules of app
			for (RuleEntity ruleEntity : ruleEntities) {
				if (ruleEntity != null) {
					rules.add(toRule(ruleEntity));
				}
			}

			// return rules
			return new ResponseEntity<List<Rule>>(rules, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Rule>>(HttpStatus.NOT_FOUND);
		}
    }

    public ResponseEntity<Object> updateRule(@NotNull @ApiParam(value = "Old Rule name", required = true) @Valid @RequestParam(value = "oldRuleName", required = true) String oldRuleName,@ApiParam(value = "New rule" ,required=true )  @Valid @RequestBody Rule newRule) {
		RuleEntity rule = ruleRepository.getByNameAndApiToken(oldRuleName, newRule.getApitoken());
		PropertyEntity property = propertyRepository.getByApiTokenAndRuleNameAndName(newRule.getApitoken(), newRule.getName(),newRule.getProperty().getName());

		// when we update a rule, we also want to update the property associated
		if (rule != null && property != null) {

			// set new values for property
			Property newProperty = newRule.getProperty();
			property.setValue(newProperty.getValue());
			property.setOperator(newProperty.getOperator());
			Long newPropertyId = propertyRepository.save(property).getId();

			// set new values for rule
			rule.setName(newRule.getName());
			rule.setPropertyId(newPropertyId);
			ruleRepository.save(rule);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }

    private boolean checkOperator(String operator) {
    	String[] operators = {"<", ">", "<=", ">=", "==", "!="};

    	boolean result = false;

    	for (String op : operators) {
    		if (op.equals(operator)) {
    			result = true;
    			break;
			}
		}
		return result;
	}
}
