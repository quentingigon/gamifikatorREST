package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.entities.ApplicationEntity;
import io.swagger.entities.RuleEntity;
import io.swagger.repositories.ApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-02T12:37:14.114Z")

@Controller
public class RulesApiController implements RulesApi {

    private static final Logger log = LoggerFactory.getLogger(RulesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    ApplicationRepository applicationRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public RulesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<RuleEntity> createRule(@ApiParam(value = "New rule" ,required=true )  @Valid @RequestBody RuleEntity body) {
        ApplicationEntity app = applicationRepository.findByApiToken(body.getAppApiToken());

        if (app == null) {
            app = new ApplicationEntity(body.getAppApiToken());
        }
        else {

            List<RuleEntity> rules = app.getRules();
            for (int i = 0; i < rules.size(); i++) {
                if (rules.get(i).equals(body)) {
                    return ResponseEntity.status(304).build();
                }
            }
        }
        RuleEntity newRule = new RuleEntity();
        // verify rule
        app.addRulesItem(newRule);
        applicationRepository.save(app);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest().path("/{id}")
            .buildAndExpand(newRule.getAppApiToken() + newRule.getName()).toUri();
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<RuleEntity> deleteRule(@ApiParam(value = "RuleEntity to be deleted" ,required=true )  @Valid @RequestBody RuleEntity body) {
        ApplicationEntity app = applicationRepository.findByApiToken(body.getAppApiToken());
        if (app == null) {
            return ResponseEntity.notFound().build();
        }
        List<RuleEntity> appRules = app.getRules();
        for (RuleEntity rule: appRules) {
            if (rule.getName().equals(body.getName())) {
                appRules.remove(rule);
                app.setRules(appRules);
                applicationRepository.save(app);
                return ResponseEntity.ok(rule);
            }
        }
        return ResponseEntity.status(304).build();
    }

    public ResponseEntity<List<RuleEntity>> getRules(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "apitoken", required = true) String apitoken) {
        ApplicationEntity app = applicationRepository.findByApiToken(apitoken);
        if (app == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(app.getRules());
    }

    public ResponseEntity<RuleEntity> updateRule(@ApiParam(value = "updated rule" ,required=true )  @Valid @RequestBody RuleEntity body) {
        ApplicationEntity app = applicationRepository.findByApiToken(body.getAppApiToken());
        if (app == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            // TODO
            List<RuleEntity> rules = app.getRules();

            return ResponseEntity.ok(body);
        }
    }

}
