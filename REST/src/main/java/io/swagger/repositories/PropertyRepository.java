package io.swagger.repositories;

import io.swagger.entities.PropertyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {

	PropertyEntity getByApiTokenAndRuleNameAndName(String apiToken, String ruleName, String name);

	List<PropertyEntity> getByApiTokenAndRuleName(String apiToken, String ruleName);
}
