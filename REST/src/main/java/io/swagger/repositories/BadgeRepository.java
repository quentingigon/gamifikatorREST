package io.swagger.repositories;

import io.swagger.entities.BadgeEntity;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BadgeRepository extends CrudRepository<BadgeEntity, String> {

	@Lock(LockModeType.OPTIMISTIC)
	@Transactional
	BadgeEntity getByName(String name);

	@Lock(LockModeType.OPTIMISTIC)
	@Transactional
	BadgeEntity getByApiTokenAndName(String apitoken, String name);

	@Lock(LockModeType.OPTIMISTIC)
	@Transactional
	BadgeEntity getById(Long id);

	@Lock(LockModeType.OPTIMISTIC)
	@Transactional
	List<BadgeEntity> getBadgeEntitiesByApiToken(String apitoken);
}
