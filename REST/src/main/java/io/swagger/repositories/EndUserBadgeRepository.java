package io.swagger.repositories;

import io.swagger.entities.EndUserBadgeEntity;
import org.springframework.data.repository.CrudRepository;

public interface EndUserBadgeRepository extends CrudRepository<EndUserBadgeEntity, String> {
}
