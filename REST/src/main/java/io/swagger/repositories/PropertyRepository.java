package io.swagger.repositories;

import io.swagger.entities.PropertyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {

	PropertyEntity getByApiTokenAndRuleNameAndName(String apiToken, String ruleName, String name);
}
