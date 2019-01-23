package io.swagger.repositories;

import io.swagger.entities.ConditionEntity;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;

@Repository
public interface ConditionRepository extends CrudRepository<ConditionEntity, String> {

	@Lock(LockModeType.OPTIMISTIC)
	@Transactional
	ConditionEntity getById(Long id);
}
