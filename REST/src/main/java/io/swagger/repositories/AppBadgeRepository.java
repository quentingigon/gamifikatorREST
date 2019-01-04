package io.swagger.repositories;

import io.swagger.entities.AppBadgeEntity;
import org.springframework.data.repository.CrudRepository;

public interface AppBadgeRepository extends CrudRepository<AppBadgeEntity, String> {
}
