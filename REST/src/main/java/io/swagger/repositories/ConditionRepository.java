package io.swagger.repositories;

import io.swagger.entities.ConditionEntity;
import org.springframework.data.repository.CrudRepository;

public interface ConditionRepository extends CrudRepository<ConditionEntity, String> {
}
