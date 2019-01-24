package io.swagger.repositories;

import io.swagger.entities.PropertyEntity;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;

@Repository
public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {

	@Lock(LockModeType.OPTIMISTIC)
	@Transactional
	PropertyEntity getByNameAndApiToken(String name, String apiToken);
}
