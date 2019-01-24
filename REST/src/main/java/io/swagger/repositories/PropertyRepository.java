package io.swagger.repositories;

import io.swagger.entities.PropertyEntity;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PropertyRepository extends CrudRepository<PropertyEntity, String> {

	@Lock(LockModeType.OPTIMISTIC)
	@Transactional
	List<PropertyEntity> getPropertyEntitiesByRuleId(Long ruleId);
}
