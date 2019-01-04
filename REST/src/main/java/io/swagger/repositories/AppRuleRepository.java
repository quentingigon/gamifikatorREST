package io.swagger.repositories;

import io.swagger.entities.AppRuleEntity;
import org.springframework.data.repository.CrudRepository;

public interface AppRuleRepository extends CrudRepository<AppRuleEntity, String> {
}
