package io.swagger.repositories;

import io.swagger.entities.BadgeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends CrudRepository<BadgeEntity, Long> {

	BadgeEntity getByName(String name);

	BadgeEntity findByApiTokenAndName(String apiToken, String name);

	BadgeEntity getById(Long id);

	List<BadgeEntity> findBadgeEntitiesByApiToken(String apiToken);
}
