package io.swagger.repositories;

import io.swagger.entities.ApplicationEntity;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;


public interface ApplicationRepository extends CrudRepository<ApplicationEntity, String> {

	@Lock(LockModeType.PESSIMISTIC_WRITE) // TODO voir si OPTIMISTIC est pas mieux
	@Transactional
	ApplicationEntity findByApiToken(String apiToken);
}
