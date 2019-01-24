package io.swagger.repositories;

import io.swagger.entities.RuleEntity;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RuleRepository extends CrudRepository<RuleEntity, Long> {

	@Lock(LockModeType.OPTIMISTIC)
	@Transactional
	RuleEntity getByName(String name);

	@Lock(LockModeType.OPTIMISTIC)
	@Transactional
	RuleEntity getByNameAndApiToken(String name, String apiToken);

	@Lock(LockModeType.OPTIMISTIC)
	@Transactional
	List<RuleEntity> getRuleEntitiesByApiToken(String apiToken);

	@Lock(LockModeType.OPTIMISTIC)
	@Transactional
	RuleEntity getByApiTokenAndBadgeId(String name, Long badgeId);


}
