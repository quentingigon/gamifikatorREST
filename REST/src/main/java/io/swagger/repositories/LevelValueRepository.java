package io.swagger.repositories;

import io.swagger.entities.LevelValueEntity;
import org.springframework.data.repository.CrudRepository;

public interface LevelValueRepository extends CrudRepository<LevelValueEntity, String> {
}
