package io.swagger.repositories;

import io.swagger.entities.RuleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RuleRepository extends CrudRepository<RuleEntity, String> {

}
